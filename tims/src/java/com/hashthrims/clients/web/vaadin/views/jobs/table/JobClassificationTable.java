/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.Classifications;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class JobClassificationTable extends Table {

     private static final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public JobClassificationTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Description", String.class, null);
        addContainerProperty("Code", String.class, null);
        // Add Data Columns
        List<Classifications> jobClass = data.getclassificationsServive().findAll();
        for (Classifications classifications : jobClass) {
            addItem(new Object[]{classifications.getJob_name(),
                                 classifications.getJob_desc(),
                                 classifications.getJob_code() }, classifications.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
