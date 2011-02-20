/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency.views;

import com.hashthrims.clients.web.vaadin.views.employeelists.competency.forms.CompetencyListForm;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.model.CompetencyListBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.table.CompetencyListTable;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyListFactory;
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
public class CompetencyListViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CompetencyListForm cf;
   private static ClientDataService data = new ClientDataService();
    private CompetencyListTable table;

    public CompetencyListViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new CompetencyListForm();
        form = cf.createCompetencyForm();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        CompetencyListBean bean = new CompetencyListBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CompetencyListTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CompetencyListBean jc = new CompetencyListBean();
            jc.setCompetencyName(record.getItemProperty("Competency Name").toString());
            jc.setCompetencyType(record.getItemProperty("Subject Area").toString());
            jc.setCompetencyNotes(record.getItemProperty("Competency Notes").toString());
            jc.setCompetencyId(new Long(table.getValue().toString()));

            if (jc != form.getItemDataSource()) {
                BeanItem item = new BeanItem(jc);
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
        EmployeeListMenuView.compSate = "COMPETENCY";
        if (source == cf.getSave()) {
            saveNewCompetencyList(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getUpdate()) {
            saveEditedCompetencyList(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getDelete()) {
            deleteCompetency(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));

        }

    }

    public void saveNewCompetencyList(Form form) {
        CompetencyListFactory factory = data.getCompetencyListFactory();
        String competencyName = form.getField("competencyName").getValue().toString();
        String competencyNotes = form.getField("competencyNotes").getValue().toString();
        String competencyType = form.getField("competencyType").getValue().toString();
        CompetencyList c = factory.createCompetencyList(competencyName, competencyNotes, competencyType);
        data.getCompetencyList().persist(c);

    }

    public void saveEditedCompetencyList(Form form) {
        CompetencyListFactory factory = data.getCompetencyListFactory();
        String competencyName = form.getField("competencyName").getValue().toString();
        String competencyNotes = form.getField("competencyNotes").getValue().toString();
        String competencyType = form.getField("competencyType").getValue().toString();
        Long competencyId = Long.parseLong(form.getField("competencyId").getValue().toString());
        CompetencyList c = factory.updateCompetencyList(competencyName, competencyNotes, competencyType, competencyId);
        data.getCompetencyList().merge(c);
    }

    public void deleteCompetency(Form form) {
        CompetencyListFactory factory = data.getCompetencyListFactory();
        Long competencyId = Long.parseLong(form.getField("competencyId").getValue().toString());
        CompetencyList c = factory.loadCompetencyList(competencyId);
        data.getCompetencyList().remove(c);
    }
}
