/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.courses;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.reports.EmployeeViewPage;
import com.hashthrims.clients.web.vaadin.views.reports.EmployeesTable;
import com.hashthrims.clients.web.vaadin.views.reports.PositionsTable;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class CoursesReportsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;
    private EmployeesTable empTable;

    public CoursesReportsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;
        //  main.setTheme(getStyleName());

        VerticalLayout positionsTab = new VerticalLayout();
        positionsTab.setMargin(true);
        positionsTab.addComponent(new PositionsTable(main));

        VerticalLayout employeesTab = new VerticalLayout();
        employeesTab.setMargin(true);
        employeesTab.addComponent(new EmployeeViewPage(main));

        VerticalLayout facilitiesTab = new VerticalLayout();
        facilitiesTab.setMargin(true);
        // facilitiesTab.addComponent(new FacilityTable(app));

        facilitiesTab.setHeight("100%");
        facilitiesTab.setWidth("500px");
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(positionsTab, "Positions", null);
        tab.addTab(employeesTab, "Employees", null);
        tab.addTab(facilitiesTab, "Facilities", null);

        if (selectedTab.equals("POSITION")) {
            tab.setSelectedTab(positionsTab);
        } else if (selectedTab.equals("EMPLOYEE")) {
            tab.setSelectedTab(employeesTab);
        } else if (selectedTab.equals("FACILITY")) {
            tab.setSelectedTab(facilitiesTab);
        }
        addComponent(tab);
    }
}
