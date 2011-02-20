/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.City;
import com.vaadin.addon.chameleon.ChameleonTheme;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CityTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CityTable(HashThrimsMain app) {
        this.main = app;
        addStyleName(ChameleonTheme.TABLE_STRIPED);

        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("City Name", String.class, null);
        addContainerProperty("City Code", String.class, null);
        addContainerProperty("District", String.class, null);
        // Add Data Columns
        List<City> cities = data.getCityService().findAll();
        for (City city : cities) {
            if (city.getDistrict() != null) {
                addItem(new Object[]{city.getName(), city.getCityCode(), city.getDistrict().getDistrictName()}, city.getId());
            } else {
                addItem(new Object[]{city.getName(), city.getCityCode(), "NEW VALUE"}, city.getId());
            }
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
