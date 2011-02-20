/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.CountinueEducationForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.CountinueEducationBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.CountinueEducationTable;
import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import com.hashthrims.infrastructure.factories.TrainingFactory;
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
 * @author bulelani
 */
public class ContinueEducationViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CountinueEducationForm pform;
     private static ClientDataService data = new ClientDataService();
    private CountinueEducationTable table;

    public ContinueEducationViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CountinueEducationForm();
        form = pform.createCountinueEducationForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CountinueEducationBean bean = new CountinueEducationBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CountinueEducationTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CountinueEducationBean courseEducation = new CountinueEducationBean();
            courseEducation.setNameOfContinueCourse(record.getItemProperty("Countinue Course Education").toString());
            courseEducation.setCreditHours(record.getItemProperty("Credit Hours").toString());
            courseEducation.setContinueId(new Long(table.getValue().toString()));

            if (courseEducation != form.getItemDataSource()) {
                BeanItem item = new BeanItem(courseEducation);
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
            saveNewContiuingEducationCourse(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EDUCATION"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EDUCATION"));
        } else if (source == pform.getUpdate()) {
            saveEditedContiuingEducationCourse(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EDUCATION"));

        } else if (source == pform.getDelete()) {
            deleteContiuingEducationCourse(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EDUCATION"));

        }

    }

    public void saveNewContiuingEducationCourse(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String courseEducation = form.getField("nameOfContinueCourse").getValue().toString();
        String creditHours = form.getField("creditHours").getValue().toString();
        ContiuingEducationCourse c = factory.createContiuingEducationCourse(courseEducation, creditHours);
        data.getContinuingEducationCourseService().persist(c);
    }

    public void saveEditedContiuingEducationCourse(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String courseEducation = form.getField("nameOfContinueCourse").getValue().toString();
        String creditHours = form.getField("creditHours").getValue().toString();
        Long categoryId = Long.parseLong(form.getField("continueId").getValue().toString());
        ContiuingEducationCourse c = factory.updatedContiuingEducationCourse(courseEducation, creditHours, categoryId);
        data.getContinuingEducationCourseService().merge(c);
    }

    public void deleteContiuingEducationCourse(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long categoryId = Long.parseLong(form.getField("continueId").getValue().toString());
        ContiuingEducationCourse c = factory.loadContiuingEducationCourse(categoryId);
        data.getContinuingEducationCourseService().remove(c);
    }
}
