/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.forms.IdentificationTypeForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model.IdentificationTypeBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table.IdentificationTypeTable;
import com.hashthrims.domain.employeelist.IdentificationType;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class IdentificationTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private IdentificationTypeForm cf;
    private ClientDataService data = new ClientDataService();
    private IdentificationTypeTable table;

    public IdentificationTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new IdentificationTypeForm();
        form = cf.createIdentificationTypeFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        IdentificationTypeBean bean = new IdentificationTypeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new IdentificationTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            IdentificationTypeBean jc = new IdentificationTypeBean();
            jc.setIdentityNameType(record.getItemProperty("Identification Type").toString());
            jc.setIdentityId(new Long(table.getValue().toString()));

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
        EmployeeListMenuView.demoSate = "IDENTIFICATION TYPE";
        if (source == cf.getSave()) {
            saveNewIdentificationType(form);
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
            saveEditedIdentificationType(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        } else if (source == cf.getDelete()) {
            deleteIdentificationType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        }

    }

    public void saveNewIdentificationType(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String name = form.getField("identityNameType").getValue().toString();
        IdentificationType c = factory.createIdentificationType(name);
        data.getIdentificationTypeService().persist(c);

    }

    public void saveEditedIdentificationType(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String name = form.getField("identityNameType").getValue().toString();
        Long identityId = Long.parseLong(form.getField("identityId").getValue().toString());
        IdentificationType c = factory.updatedIdentificationType(name, identityId);
        data.getIdentificationTypeService().merge(c);
    }

    public void deleteIdentificationType(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        Long identityId = Long.parseLong(form.getField("identityId").getValue().toString());
        IdentificationType c = factory.loadIdentificationType(identityId);
        data.getIdentificationTypeService().remove(c);
    }
}
