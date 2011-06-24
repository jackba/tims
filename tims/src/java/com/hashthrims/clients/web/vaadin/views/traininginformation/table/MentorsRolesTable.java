/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.MentorsRoles;
import java.util.List;

/**
 *
 * @author stud
 */
public class MentorsRolesTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public MentorsRolesTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Mentors Roles", String.class, null);
        
        // Add Data Columns
        List<MentorsRoles> fields = data.getMentorsRolesService().findAll();
        for (MentorsRoles field : fields) {
            addItem(new Object[]{field.getMentorsRolesName()}, field.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
