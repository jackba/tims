/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Nodes;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class NodesTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public NodesTable(HashThrimsMain app) {
        this.main = app;
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Node Name", String.class, null);
       
        // Add Data Columns
        List<Nodes> addTypes = data.getNodesService().findAll();
        for (Nodes Nodes : addTypes) {
            addItem(new Object[]{Nodes.getNodesName()}, Nodes.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
