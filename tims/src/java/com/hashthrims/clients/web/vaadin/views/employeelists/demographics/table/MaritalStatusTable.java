/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class MaritalStatusTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public MaritalStatusTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        addContainerProperty("Marital Status", String.class, null);

        List<MaritalStatusList> maritalStatus = data.getMaritalStatusListService().findAll();
        for (MaritalStatusList maritalStatusList : maritalStatus) {
            addItem(new Object[]{maritalStatusList.getStatus_name()}, maritalStatusList.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
