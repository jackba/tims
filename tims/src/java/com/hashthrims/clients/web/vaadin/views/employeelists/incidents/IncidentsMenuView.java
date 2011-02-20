/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.views.AccidentTypeListViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.views.DepartureReasonsViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.views.DisciplinaryActionViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class IncidentsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public IncidentsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout departTab = new VerticalLayout();
        departTab.setMargin(true);
        departTab.addComponent(new DepartureReasonsViewPage(main));


        VerticalLayout accTab = new VerticalLayout();
        accTab.setMargin(true);
        accTab.addComponent(new AccidentTypeListViewPage(main));
       
        VerticalLayout disciplinaryActionTab = new VerticalLayout();
        disciplinaryActionTab.setMargin(true);
        disciplinaryActionTab.addComponent(new DisciplinaryActionViewPage(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

       
        tab.addTab(accTab, "Accident Type List ", null);
        tab.addTab(departTab, "Departure Reason", null);
        tab.addTab(disciplinaryActionTab, "Disciplinary Action Type", null);
 

        if (selectedTab.equals("DEPARTURE REASONS")) {
            tab.setSelectedTab(departTab);
        }  else if (selectedTab.equals("DISCIPLINARY ACTION TYPE")) {
            tab.setSelectedTab(disciplinaryActionTab);
        } else if (selectedTab.equals("ACCIDENT TYPE LIST")) {
            tab.setSelectedTab(accTab);
        }
        addComponent(tab);
    }
}
