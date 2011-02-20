/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;

import com.hashthrims.clients.web.vaadin.views.training.forms.CourseTypeForm;
import com.hashthrims.clients.web.vaadin.views.training.model.CourseTypeBean;
import com.hashthrims.clients.web.vaadin.views.training.tables.CourseTypeTable;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
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

/**
 *
 * @author boniface
 */
public class CourseTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener{

    private HashThrimsMain main;
    private Form form;
    private CourseTypeForm pform;
     private static ClientDataService data = new ClientDataService();
    private CourseTypeTable table;

    public CourseTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CourseTypeForm();
        form = pform.createCourseTypeForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CourseTypeBean bean = new CourseTypeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CourseTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CourseTypeBean courseType = new CourseTypeBean();
            courseType.setCourseTypeName(record.getItemProperty("Course Type Name").toString());
            courseType.setCourseTypeId(new Long(table.getValue().toString()));
           

            if (courseType != form.getItemDataSource()) {
                BeanItem item = new BeanItem(courseType);
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
            saveNewCourseType(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main,"TYPE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main,"TYPE"));
        } else if (source == pform.getUpdate()) {
            saveEditedScheduleCourses(form);

            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main,"TYPE"));

        } else if (source == pform.getDelete()) {
            deleteScheduleCourses(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main,"TYPE"));

        }

    }

    public void saveNewCourseType(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String courseTypeName = form.getField("courseTypeName").getValue().toString();
        CourseTypeName c = factory.createCourseTypeName(courseTypeName);
       data.getCourseTypeNameService().persist(c);
    }

    public void saveEditedScheduleCourses(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String courseTypeName = form.getField("courseTypeName").getValue().toString();

        Long courseTypeId= Long.parseLong(form.getField("courseTypeId").getValue().toString());
      CourseTypeName c = factory.updateCourseTypeName(courseTypeName, courseTypeId);
       data.getCourseTypeNameService().merge(c);
    }

    public void deleteScheduleCourses(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        Long courseTypeId= Long.parseLong(form.getField("courseTypeId").getValue().toString());
        CourseTypeName c = factory.loadCourseTypeName(courseTypeId);
        data.getCourseTypeNameService().remove(c);
    }
}
