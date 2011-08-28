/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.courses.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author bulelani
 */
public class TargetGroupUploadsViewPage extends VerticalLayout implements
        Upload.SucceededListener,
        Upload.FailedListener,
        Upload.Receiver {

    private HashThrimsMain main;
    private VerticalLayout courseTypeLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    private File file;
    private Panel messagePanel = new Panel();
    final Upload courseType = new Upload("Upload the file here", this);

    public TargetGroupUploadsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();

        courseType.setButtonCaption("Upload Target Groups Now");

        courseType.addListener((Upload.SucceededListener) this);
        courseType.addListener((Upload.FailedListener) this);

        final Embedded sample = new Embedded("", new ThemeResource("images/uploads/targetgroup.png"));

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
                    TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
                    String targetGroup = worksheet.getRow(i).getCell(0).toString();
                    TargetGroup c = factory.createTargetGroup(targetGroup);
                    data.getTargetGroupService().persist(c);

                }
                main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "TARGET"));

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
