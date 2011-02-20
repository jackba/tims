/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.ProvinceBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.ProvinceForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.ProvinceTable;
import com.hashthrims.domain.regionlist.Country;
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
public class ProvinceViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ProvinceForm pform;
    private final ClientDataService data = new ClientDataService();
    private final ProvinceTable table;
    final private LocationFactory factory = data.getClientDataFactory();

    public ProvinceViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new ProvinceForm();
        form = pform.createProvinceFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final ProvinceBean bean = new ProvinceBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new ProvinceTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final ProvinceBean province = new ProvinceBean();
            province.setProvinceCode(record.getItemProperty("Province Code").toString());
            province.setProvinceId(new Long(table.getValue().toString()));
            province.setProvinceName(record.getItemProperty("Province Name").toString());
            province.setCountry(record.getItemProperty("Country").getValue().toString());
            if (province != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(province);
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
            saveNewProvince(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "PROVINCE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main, "PROVINCE"));
        } else if (source == pform.getUpdate()) {
            saveEditedProvince(form);

            main.mainView.setSecondComponent(new LocationMenuView(main, "PROVINCE"));

        } else if (source == pform.getDelete()) {
            deleteProvince(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "PROVINCE"));

        }

    }

    public void saveNewProvince(Form form) {

        final String provinceName = form.getField("provinceName").getValue().toString();
        final String provinceCode = form.getField("provinceCode").getValue().toString();
        final String country = form.getField("country").getValue().toString();
        final Country c = factory.createProvince(provinceName, provinceCode, country);
        data.getCountryService().persist(c);

    }

    public void saveEditedProvince(Form form) {
        final String provinceName = form.getField("provinceName").getValue().toString();
        final String provinceCode = form.getField("provinceCode").getValue().toString();
        final String country = form.getField("country").getValue().toString();
        final Long countryId = Long.parseLong(form.getField("provinceId").getValue().toString());
        final Country c = factory.updateProvince(provinceName, provinceCode, country, countryId);
        data.getCountryService().merge(c);
    }

    public void deleteProvince(Form form) {
        final Long provinceId = Long.parseLong(form.getField("provinceId").getValue().toString());
        final Province c = factory.loadRegion(provinceId);
        data.getRegionService().remove(c);
    }
}
