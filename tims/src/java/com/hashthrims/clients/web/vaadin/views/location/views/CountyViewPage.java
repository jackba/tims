/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.CountyBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.CountyForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.CountyTable;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.Province;
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
public class CountyViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final CountyForm pform;
    private final ClientDataService data = new ClientDataService();
    private final CountyTable table;
    private final LocationFactory factory = data.getClientDataFactory();

    public CountyViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CountyForm();
        form = pform.createCountyFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CountyBean bean = new CountyBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CountyTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final CountyBean County = new CountyBean();
            County.setCountyCode(record.getItemProperty("District Code").toString());
            County.setCountyId(new Long(table.getValue().toString()));
            County.setCountyName(record.getItemProperty("District Name").toString());
            County.setProvince(record.getItemProperty("Province").getValue().toString());
            if (County != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(County);
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
            saveNewCounty(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "COUNTY"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main, "COUNTY"));
        } else if (source == pform.getUpdate()) {
            saveEditedCounty(form);

            main.mainView.setSecondComponent(new LocationMenuView(main, "COUNTY"));

        } else if (source == pform.getDelete()) {
            deleteCounty(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "COUNTY"));

        }

    }

    public void saveNewCounty(Form form) {

        final String countyName = form.getField("countyName").getValue().toString();
        final String countyCode = form.getField("countyCode").getValue().toString();
        final String province = form.getField("province").getValue().toString();
        final Province prov = factory.createCounty(countyName, countyCode, province);
        data.getRegionService().persist(prov);

    }

    public void saveEditedCounty(Form form) {

        final String countyName = form.getField("countyName").getValue().toString();
        final String countyCode = form.getField("countyCode").getValue().toString();
        final String province = form.getField("province").getValue().toString();
        final Long countryId = Long.parseLong(form.getField("countyId").getValue().toString());
        final Province prov = factory.updateCounty(countyName, countyCode, province, countryId);
        data.getRegionService().merge(prov);
    }

    public void deleteCounty(Form form) {

        final Long countyId = Long.parseLong(form.getField("countyId").getValue().toString());
        final County c = factory.loadCounty(countyId);
        data.getCountyService().remove(c);
    }
}
