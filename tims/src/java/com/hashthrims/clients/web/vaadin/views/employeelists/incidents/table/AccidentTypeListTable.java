/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class AccidentTypeListTable extends Table {

    private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public AccidentTypeListTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Accident Name", String.class, null);
        
        // Add Data Columns
        List<AccidentTypeList> accType = data.getAccidentType().findAll();
        for (AccidentTypeList act : accType) {
            addItem(new Object[]{act.getAccidentName()}, act.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
