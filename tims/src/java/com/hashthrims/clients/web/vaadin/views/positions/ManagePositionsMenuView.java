/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;

import com.hashthrims.clients.web.vaadin.views.positions.views.PositionsTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.positions.views.PositionsViewPage;
import com.hashthrims.clients.web.vaadin.views.positions.views.SalarySourcesViewPage;
import com.hashthrims.clients.web.vaadin.views.positions.views.StatusViewPage;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author GrimmTech
 */
public class ManagePositionsMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public ManagePositionsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout positionsTab = new VerticalLayout();
        positionsTab.setMargin(true);
        positionsTab.addComponent(new PositionsViewPage(main));

        final VerticalLayout statusTab = new VerticalLayout();
        statusTab.setMargin(true);
        statusTab.addComponent(new StatusViewPage(main));

        final VerticalLayout positionsTypeTab = new VerticalLayout();
        positionsTypeTab.setMargin(true);
        positionsTypeTab.addComponent(new PositionsTypeViewPage(main));

        final VerticalLayout salarySourcesTab = new VerticalLayout();
        salarySourcesTab.setMargin(true);
        salarySourcesTab.addComponent(new SalarySourcesViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        // tab.addTab(salarySourcesTab, "Salary Sources", null);
        tab.addTab(statusTab, "Status", null);
        tab.addTab(positionsTypeTab, "Position Type", null);
        tab.addTab(positionsTab, "Positions", null);
        if (selectedTab.equals("SOURCE")) {
            tab.setSelectedTab(salarySourcesTab);
        } else if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(positionsTypeTab);
        } else if (selectedTab.equals("POSITION")) {
            tab.setSelectedTab(positionsTab);
        } else if (selectedTab.equals("STATUS")) {
            tab.setSelectedTab(statusTab);
        }

        addComponent(tab);

    }
}
