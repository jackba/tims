/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.positions.PositionTypes;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PositionTypeTable extends Table {

    private static final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public PositionTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Position Type", String.class, null);
        // Add Data Columns
        List<PositionTypes> positionType = data.getPositionTypes().findAll();
        for (PositionTypes posType : positionType) {
            if (posType.getPosTypeName() != null) {
                addItem(new Object[]{posType.getPosTypeName()}, posType.getId());
            } else {
                addItem(new Object[]{posType.getPosTypeName(), "NEW VALUE"}, posType.getId());
            }
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
