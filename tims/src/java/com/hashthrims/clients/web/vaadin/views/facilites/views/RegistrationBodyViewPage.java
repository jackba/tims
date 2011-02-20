/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.clients.web.vaadin.views.facilites.forms.RegistrationBodyForm;
import com.hashthrims.clients.web.vaadin.views.facilites.model.RegistrationBodyBean;
import com.hashthrims.clients.web.vaadin.views.facilites.table.RegistrationBodyTable;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.infrastructure.factories.offices.OrganisationListFactory;
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
public class RegistrationBodyViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {
    private final HashThrimsMain main;
    private final Form form;
    private final RegistrationBodyForm cf;
    private final ClientDataService data = new ClientDataService();
    private final RegistrationBodyTable table;
    private final OrganisationListFactory factory = data.getOfficeFactory();

    public RegistrationBodyViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new RegistrationBodyForm();
        form = cf.createRegistrationFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final RegistrationBodyBean bean = new RegistrationBodyBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new RegistrationBodyTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final RegistrationBodyBean reg = new RegistrationBodyBean();
            reg.setRegistrationBodyName(record.getItemProperty("Registration Body Name").toString());
            reg.setRegistrationBodyId(new Long(table.getValue().toString()));
            if (reg != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(reg);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(cf.orderList());

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
           saveNewRegistrationBody(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main,"REGISTRATIONBODY"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new OrganisationListMenuView(main,"REGISTRATIONBODY"));
        } else if (source == cf.getUpdate()) {
           saveEditedRegistrationBody(form);

            main.mainView.setSecondComponent(new OrganisationListMenuView(main,"REGISTRATIONBODY"));

        } else if (source == cf.getDelete()) {
            deleteRegistrationBody(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main,"REGISTRATIONBODY"));

        }

    }

    public void saveNewRegistrationBody(Form form) {
      
        final String registrationBodyName = form.getField("registrationBodyName").getValue().toString();
        final RegistrationBody c = factory.createRegistrationBody(registrationBodyName);
        data.getRegistrationBodyService().persist(c);

    }

    public void saveEditedRegistrationBody(Form form) {
        final String registrationBodyName = form.getField("registrationBodyName").getValue().toString();
        final Long registrationBodyId = Long.parseLong(form.getField("registrationBodyId").getValue().toString());
        final RegistrationBody c = factory.updateRegistrationBody(registrationBodyName, registrationBodyId );
        data.getRegistrationBodyService().merge(c);
    }

    public void deleteRegistrationBody(Form form) {
        final Long registrationBodyId = Long.parseLong(form.getField("registrationBodyId").getValue().toString());
        final RegistrationBody c = factory.loadRegistrationBody(registrationBodyId);
        data.getRegistrationBodyService().remove(c);
    }
}
