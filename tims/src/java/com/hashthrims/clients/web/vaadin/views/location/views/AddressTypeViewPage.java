/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.AddressTypeBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.AddressTypeForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.AddressTypeTable;
import com.hashthrims.domain.regionlist.AddressType;
import com.hashthrims.infrastructure.factories.LocationFactory;
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
public class AddressTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final AddressTypeForm cf;
    private final ClientDataService data = new ClientDataService();
    final private LocationFactory factory = data.getClientDataFactory();
    private final AddressTypeTable table;

    public AddressTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new AddressTypeForm();
        form = cf.createAddressTypeFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final AddressTypeBean bean = new AddressTypeBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new AddressTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final AddressTypeBean AddressType = new AddressTypeBean();
            AddressType.setAddressTypeName(record.getItemProperty("Address Type Name").toString());
            AddressType.setAddressTypeId(new Long(table.getValue().toString()));

            if (AddressType != form.getItemDataSource()) {
                BeanItem item = new BeanItem(AddressType);
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
            saveNewAddressType(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "ADDRESS"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main, "ADDRESS"));
        } else if (source == cf.getUpdate()) {
            saveEditedAddressType(form);

            main.mainView.setSecondComponent(new LocationMenuView(main, "ADDRESS"));

        } else if (source == cf.getDelete()) {
            deleteAddressType(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "ADDRESS"));

        }

    }

    public void saveNewAddressType(Form form) {
        final String addressTypeName = form.getField("addressTypeName").getValue().toString();
        final AddressType c = factory.createAddressType(addressTypeName);
        data.getAddressTypeService().persist(c);

    }

    public void saveEditedAddressType(Form form) {
        final String addressTypeName = form.getField("addressTypeName").getValue().toString();
        final Long addressTypeId = Long.parseLong(form.getField("addressTypeId").getValue().toString());
        final AddressType c = factory.updatedAddressType(addressTypeName, addressTypeId);
        data.getAddressTypeService().merge(c);
    }

    public void deleteAddressType(Form form) {
        final Long addressTypeId = Long.parseLong(form.getField("addressTypeId").getValue().toString());
        final AddressType c = factory.loadAddressType(addressTypeId);
        data.getAddressTypeService().remove(c);
    }
}
