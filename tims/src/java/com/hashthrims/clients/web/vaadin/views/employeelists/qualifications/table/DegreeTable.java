/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.Degree;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DegreeTable extends Table {

   private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public DegreeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.

        addContainerProperty("Degree Name", String.class, null);
        addContainerProperty("Education Type", String.class, null);
       
        // Add Data Columns
        List<Degree> deg = data.getDegreeName().findAll();
        for (Degree degre : deg) {
            addItem(new Object[]{degre.getDegree_name(), degre.getEducType().getEduc_type_name()},degre.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
