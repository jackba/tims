/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.nimart.mentors;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.nimart.mentors.views.AssignMenteesViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.mentors.views.ListMenteesViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.mentors.views.LogBookViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageNimartMentorsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageNimartMentorsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout assignTab = new VerticalLayout();
        assignTab.setMargin(true);
        assignTab.addComponent(new AssignMenteesViewPage(main));

        final VerticalLayout listMenteesTab = new VerticalLayout();
        listMenteesTab.setMargin(true);
        listMenteesTab.addComponent(new ListMenteesViewPage(main));

        final VerticalLayout logBookTab = new VerticalLayout();
        logBookTab.setMargin(true);
        logBookTab.addComponent(new LogBookViewPage(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(assignTab, "Assign Mentees", null);
        tab.addTab(listMenteesTab, "List of Mentees", null);
        tab.addTab(logBookTab, "Mentee's Log Book", null);


        if (selectedTab.equals("ASSN")) {
            tab.setSelectedTab(assignTab);
        } else if (selectedTab.equals("LIST")) {
            tab.setSelectedTab(listMenteesTab);
        } else if (selectedTab.equals("LOG")) {
            tab.setSelectedTab(logBookTab);
        }
        addComponent(tab);
    }
}
