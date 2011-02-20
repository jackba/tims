/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.forms.MaritalStatusForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model.MaritalStatusBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table.MaritalStatusTable;
import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.infrastructure.factories.EmployeeFactory;
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
public class MaritalStatusViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private MaritalStatusForm cf;
    private ClientDataService data = new ClientDataService();
    private MaritalStatusTable table;

    public MaritalStatusViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new MaritalStatusForm();
        form = cf.createMaritalStatusFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        MaritalStatusBean bean = new MaritalStatusBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MaritalStatusTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            MaritalStatusBean maritalStatus = new MaritalStatusBean();
            maritalStatus.setStatus(record.getItemProperty("Marital Status").toString());
            maritalStatus.setMaritalStatusId(new Long(table.getValue().toString()));
            if (maritalStatus != form.getItemDataSource()) {
                BeanItem item = new BeanItem(maritalStatus);
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
            saveNewMaritalStatus(form);
            EmployeeListMenuView.demoSate = "MARITAL STATUS";
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));
        } else if (source == cf.getUpdate()) {
            saveEditedMaritalStatus(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        } else if (source == cf.getDelete()) {
            deleteMaritalStatus(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        }

    }

    public void saveNewMaritalStatus(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String status = form.getField("status").getValue().toString();
        MaritalStatusList m = factory.createMaritalStatusList(status);
        data.getMaritalStatusListService().persist(m);

    }

    public void saveEditedMaritalStatus(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String status = form.getField("status").getValue().toString();
        Long maritalStatusId = Long.parseLong(form.getField("maritalStatusId").getValue().toString());
        MaritalStatusList c = factory.updatedMaritalStatusList(status, maritalStatusId);
        data.getMaritalStatusListService().merge(c);
    }

    public void deleteMaritalStatus(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        Long maritalStatusId = Long.parseLong(form.getField("maritalStatusId").getValue().toString());
        MaritalStatusList c = factory.loadMaritalStatusList(maritalStatusId);
        data.getMaritalStatusListService().remove(c);
    }
}
