/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.forms.CompetencyEvaluationForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.model.CompetencyEvaluationBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.table.CompetencyEvaluationTable;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyEvaluationFactory;
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
public class CompetencyEvaluationViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CompetencyEvaluationForm cf;
    private static ClientDataService data = new ClientDataService();
    private CompetencyEvaluationTable table;

    public CompetencyEvaluationViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new CompetencyEvaluationForm();
        form = cf.createCompetencyEvaluationFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        CompetencyEvaluationBean bean = new CompetencyEvaluationBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CompetencyEvaluationTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CompetencyEvaluationBean competencyEvalBean = new CompetencyEvaluationBean();
            competencyEvalBean.setCompetencyEvaluationName(record.getItemProperty("Competency Evaluation").toString());
            competencyEvalBean.setCompetencyEvaluationId(new Long(table.getValue().toString()));
            if (competencyEvalBean != form.getItemDataSource()) {
                BeanItem item = new BeanItem(competencyEvalBean);
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
            saveNewCompetencyEvaluation(form);
            EmployeeListMenuView.compSate = "COMPETENCY EVALUATION";
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, "COMPETENCY"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            EmployeeListMenuView.compSate = "COMPETENCY EVALUATION";
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getUpdate()) {
            saveEditedCompetencyEvaluation(form);
            EmployeeListMenuView.compSate = "COMPETENCY EVALUATION";
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));

        } else if (source == cf.getDelete()) {
            deleteCompetencyEvaluation(form);
            EmployeeListMenuView.compSate = "COMPETENCY EVALUATION";
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));

        }

    }

    public void saveNewCompetencyEvaluation(Form form) {
        CompetencyEvaluationFactory factory = data.getCompetencyEvaluationFactory();
        String name = form.getField("competencyEvaluationName").getValue().toString();
        CompetencyEvaluation c = factory.createCompetencyEvaluation(name);
        data.getCompetencyEvaluationService().persist(c);

    }

    public void saveEditedCompetencyEvaluation(Form form) {
       CompetencyEvaluationFactory factory = data.getCompetencyEvaluationFactory();
        String name = form.getField("competencyEvaluationName").getValue().toString();
        Long competencyEvaluationId = Long.parseLong(form.getField("competencyEvaluationId").getValue().toString());
        CompetencyEvaluation c = factory.updatedCompetencyEvaluation(name, competencyEvaluationId);
        data.getCompetencyEvaluationService().merge(c);
    }

    public void deleteCompetencyEvaluation(Form form) {
        CompetencyEvaluationFactory factory = data.getCompetencyEvaluationFactory();
        Long competencyEvaluationId = Long.parseLong(form.getField("competencyEvaluationId").getValue().toString());
        CompetencyEvaluation c = factory.loadCompetencyEvaluation(competencyEvaluationId);
        data.getCompetencyEvaluationService().remove(c);
    }
}
