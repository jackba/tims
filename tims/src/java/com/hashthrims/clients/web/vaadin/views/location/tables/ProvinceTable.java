/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.Province;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ProvinceTable extends Table {

    private ClientDataService data;
    private HashThrimsMain main;

    public ProvinceTable(HashThrimsMain app) {
        data = new ClientDataService();
        this.main = app;
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Province Name", String.class, null);
        addContainerProperty("Province Code", String.class, null);
        addContainerProperty("Country", String.class, null);
        // Add Data Columns
        List<Province> provinces = data.getRegionService().findAll();

        if (provinces.size() > 0) {
            for (Province province : provinces) {
                if (province.getCountry() != null) {
                    addItem(new Object[]{province.getRegionName(), province.getRegionCode(), province.getCountry().getCountryName()}, province.getId());
                } else {
                    addItem(new Object[]{province.getRegionName(), province.getRegionCode(), "NEW VALUE"}, province.getId());
                }
            }
        }
        setNullSelectionAllowed(false);
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
