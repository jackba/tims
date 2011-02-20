/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.training.TrainingUtil;

import com.hashthrims.clients.web.vaadin.views.training.forms.CourseForm;
import com.hashthrims.clients.web.vaadin.views.training.model.CourseBean;
import com.hashthrims.clients.web.vaadin.views.training.tables.TrainingCoursesTable;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class CourseViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CourseForm pform;
    private static ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private TrainingCoursesTable table;
    private TrainingUtil st = new TrainingUtil();

    public CourseViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CourseForm();
        form = pform.createCourseFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CourseBean bean = new CourseBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new TrainingCoursesTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CourseBean course = new CourseBean();


            course.setCourseId(new Long(table.getValue().toString()));
            course.setCourseName(record.getItemProperty("Name").toString());
            course.setCourseCatergory(record.getItemProperty("Training Field").toString());
            course.setCourseStatus(record.getItemProperty("Status").toString());
            course.setCourseType(record.getItemProperty("Type").toString());
            course.setTrainingInstitution(record.getItemProperty("Offered By").toString());
            course.setCourseNotes(record.getItemProperty("Notes").toString());



            if (course != form.getItemDataSource()) {
                BeanItem item = new BeanItem(course);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewCourse(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "COURSE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "COURSE"));
        } else if (source == pform.getUpdate()) {
            saveEditedCourse(form);

            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "COURSE"));

        } else if (source == pform.getDelete()) {
            deleteCourse(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "COURSE"));

        }

    }

    public void saveNewCourse(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();

        String courseName = form.getField("courseName").getValue().toString();
        String courseCatergory = form.getField("courseCatergory").getValue().toString();
        String courseType = form.getField("courseType").getValue().toString();
        String courseStatus = form.getField("courseStatus").getValue().toString();
        String trainingInstitution = form.getField("trainingInstitution").getValue().toString();
        String courseNotes = form.getField("courseNotes").getValue().toString();

        Map<String, String> simpleFields = new HashMap<String, String>();
        simpleFields.put("courseName", courseName);
        simpleFields.put("courseCatergory", courseCatergory);
        simpleFields.put("courseStatus", courseStatus);
        simpleFields.put("trainingInstitution", trainingInstitution);
        simpleFields.put("courseNotes", courseNotes);
        simpleFields.put("courseType", courseType);


        Object competency = form.getField("competency").getValue();
        Object trainingFunder = form.getField("trainingFunder").getValue();
        List<String> competencies = fieldValues.getSelectListFields(competency);
        List<String> trainingFunders = fieldValues.getSelectListFields(trainingFunder);

        TrainingCourses c = factory.createTrainingCourses(simpleFields, competencies, trainingFunders);
        data.getTrainingCoursesService().persist(c);
    }

    public void saveEditedCourse(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        
        String courseName = form.getField("courseName").getValue().toString();
        String courseCatergory = form.getField("courseCatergory").getValue().toString();
        String courseType = form.getField("courseType").getValue().toString();
        String courseStatus = form.getField("courseStatus").getValue().toString();
        String trainingInstitution = form.getField("trainingInstitution").getValue().toString();
        String courseNotes = form.getField("courseNotes").getValue().toString();

        Map<String, String> simpleFields = new HashMap<String, String>();
        simpleFields.put("courseName", courseName);
        simpleFields.put("courseCatergory", courseCatergory);
        simpleFields.put("courseStatus", courseStatus);
        simpleFields.put("trainingInstitution", trainingInstitution);
        simpleFields.put("courseNotes", courseNotes);
        simpleFields.put("courseType", courseType);


        Object competency = form.getField("competency").getValue();
        Object trainingFunder = form.getField("trainingFunder").getValue();
        List<String> competencies = fieldValues.getSelectListFields(competency);
        List<String> trainingFunders = fieldValues.getSelectListFields(trainingFunder);

        Long courseId = Long.parseLong(form.getField("courseId").getValue().toString());

        TrainingCourses c = factory.updateTrainingCourses(simpleFields, competencies, trainingFunders, courseId);
        data.getTrainingCoursesService().merge(c);
    }

    public void deleteCourse(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        Long courseId = Long.parseLong(form.getField("courseId").getValue().toString());
        TrainingCourses c = factory.loadTrainingCourses(courseId);
        data.getTrainingCoursesService().remove(c);
    }
}
