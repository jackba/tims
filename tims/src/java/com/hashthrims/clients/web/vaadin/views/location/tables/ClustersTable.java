/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Clusters;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ClustersTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public ClustersTable(HashThrimsMain app) {
        this.main = app;
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Cluster Name", String.class, null);
       
        // Add Data Columns
        List<Clusters> addTypes = data.getClustersService().findAll();
        for (Clusters Clusters : addTypes) {
            addItem(new Object[]{Clusters.getClusterName()}, Clusters.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
