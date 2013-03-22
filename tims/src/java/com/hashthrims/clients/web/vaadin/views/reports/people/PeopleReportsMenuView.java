/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.reports.EmployeesTable;
import com.hashthrims.clients.web.vaadin.views.reports.people.views.MentoredPeopleViewPage;
import com.hashthrims.clients.web.vaadin.views.reports.people.views.NimartPeopleViewPage;
import com.hashthrims.clients.web.vaadin.views.reports.people.views.TrainedPeopleViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class PeopleReportsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;
    private EmployeesTable empTable;

    public PeopleReportsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;
        //  main.setTheme(getStyleName());

        VerticalLayout trainedTabs = new VerticalLayout();
        trainedTabs.setMargin(true);
        trainedTabs.addComponent(new TrainedPeopleViewPage(main));

        VerticalLayout mentoredTabs = new VerticalLayout();
        mentoredTabs.setMargin(true);
        mentoredTabs.addComponent(new MentoredPeopleViewPage(main));

        VerticalLayout nimartTabs = new VerticalLayout();
        nimartTabs.setMargin(true);
        nimartTabs.addComponent(new NimartPeopleViewPage(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(trainedTabs, "Trained People REPORTS", null);
        tab.addTab(mentoredTabs, "Mentored People REPORTS", null);
        tab.addTab(nimartTabs, "Nimart People REPORTS", null);

        if (selectedTab.equals("TRAINED")) {
            tab.setSelectedTab(trainedTabs);
        } else if (selectedTab.equals("MENTORED")) {
            tab.setSelectedTab(mentoredTabs);
        } else if (selectedTab.equals("NIMART")) {
            tab.setSelectedTab(nimartTabs);
        }
        addComponent(tab);
    }
}
