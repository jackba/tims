/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.District;
import com.vaadin.addon.chameleon.ChameleonTheme;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DistrictTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public DistrictTable(HashThrimsMain app) {
        this.main = app;
        addStyleName(ChameleonTheme.TABLE_STRIPED);
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Sub District Name", String.class, null);
        addContainerProperty("Sub District Code", String.class, null);
        addContainerProperty("District", String.class, null);
        // Add Data Columns
        List<District> districts = data.getDistrictService().findAll();
        for (District district : districts) {
            if (district.getCounty() != null) {
                addItem(new Object[]{district.getDistrictName(), district.getDistrictCode(), district.getCounty().getCountyName()}, district.getId());
            } else {
                addItem(new Object[]{district.getDistrictName(), district.getDistrictCode(), "NE VALUE"}, district.getId());
            }
        }



        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
