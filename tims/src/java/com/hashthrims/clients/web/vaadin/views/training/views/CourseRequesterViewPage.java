/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.training.forms.CourseRequestorForm;
import com.hashthrims.clients.web.vaadin.views.training.model.CourseRequestorBean;
import com.hashthrims.clients.web.vaadin.views.training.tables.CourseRequestorTable;

import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
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
public class CourseRequesterViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CourseRequestorForm pform;
     private static ClientDataService data = new ClientDataService();
    private CourseRequestorTable table;

    public CourseRequesterViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CourseRequestorForm();
        form = pform.createCourseRequestorForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CourseRequestorBean bean = new CourseRequestorBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CourseRequestorTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CourseRequestorBean requestor = new CourseRequestorBean();
            requestor.setRequestorName(record.getItemProperty("Course Requestor").toString());
            requestor.setRequestorId(new Long(table.getValue().toString()));

            if (requestor != form.getItemDataSource()) {
                BeanItem item = new BeanItem(requestor);
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
            saveNewTrainingCourseRequestors(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "REQUEST"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "REQUEST"));
        } else if (source == pform.getUpdate()) {
            saveEditedTrainingCourseRequestors(form);

            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "REQUEST"));

        } else if (source == pform.getDelete()) {
            deleteTrainingCourseRequestors(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "REQUEST"));

        }

    }


        private String requestorName;
    private Long requestorId;
    public void saveNewTrainingCourseRequestors(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String req = form.getField("requestorName").getValue().toString();
        TrainingCourseRequestors s = factory.createTrainingCourseRequestors(req);
        data.getTrainingCourseRequestorsType().persist(s);
    }

    public void saveEditedTrainingCourseRequestors(Form form) {
       TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
       String req = form.getField("requestorName").getValue().toString();
       Long reqId = Long.parseLong(form.getField("requestorId").getValue().toString());
       TrainingCourseRequestors s = factory.updatedTrainingCourseRequestors(req,reqId);
       data.getTrainingCourseRequestorsType().merge(s);
    }

    public void deleteTrainingCourseRequestors(Form form) {
       TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        Long reqId = Long.parseLong(form.getField("requestorId").getValue().toString());
        TrainingCourseRequestors c = factory.loadTrainingCourseRequestors(reqId);
        data.getTrainingCourseRequestorsType().remove(c);
    }
}
