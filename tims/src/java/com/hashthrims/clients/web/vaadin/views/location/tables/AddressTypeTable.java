/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.AddressType;
import com.vaadin.addon.chameleon.ChameleonTheme;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class AddressTypeTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public AddressTypeTable(HashThrimsMain app) {
        this.main = app;
        addStyleName(ChameleonTheme.TABLE_STRIPED);
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Address Type Name", String.class, null);
       
        // Add Data Columns
        List<AddressType> addTypes = data.getAddressTypeService().findAll();
        for (AddressType AddressType : addTypes) {
            addItem(new Object[]{AddressType.getAddressTypeName()}, AddressType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
