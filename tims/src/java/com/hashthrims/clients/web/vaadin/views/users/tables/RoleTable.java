/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.users.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Roles;
import com.hashthrims.domain.Users;
import com.vaadin.ui.*;
import java.util.List;
/**
 *
 * @author abismail
 */
public class RoleTable extends Table{
 private static ClientDataService data = new ClientDataService();
private Long id;
private Roles role;
    private HashThrimsMain main;

    public RoleTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("FirstName", String.class, null);
        addContainerProperty("Middle Name(s) ", String.class, null);
        addContainerProperty("Last Name ", String.class, null);
        addContainerProperty("Username ", String.class, null);
        addContainerProperty("Role  ", String.class, null);
        // Add Data Columns
        List<Users> countries = data.getUsersService().findAll();
        for (Users country : countries) {
            id = ((Roles)country.getRoles()).getId();
            role = data.getRolesService().find(id);
            addItem(new Object[]{country.getFirstname(), country.getMiddlename(), country.getLastname(), country.getEmail(), role.getRoleName()}, country.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
