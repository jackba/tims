/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.training.forms.CourseStatusForm;
import com.hashthrims.clients.web.vaadin.views.training.model.CourseStatusBean;
import com.hashthrims.clients.web.vaadin.views.training.tables.CourseStatusTable;

import com.hashthrims.domain.traininglist.TrainingCourseStatus;
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
public class CourseStatusViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CourseStatusForm pform;
      private static ClientDataService data = new ClientDataService();
    private CourseStatusTable table;

    public CourseStatusViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CourseStatusForm();
        form = pform.createCourseStatusForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CourseStatusBean bean = new CourseStatusBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CourseStatusTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CourseStatusBean status = new CourseStatusBean();
            status.setStatusName(record.getItemProperty("Course Status").toString());
            status.setStatusId(new Long(table.getValue().toString()));

            if (status != form.getItemDataSource()) {
                BeanItem item = new BeanItem(status);
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
            saveNewTrainingCourseStatus(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "STATUS"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "STATUS"));
        } else if (source == pform.getUpdate()) {
            saveEditedTrainingCourseStatus(form);

            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "STATUS"));

        } else if (source == pform.getDelete()) {
            deleteTrainingCourseStatus(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "STATUS"));

        }

    }

    public void saveNewTrainingCourseStatus(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String status = form.getField("statusName").getValue().toString();
        TrainingCourseStatus s = factory.createTrainingCourseStatus(status);
        data.getTrainingCourseStatusType().persist(s);
    }

    public void saveEditedTrainingCourseStatus(Form form) {
         TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String sts = form.getField("statusName").getValue().toString();
        Long courseStatusId = Long.parseLong(form.getField("statusId").getValue().toString());
        TrainingCourseStatus s = factory.updatedTrainingCourseStatus(sts,courseStatusId);
        data.getTrainingCourseStatusType().merge(s);
    }

    public void deleteTrainingCourseStatus(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        Long salarySourceId = Long.parseLong(form.getField("statusId").getValue().toString());
        TrainingCourseStatus c = factory.loadTrainingCourseStatus(salarySourceId);
        data.getTrainingCourseStatusType().remove(c);
    }
}
