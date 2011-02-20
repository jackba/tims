/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.RegistrationBody;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class RegistrationBodyTable extends Table {

    private final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public RegistrationBodyTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Registration Body Name", String.class, null);
        // Add Data Columns
        List<RegistrationBody> bodies = data.getRegistrationBodyService().findAll();
        for (RegistrationBody body : bodies) {
            addItem(new Object[]{body.getName()}, body.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
