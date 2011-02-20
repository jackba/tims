/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.views;

import com.hashthrims.clients.web.vaadin.views.jobs.forms.SalaryGradesForm;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.jobs.CreateJobsStructureMenuView;
import com.hashthrims.clients.web.vaadin.views.jobs.model.SalaryGradeBean;
import com.hashthrims.clients.web.vaadin.views.jobs.table.SalaryGradeTable;
import com.hashthrims.domain.jobs.SalaryGrade;
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
import java.math.BigDecimal;

/**
 *
 * @author boniface
 */
public class SalaryGradeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private SalaryGradesForm cf;
    private static ClientDataService data = new ClientDataService();
    private SalaryGradeTable table;

    public SalaryGradeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new SalaryGradesForm();
        form = cf.createSalaryGradesFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        SalaryGradeBean bean = new SalaryGradeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new SalaryGradeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            SalaryGradeBean salaryGrade = new SalaryGradeBean();

            salaryGrade.setGradeName(record.getItemProperty("Grade Name").toString());
            salaryGrade.setStartAmount(new BigDecimal(record.getItemProperty("Start Amount").toString()));
            salaryGrade.setEndAmount(new BigDecimal(record.getItemProperty("End Amount").toString()));
            salaryGrade.setMidAmount(new BigDecimal(record.getItemProperty("Mid Amount").toString()));
            salaryGrade.setCurrentAmount(new BigDecimal(record.getItemProperty("Current Amount").toString()));
            salaryGrade.setCurrency(record.getItemProperty("Currency").toString());
            salaryGrade.setNotes(record.getItemProperty("Notes").toString());
            salaryGrade.setSalaryGradeId(new Long(table.getValue().toString()));

            if (salaryGrade != form.getItemDataSource()) {
                BeanItem item = new BeanItem(salaryGrade);
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
            saveNewSalaryGrade(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "GRADE"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "GRADE"));
        } else if (source == cf.getUpdate()) {
            saveEditedSalaryGrade(form);

            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "GRADE"));

        } else if (source == cf.getDelete()) {
            deleteSalaryGrade(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "GRADE"));

        }

    }

    public void saveNewSalaryGrade(Form form) {
       JobsFactory factory = data.getJobsFactory();
        String gradeName = form.getField("gradeName").toString();
        String startAmount = form.getField("startAmount").toString();
        String endAmount = form.getField("endAmount").toString();
        String midAmount = form.getField("midAmount").toString();
        String currentAmount = form.getField("currentAmount").toString();
        String currency = form.getField("currency").toString();
        String notes = form.getField("notes").toString();
        SalaryGrade c = factory.createSalaryGrade(gradeName, startAmount, endAmount, midAmount, currentAmount, currency, notes);
        data.getsalaryGradesService().persist(c);

    }

    public void saveEditedSalaryGrade(Form form) {
       JobsFactory factory = data.getJobsFactory();
        String gradeName = form.getField("gradeName").toString();
        String startAmount = form.getField("startAmount").toString();
        String endAmount = form.getField("endAmount").toString();
        String midAmount = form.getField("midAmount").toString();
        String currentAmount = form.getField("currentAmount").toString();
        String currency = form.getField("currency").toString();
        String notes = form.getField("notes").toString();
        Long salaryGradeId = Long.parseLong(form.getField("salaryGradeId").getValue().toString());
        SalaryGrade sg = factory.updateSalaryGrade(gradeName, startAmount, endAmount, midAmount, currentAmount, currency, notes, salaryGradeId);
        data.getsalaryGradesService().merge(sg);
    }

    public void deleteSalaryGrade(Form form) {
      JobsFactory factory = data.getJobsFactory();
        Long salaryGradeId = Long.parseLong(form.getField("salaryGradeId").getValue().toString());
        SalaryGrade c = factory.loadSalaryGrades(salaryGradeId);
        data.getsalaryGradesService().remove(c);
    }
}
