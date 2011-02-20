/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.IdentificationType;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class IdentificationTypeTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public IdentificationTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Identification Type", String.class, null);
        // Add Data Columns
        List<IdentificationType> identificationType = data.getIdentificationTypeService().findAll();
        for (IdentificationType idType : identificationType) {
            addItem(new Object[]{idType.getIdentity_name_type()}, idType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
