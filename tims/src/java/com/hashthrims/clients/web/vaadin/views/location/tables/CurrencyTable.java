/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.Currency;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CurrencyTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CurrencyTable(HashThrimsMain app) {
        this.main = app;
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Currency Name", String.class, null);
        addContainerProperty("Currency Symbol", String.class, null);
        addContainerProperty("Currency Code", String.class, null);
        // Add Data Columns
        List<Currency> currencies = data.getCurrencyService().findAll();
        for (Currency currency : currencies) {
            addItem(new Object[]{currency.getCurrencyName(), currency.getCurrencySymbol(), currency.getCurrencyCode()}, currency.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
