/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.County;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CountyTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CountyTable(HashThrimsMain app) {
        this.main = app;
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("District Name", String.class, null);
        addContainerProperty("District Code", String.class, null);
        addContainerProperty("Province", String.class, null);
        // Add Data Columns
        List<County> counties = data.getCountyService().findAll();
        for (County county : counties) {
            if (county.getProvince() != null) {
                addItem(new Object[]{county.getCountyName(), county.getCountyCode(), county.getProvince().getRegionName()}, county.getId());
            } else {
                addItem(new Object[]{county.getCountyName(), county.getCountyCode(), "NEW VALUE"}, county.getId());
            }
        }



        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
