/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.jobs.CreateJobsStructureMenuView;
import com.hashthrims.clients.web.vaadin.views.jobs.forms.JobClassificationForm;
import com.hashthrims.clients.web.vaadin.views.jobs.model.JobClassificationBean;
import com.hashthrims.clients.web.vaadin.views.jobs.table.JobClassificationTable;
import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.infrastructure.factories.jobs.JobsFactory;
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
public class JobClassificationViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final JobClassificationForm cf;
    private static final ClientDataService data = new ClientDataService();
    private final JobClassificationTable table;
    private final JobsFactory factory = data.getJobsFactory();

    public JobClassificationViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new JobClassificationForm();
        form = cf.createJobClassificationFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final JobClassificationBean bean = new JobClassificationBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new JobClassificationTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final JobClassificationBean jc = new JobClassificationBean();
            jc.setName(record.getItemProperty("Name").toString());
            jc.setDescription(record.getItemProperty("Description").toString());
            jc.setCode(record.getItemProperty("Code").toString());
            jc.setClassificationId(new Long(table.getValue().toString()));

            if (jc != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(jc);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                cf.getSave().setVisible(false);
                cf.getEdit().setVisible(true);
                cf.getCancel().setVisible(true);
                cf.getDelete().setVisible(true);
                cf.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == cf.getSave()) {
            saveNewJobClass(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CLASSIFICATION"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CLASSIFICATION"));
        } else if (source == cf.getUpdate()) {
            saveEditedClassification(form);

            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CLASSIFICATION"));

        } else if (source == cf.getDelete()) {
            deleteClassification(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CLASSIFICATION"));

        }

    }

    public void saveNewJobClass(Form form) {
        final String name = form.getField("name").getValue().toString();
        final String code = form.getField("code").getValue().toString();
        final String desc = form.getField("description").getValue().toString();
        final Classifications c = factory.createClassifications(name, desc, code);
        data.getclassificationsServive().persist(c);

    }

    public void saveEditedClassification(Form form) {
        final String name = form.getField("name").getValue().toString();
        final String code = form.getField("code").getValue().toString();
        final String desc = form.getField("description").getValue().toString();
        final Long classificationId = Long.parseLong(form.getField("classificationId").getValue().toString());
        final Classifications c = factory.updatedClassification(name, code, desc, classificationId);
        data.getclassificationsServive().merge(c);
    }

    public void deleteClassification(Form form) {
        final Long classificationId = Long.parseLong(form.getField("classificationId").getValue().toString());
        final Classifications c = factory.loadClassification(classificationId);
        data.getclassificationsServive().remove(c);
    }
}
