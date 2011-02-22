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
public class UserTable extends Table {

    private static ClientDataService data = new ClientDataService();
    private Long id;
    private HashThrimsMain main;

    public UserTable(HashThrimsMain app) {
        this.main = app;
        setSizeFull();
        addContainerProperty("FirstName", String.class, null);
        addContainerProperty("Middle Name(s)", String.class, null);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("Username", String.class, null);
        addContainerProperty("Roles", String.class, null);
        // Add Data Columns
        List<Users> users = data.getUsersService().findAll();
        for (Users user : users) {
            addItem(new Object[]{user.getFirstname(),
                        user.getMiddlename(),
                        user.getLastname(),
                        user.getEmail(),
                        (getRoles(user.getRoles()))}, user.getId());
        }

        setNullSelectionAllowed(false);

        setSelectable(true);
        setImmediate(true);
    }

    private String getRoles(List<Roles> roles) {
        final StringBuilder st = new StringBuilder();
        if (roles!=null) {
            for (Roles role : roles) {
                st.append(" ");
                st.append(role.getRoleName());
            }
        }
        return st.toString();
    }
}
