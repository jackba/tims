/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.CurrencyBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.CurrencyForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.CurrencyTable;
import com.hashthrims.domain.regionlist.Currency;
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
public class CurrencyViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final CurrencyForm cf;
    private final ClientDataService data = new ClientDataService();
    private final CurrencyTable table;
    private final LocationFactory factory = data.getClientDataFactory();

    public CurrencyViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new CurrencyForm();
        form = cf.createCurrencyFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final CurrencyBean bean = new CurrencyBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CurrencyTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final CurrencyBean currency = new CurrencyBean();
            currency.setCurrencyName(record.getItemProperty("Currency Name").toString());
            currency.setCurrencyId(new Long(table.getValue().toString()));
            currency.setCurrencySymbol(record.getItemProperty("Currency Symbol").toString());
            currency.setCurrencyCode(record.getItemProperty("Currency Code").getValue().toString());
            if (currency != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(currency);
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
            saveNewCurrency(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "CURRENCY"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main, "CURRENCY"));
        } else if (source == cf.getUpdate()) {
            saveEditedCurrency(form);

            main.mainView.setSecondComponent(new LocationMenuView(main, "CURRENCY"));

        } else if (source == cf.getDelete()) {
            deleteCurrency(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "CURRENCY"));

        }

    }

    public void saveNewCurrency(Form form) {

        final String currencyName = form.getField("currencyName").getValue().toString();
        final String currencySymbol = form.getField("currencySymbol").getValue().toString();
        final String currencyCode = form.getField("currencyCode").getValue().toString();
        final Currency c = factory.createCurrency(currencyName, currencySymbol, currencyCode);
        data.getCurrencyService().persist(c);

    }

    public void saveEditedCurrency(Form form) {

        final String currencyName = form.getField("currencyName").getValue().toString();
        final String currencySymbol = form.getField("currencySymbol").getValue().toString();
        final String currencyCode = form.getField("currencyCode").getValue().toString();
        final Long currencyId = Long.parseLong(form.getField("currencyId").getValue().toString());
        final Currency c = factory.updatedCurrency(currencyName, currencySymbol, currencyCode, currencyId);
        data.getCurrencyService().merge(c);
    }

    public void deleteCurrency(Form form) {

        final Long currencyId = Long.parseLong(form.getField("currencyId").getValue().toString());
        final Currency c = factory.loadCurrency(currencyId);
        data.getCurrencyService().remove(c);
    }
}
