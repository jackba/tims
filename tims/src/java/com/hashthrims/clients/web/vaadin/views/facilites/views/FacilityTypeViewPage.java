/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.facilites.forms.FacilityTypeForm;
import com.hashthrims.clients.web.vaadin.views.facilites.model.FacilityTypeBean;
import com.hashthrims.clients.web.vaadin.views.facilites.table.FacilityTypeTable;
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
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.infrastructure.factories.offices.OrganisationListFactory;

/**
 *
 * @author boniface
 */
public class FacilityTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final FacilityTypeForm cf;
    private final ClientDataService data = new ClientDataService();
    private final FacilityTypeTable table;
    private final  OrganisationListFactory factory = data.getOfficeFactory();

    public FacilityTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new FacilityTypeForm();
        form = cf.createFacilityTypeFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final FacilityTypeBean bean = new FacilityTypeBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new FacilityTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final FacilityTypeBean facilityType = new FacilityTypeBean();
            facilityType.setFacilityTypeName(record.getItemProperty("Facility Type Name").toString());
            facilityType.setFacilityId(new Long(table.getValue().toString()));

            if (facilityType != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(facilityType);
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
            saveNewFacilityType(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITYTYPE"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITYTYPE"));
        } else if (source == cf.getUpdate()) {
            saveEditedFacilityType(form);

            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITYTYPE"));

        } else if (source == cf.getDelete()) {
            deleteFacilityType(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITYTYPE"));

        }

    }

    public void saveNewFacilityType(Form form) {
       
        final String facilityTypeName = form.getField("facilityTypeName").getValue().toString();
        final FacilityType c = factory.createFacilityType(facilityTypeName);
        data.getFacilityTypeService().persist(c);

    }

    public void saveEditedFacilityType(Form form) {
 
        final String facilityTypeName = form.getField("facilityTypeName").getValue().toString();
        final Long facilityTypeId = Long.parseLong(form.getField("facilityId").getValue().toString());
        final FacilityType c = factory.updateFacilityType(facilityTypeName, facilityTypeId);
        data.getFacilityTypeService().merge(c);
    }

    public void deleteFacilityType(Form form) {
        final Long facilityTypeId = Long.parseLong(form.getField("facilityId").getValue().toString());
        final FacilityType c = factory.loadFacilityType(facilityTypeId);
        data.getFacilityTypeService().remove(c);
    }
}
