/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users;

import com.hashthrims.clients.web.vaadin.views.training.*;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseRequesterViewPage;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseStatusViewPage;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseViewPage;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.users.views.RolesViewPage;
import com.hashthrims.clients.web.vaadin.views.users.views.UsersViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageUsersMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageUsersMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

       final  VerticalLayout systemUsersTab = new VerticalLayout();
        systemUsersTab.setMargin(true);
        systemUsersTab.addComponent(new UsersViewPage(main));

        final VerticalLayout systemRolesTab = new VerticalLayout();
        systemRolesTab.setMargin(true);
        systemRolesTab.addComponent(new RolesViewPage(main));

      

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(systemUsersTab, "Add System Users", null);
        //tab.addTab(systemRolesTab, "Add System Roles", null);
       

        if (selectedTab.equals("USERS")) {
            tab.setSelectedTab(systemUsersTab);
        } else if (selectedTab.equals("ROLES")) {
            tab.setSelectedTab(systemRolesTab);
        }
        addComponent(tab);
    }
}
