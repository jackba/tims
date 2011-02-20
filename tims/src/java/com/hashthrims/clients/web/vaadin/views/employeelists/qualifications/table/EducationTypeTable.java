/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.EducationType;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EducationTypeTable extends Table {

   private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public EducationTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Education Type", String.class, null);
       
        // Add Data Columns
        List<EducationType> edtyp = data.getEducationTypeService().findAll();
        for (EducationType et : edtyp) {
            addItem(new Object[]{et.getEduc_type_name()},et.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
