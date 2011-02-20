/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.views;

import com.hashthrims.clients.web.vaadin.views.jobs.forms.JobsForm;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.jobs.CreateJobsStructureMenuView;
import com.hashthrims.clients.web.vaadin.views.jobs.model.JobsBean;
import com.hashthrims.clients.web.vaadin.views.jobs.table.JobsTable;
import com.hashthrims.domain.jobs.Jobs;
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
public class JobsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final JobsForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final JobsTable table;
    private final JobsFactory factory = data.getJobsFactory();

    public JobsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new JobsForm();
        form = pform.createJobFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        JobsBean bean = new JobsBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new JobsTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final JobsBean job = new JobsBean();
            job.setTitle(record.getItemProperty("Job Title").toString());
            job.setCode(record.getItemProperty("Job Code").toString());
            job.setDescription(record.getItemProperty("Job Description").toString());
//            job.setSalaryGrade(record.getItemProperty("Salary Grade").getValue().toString());
            job.setJobClassification(record.getItemProperty("Classification").getValue().toString());
            job.setCadre(record.getItemProperty("Cadre").getValue().toString());
            job.setJobsId(new Long(table.getValue().toString()));
            if (job != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(job);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(pform.orderList());

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
            saveNewDistrict(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "JOBS"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "JOBS"));
        } else if (source == pform.getUpdate()) {
            saveEditedDistrict(form);

            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "JOBS"));

        } else if (source == pform.getDelete()) {
            deleteDistrict(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "JOBS"));

        }

    }

    public void saveNewDistrict(Form form) {

        final String title = form.getField("title").getValue().toString();
        final String description = form.getField("description").getValue().toString();
        final String code = form.getField("code").getValue().toString();
//        String salaryGrade = form.getField("salaryGrade").getValue().toString();
        final String jobClassification = form.getField("jobClassification").getValue().toString();
        final String cadre = form.getField("cadre").getValue().toString();

        final Jobs d = factory.createJob(title, description, code, jobClassification, cadre);
        data.getJobService().persist(d);

    }

    public void saveEditedDistrict(Form form) {

        final String title = form.getField("title").getValue().toString();
        final String description = form.getField("description").getValue().toString();
        final String code = form.getField("code").getValue().toString();
        //final String salaryGrade = form.getField("salaryGrade").getValue().toString();
        final String jobClassification = form.getField("jobClassification").getValue().toString();
        final String cadre = form.getField("cadre").getValue().toString();
        final Long jobsId = Long.parseLong(form.getField("jobsId").getValue().toString());
        final Jobs j = factory.updateJob(title, description, code, jobClassification, cadre, jobsId);
        data.getJobService().merge(j);
    }

    public void deleteDistrict(Form form) {
        final Long jobsId = Long.parseLong(form.getField("jobsId").getValue().toString());
        final Jobs c = factory.loadJobs(jobsId);
        data.getJobService().remove(c);
    }
}
