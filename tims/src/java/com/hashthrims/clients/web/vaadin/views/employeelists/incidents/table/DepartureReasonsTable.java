/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.DepartureReasons;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DepartureReasonsTable extends Table {

   private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public DepartureReasonsTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        addContainerProperty("Departure Reasons", String.class, null);

        List<DepartureReasons> depre = data.getDepartureReasonsService().findAll();
        for (DepartureReasons depart : depre) {
            addItem(new Object[]{depart.getReason_name()},depart.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
