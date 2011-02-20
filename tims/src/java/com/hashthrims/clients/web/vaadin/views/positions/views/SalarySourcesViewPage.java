/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.forms.SalarySourcesForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.SalarySourcesBean;
import com.hashthrims.clients.web.vaadin.views.positions.table.SalarySourcesTable;

import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;

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
public class SalarySourcesViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private SalarySourcesForm pform;
      private static ClientDataService data = new ClientDataService();
    private SalarySourcesTable table;

    public SalarySourcesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new SalarySourcesForm();
        form = pform.createSalarySourcesFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        SalarySourcesBean bean = new SalarySourcesBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new SalarySourcesTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            SalarySourcesBean salSources = new SalarySourcesBean();
            salSources.setSalarySourcesName(record.getItemProperty("Salary Source Name").toString());
            salSources.setSalarySourcesId(new Long(table.getValue().toString()));

            if (salSources != form.getItemDataSource()) {
                BeanItem item = new BeanItem(salSources);
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
            saveNewSalarySource(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "SOURCE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "SOURCE"));
        } else if (source == pform.getUpdate()) {
            saveEditedSalarySource(form);

            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "SOURCE"));

        } else if (source == pform.getDelete()) {
            deleteSalarySource(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "SOURCE"));

        }

    }

    public void saveNewSalarySource(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        String salarySourceName = form.getField("salarySourcesName").getValue().toString();
        SalarySources s = factory.createSalarySource(salarySourceName);
        data.getSalarySourcesService().persist(s);
    }

    public void saveEditedSalarySource(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        String salarySourceName = form.getField("salarySourcesName").getValue().toString();
        Long salarySourceId = Long.parseLong(form.getField("salarySourcesId").getValue().toString());
        SalarySources s = factory.updateSalarySource(salarySourceName, salarySourceId);
        data.getSalarySourcesService().merge(s);
    }

    public void deleteSalarySource(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        Long salarySourceId = Long.parseLong(form.getField("salarySourcesId").getValue().toString());
        SalarySources c = factory.loadSalarySource(salarySourceId);
        data.getSalarySourcesService().remove(c);
    }
}
