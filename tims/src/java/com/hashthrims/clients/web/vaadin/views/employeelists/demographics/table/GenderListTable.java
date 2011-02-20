/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.GenderList;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class GenderListTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public GenderListTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Gender", String.class, null);

        // Add Data Columns
        List<GenderList> genderLists = data.getGenderListService().findAll();
        for (GenderList genderList : genderLists) {
            addItem(new Object[]{genderList.getGender()}, genderList.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
