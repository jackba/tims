/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.Country;
import com.vaadin.addon.chameleon.ChameleonTheme;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CountryTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CountryTable(HashThrimsMain app) {
        this.main = app;
        addStyleName(ChameleonTheme.TABLE_STRIPED);
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Country Name", String.class, null);
        addContainerProperty("Alpha Code", String.class, null);
        addContainerProperty("Numeric Code", Integer.class, null);
        // Add Data Columns
        List<Country> countries = data.getCountryService().findAll();
        for (Country country : countries) {
            addItem(new Object[]{country.getCountryName(), country.getAlphaCode(), country.getNumericCode()}, country.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
