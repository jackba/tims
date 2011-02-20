/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.FacilityType;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class FacilityTypeTable extends Table {

    private final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public FacilityTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Facility Type Name", String.class, null);
       
        // Add Data Columns
        List<FacilityType> facilityTypes = data.getFacilityTypeService().findAll();
        for (FacilityType facilityType : facilityTypes) {
            addItem(new Object[]{facilityType.getFacilityName()}, facilityType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
