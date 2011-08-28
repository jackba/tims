/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.factories.PersonFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author bulelani
 */
public class AddPeopleUploadsViewPage extends VerticalLayout implements
        Upload.SucceededListener,
        Upload.FailedListener,
        Upload.Receiver {

    private HashThrimsMain main;
    private VerticalLayout courseTypeLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    private File file;
    private Panel messagePanel = new Panel();
    final Upload courseType = new Upload("Upload the file here", this);
    private DataFieldsUtil fieldValues = new DataFieldsUtil();

    public AddPeopleUploadsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();

        courseType.setButtonCaption("Upload Now");

        courseType.addListener((Upload.SucceededListener) this);
        courseType.addListener((Upload.FailedListener) this);

        final Embedded sample = new Embedded("", new ThemeResource("images/uploads/people.png"));

        courseTypeLayout.addComponent(new Label("<h2> Sample Spread Sheet Format</h2>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(sample);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));

        courseTypeLayout.addComponent(courseType);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));

        addComponent(courseTypeLayout);
        addComponent(messagePanel);

    }

    @Override
    public void uploadSucceeded(SucceededEvent event) {
        Upload source = (Upload) event.getComponent();
        if (source == courseType) {
            final FileResource excelFile = new FileResource(file, getApplication());
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(excelFile.getSourceFile());
                HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                HSSFSheet worksheet = workbook.getSheetAt(0);
                for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
//                    CompetencyListFactory factory = data.getCompetencyListFactory();
//                    String competencyName = worksheet.getRow(i).getCell(0).toString();
                    Map<String, String> names = new HashMap<String, String>();
                    Map<String, Collection<Long>> lists = new HashMap<String, Collection<Long>>();
                    Map<String, Long> demo = new HashMap<String, Long>();


                    PersonFactory factory = data.getPersonFactory();
                    String surname = worksheet.getRow(i).getCell(0).toString();
                    String firstname = worksheet.getRow(i).getCell(1).toString();
                    String othername = worksheet.getRow(i).getCell(2).toString();
                    names.put("firstname", firstname);
                    names.put("surname", surname);
                    names.put("othername", othername);
                    Date dob = fieldValues.getDateFields(worksheet.getRow(i).getCell(3).toString());

                    Long genderId = fieldValues.getLongsFromSpreadSheet(worksheet.getRow(i).getCell(4).toString());
                    Long raceId = fieldValues.getLongsFromSpreadSheet(worksheet.getRow(i).getCell(5).toString());
                    demo.put("raceId", raceId);
                    demo.put("genderId", genderId);



                    Collection<Long> rolesid = fieldValues.getLongValuesFromStringTokens(worksheet.getRow(i).getCell(6).toString());
                    Collection<Long> competencyFieldId = fieldValues.getLongValuesFromStringTokens(worksheet.getRow(i).getCell(8).toString());
                    Collection<Long> expertiseId = fieldValues.getLongValuesFromStringTokens(worksheet.getRow(i).getCell(7).toString());

                    lists.put("rolesid", rolesid);
                    lists.put("competencyFieldId", competencyFieldId);
                    lists.put("expertiseId", expertiseId);
                    Long positionId = fieldValues.getLongsFromSpreadSheet(worksheet.getRow(i).getCell(9).toString());
                    Positions pos = data.getPositionsService().find(positionId);

                    Person p = null;
                    if (firstname != null || surname != null) {
                        p = factory.createNewPerson(names, lists, demo, dob);
                        EmployeePosition em = new EmployeePosition();
                        em.setPosition(pos);
                        em.setStartDate(new Date());
                        em.setStatus("CURRENT");
                        p.getPosition().add(em);
                        data.getPersonService().persist(p);
                    } else {
                        throw new UnsupportedOperationException("First Name and Last Name needed");
                    }


                }
                main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
            } catch (FileNotFoundException e) {
                messagePanel.addComponent(new Label("<h3> Problem With Upload</h3>", Label.CONTENT_XHTML));
            } catch (IOException e) {
                messagePanel.addComponent(new Label("<h3> Problem With Upload</h3>", Label.CONTENT_XHTML));
            }

        }

    }

    @Override
    public void uploadFailed(FailedEvent event) {
        Upload source = (Upload) event.getComponent();
        if (source == courseType) {
            messagePanel.addComponent(new Label("<h3> Uploadd Failed</h3>", Label.CONTENT_XHTML));
        }
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null; // Output stream to write to
        file = new File("/tmp/" + filename);
        try {
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return fos; // Return the output stream to write to

    }
}
