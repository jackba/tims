/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.RaceList;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class RaceListTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public RaceListTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Race", String.class, null);

        // Add Data Columns
        List<RaceList> raceLists = data.getRaceListService().findAll();
        for (RaceList raceList : raceLists) {
            addItem(new Object[]{raceList.getRaceName()}, raceList.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
