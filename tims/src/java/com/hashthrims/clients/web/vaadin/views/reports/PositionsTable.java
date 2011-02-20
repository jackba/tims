/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.reports;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.positions.Positions;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PositionsTable extends Table {

       private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public PositionsTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Tittle", String.class, null);
        addContainerProperty("Position Code", String.class, null);
        addContainerProperty("Position  Type", String.class, null);
        addContainerProperty("Status", String.class, null);
        addContainerProperty("Post Date", String.class, null);
        addContainerProperty("Salary ", String.class, null);
        addContainerProperty("Facility", String.class, null);
        addContainerProperty("Department", String.class, null);
        // Add Data Columns
        List<Positions> positions = data.getPositionsService().findAll();
        for (Positions position : positions) {
           // addItem(new Object[]{position.getPositionTitle(), position.getPositionCode(), position.getPosType(),position.isAvailable(),position.getPostdate(),position.getFacililty(),position.getDepartment()}, position.getId());
        }



        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setStyleName(ALIGN_LEFT);


    }
}
