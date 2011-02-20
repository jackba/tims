/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.positions.Status;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class StatusTable extends Table {

    private static final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public StatusTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Status", String.class, null);
        // Add Data Columns
        List<Status> statuses = data.getStatusService().findAll();
        for (Status status : statuses) {
            if (status.getStatus() != null) {
                addItem(new Object[]{status.getStatus()}, status.getId());
            } else {
                addItem(new Object[]{status.getStatus(), "NEW VALUE"}, status.getId());
            }
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
