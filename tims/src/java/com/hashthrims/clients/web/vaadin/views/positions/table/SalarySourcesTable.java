/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.positions.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.positions.SalarySources;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class SalarySourcesTable extends Table {

     private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public SalarySourcesTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Salary Source Name", String.class, null);
                // Add Data Columns
        List<SalarySources> salarySource = data.getSalarySourcesService().findAll();
        for (SalarySources salSource : salarySource) {
            if (salSource.getSalSourceName()!=null) {
                addItem(new Object[]{salSource.getSalSourceName()}, salSource.getId());
            } else {
                 addItem(new Object[]{salSource.getSalSourceName(), "NEW VALUE"}, salSource.getId());
            }
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
