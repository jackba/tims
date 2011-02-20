/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DisciplinaryActionListTable extends Table {

   private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public DisciplinaryActionListTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Disciplinary Action List", String.class, null);


        // Add Data Columns
        List<DisciplineActionTypeList> list = data.getDisciplineActionType().findAll();
        for (DisciplineActionTypeList dp : list) {
            addItem(new Object[]{dp.getDisplineName() }, dp.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
