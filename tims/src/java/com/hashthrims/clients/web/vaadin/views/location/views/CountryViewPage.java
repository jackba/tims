/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.CountryBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.CountryForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.CountryTable;
import com.hashthrims.domain.regionlist.Country;
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
public class CountryViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {
    private final HashThrimsMain main;
    private final Form form;
    private final CountryForm cf;
    private final ClientDataService data = new ClientDataService();
    private final CountryTable table;
    private final LocationFactory factory = data.getClientDataFactory();


    public CountryViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new CountryForm();
        form = cf.createCountryFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final CountryBean bean = new CountryBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CountryTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final CountryBean country = new CountryBean();
            country.setAlphaCode(record.getItemProperty("Alpha Code").toString());
            country.setCountryId(new Long(table.getValue().toString()));
            country.setCountryName(record.getItemProperty("Country Name").toString());
            country.setNumericCode(Integer.parseInt(record.getItemProperty("Numeric Code").getValue().toString()));
            if (country != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(country);
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
            saveNewCountry(form);
            main.mainView.setSecondComponent(new LocationMenuView(main,"COUNTRY"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main,"COUNTRY"));
        } else if (source == cf.getUpdate()) {
            saveEditedCountry(form);

            main.mainView.setSecondComponent(new LocationMenuView(main,"COUNTRY"));

        } else if (source == cf.getDelete()) {
            deleteCountry(form);
            main.mainView.setSecondComponent(new LocationMenuView(main,"COUNTRY"));

        }

    }

    public void saveNewCountry(Form form) {
        
        final String countryName = form.getField("countryName").getValue().toString();
        final String alphaCode = form.getField("alphaCode").getValue().toString();
        final int numericCode = Integer.parseInt(form.getField("numericCode").getValue().toString());
        final Country c = factory.createCountry(countryName, alphaCode, numericCode);
        data.getCountryService().persist(c);

    }

    public void saveEditedCountry(Form form) {
       
        final String countryName = form.getField("countryName").getValue().toString();
        final String alphaCode = form.getField("alphaCode").getValue().toString();
        final int numericCode = Integer.parseInt(form.getField("numericCode").getValue().toString());
        final Long countryId = Long.parseLong(form.getField("countryId").getValue().toString());
        final Country c = factory.updatedCountry(countryName, alphaCode, numericCode, countryId);
        data.getCountryService().merge(c);
    }

    public void deleteCountry(Form form) {
      
        final Long countryId = Long.parseLong(form.getField("countryId").getValue().toString());
        final Country c = factory.loadCountry(countryId);
        data.getCountryService().remove(c);
    }
}
