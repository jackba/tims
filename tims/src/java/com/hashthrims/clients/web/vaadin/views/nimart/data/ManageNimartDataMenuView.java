/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.nimart.data;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.nimart.data.views.CategoryViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.data.views.MentorsViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.data.views.TasksViewPage;
import com.hashthrims.clients.web.vaadin.views.users.views.RolesViewPage;
import com.hashthrims.clients.web.vaadin.views.users.views.UsersViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageNimartDataMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageNimartDataMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout tasksTab = new VerticalLayout();
        tasksTab.setMargin(true);
        tasksTab.addComponent(new TasksViewPage(main));

        final VerticalLayout categoryTab = new VerticalLayout();
        categoryTab.setMargin(true);
        categoryTab.addComponent(new CategoryViewPage(main));

        final VerticalLayout mentorsTab = new VerticalLayout();
        mentorsTab.setMargin(true);
        mentorsTab.addComponent(new MentorsViewPage(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(categoryTab, "Competence Category", null);
        tab.addTab(categoryTab, "Competency Tasks", null);
        tab.addTab(mentorsTab, "NIMART Mentors", null);


        if (selectedTab.equals("CAT")) {
            tab.setSelectedTab(categoryTab);
        } else if (selectedTab.equals("TASKS")) {
            tab.setSelectedTab(tasksTab);
        } else if (selectedTab.equals("MENTORS")) {
            tab.setSelectedTab(mentorsTab);
        }
        addComponent(tab);
    }
}
