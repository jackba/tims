/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.courses.views;

import com.hashthrims.clients.web.vaadin.views.uploads.people.views.*;
import com.hashthrims.clients.web.vaadin.views.uploads.competencies.views.*;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyListFactory;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author bulelani
 */
public class AddCourseUploadsViewPage extends VerticalLayout implements
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

    public AddCourseUploadsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();

        courseType.setButtonCaption("Upload Courses Now");

        courseType.addListener((Upload.SucceededListener) this);
        courseType.addListener((Upload.FailedListener) this);

        final Embedded sample = new Embedded("", new ThemeResource("images/uploads/courses.png"));

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

                    String courseName = worksheet.getRow(i).getCell(0).toString();
                    String courseObjective = worksheet.getRow(i).getCell(1).toString();

                    Double di = new Double(worksheet.getRow(i).getCell(2).toString());
                    Long ctyid = di.longValue();
                    String courseTyp = data.getCourseTypeNameService().find(ctyid).getCourseType();

                    Double ti = new Double(worksheet.getRow(i).getCell(3).toString());
                    Long tfiedId = ti.longValue();
                    String courseCatergory = data.getTrainingCourseCategoryService().find(tfiedId).getCategoryName();

                    Double si = new Double(worksheet.getRow(i).getCell(4).toString());
                    Long stid = si.longValue();
                    String courseStatus = data.getStatusService().find(stid).getStatus();

                    Double isti = new Double(worksheet.getRow(i).getCell(5).toString());
                    Long tiId = isti.longValue();
                    String trainingInstitution = data.getTrainingInstitutionService().find(tiId).getTrainingInstitution();

                    Double cri = new Double(worksheet.getRow(i).getCell(6).toString());
                    Long critId = cri.longValue();
                    String courseCriteria = data.getCriteriaService().find(critId).getSelectionCriteria();


                    Map<String, String> simpleFields = new HashMap<String, String>();
                    simpleFields.put("courseName", courseName);
                    simpleFields.put("courseCatergory", courseCatergory);
                    simpleFields.put("courseStatus", courseStatus);
                    simpleFields.put("trainingInstitution", trainingInstitution);
                    simpleFields.put("courseCriteria", courseCriteria);
                    simpleFields.put("courseType", courseTyp);
                    simpleFields.put("courseObjective", courseObjective);

                    Collection<Long> funderId = fieldValues.getLongValuesFromStringTokens(worksheet.getRow(i).getCell(7).toString());
                    List<String> trainingFunders = new ArrayList<String>();
                    for (Long fid : funderId) {
                        TrainingFunder f = data.getTrainingFunderService().find(fid);
                        trainingFunders.add(f.getTrainingFunderName());
                    }

                    Collection<Long> tgId = fieldValues.getLongValuesFromStringTokens(worksheet.getRow(i).getCell(8).toString());
                    List<String> targetGroups = new ArrayList<String>();
                    for (Long fid : tgId) {
                        TargetGroup f = data.getTargetGroupService().find(fid);
                        targetGroups.add(f.getTargetGroupName());
                    }


                    Collection<Long> cpmpId = fieldValues.getLongValuesFromStringTokens(worksheet.getRow(i).getCell(9).toString());
                    List<String> competencies = new ArrayList<String>();
                    for (Long fid : cpmpId) {
                        CompetencyList f = data.getCompetencyList().find(fid);
                        competencies.add(f.getComp_name());
                    }

                  
                    TrainingCourses c = factory.createTrainingCourses(simpleFields, competencies, trainingFunders, targetGroups);
                    data.getTrainingCoursesService().persist(c);

                }
                main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "COURSE"));

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
