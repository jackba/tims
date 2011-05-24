/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.positions.views.GlobalFacilityPositionsViewPage;
import com.hashthrims.clients.web.vaadin.views.positions.views.GlobalPositionsViewPage;

import com.hashthrims.clients.web.vaadin.views.positions.views.PositionsTypeViewPage;
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

        final VerticalLayout statusTab = new VerticalLayout();
        statusTab.setMargin(true);
        statusTab.addComponent(new StatusViewPage(main));

        final VerticalLayout positionsTypeTab = new VerticalLayout();
        positionsTypeTab.setMargin(true);
        positionsTypeTab.addComponent(new PositionsTypeViewPage(main));

        final VerticalLayout globalPositionsTab = new VerticalLayout();
        globalPositionsTab.setMargin(true);
        globalPositionsTab.addComponent(new GlobalPositionsViewPage(main));

        final VerticalLayout globalFacilitiesTab = new VerticalLayout();
        globalFacilitiesTab.setMargin(true);
        globalFacilitiesTab.addComponent(new GlobalFacilityPositionsViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");



        tab.addTab(globalPositionsTab, "Positions", null);
        tab.addTab(globalFacilitiesTab, "Facility Positions", null);
        tab.addTab(positionsTypeTab, "Position Type", null);
        tab.addTab(statusTab, "Status", null);
         if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(positionsTypeTab);
        } else if (selectedTab.equals("STATUS")) {
            tab.setSelectedTab(statusTab);
        } else if (selectedTab.equals("GLOBAL")) {
            tab.setSelectedTab(globalPositionsTab);
        } else if (selectedTab.equals("FACILITIES")) {
            tab.setSelectedTab(globalFacilitiesTab);
        }

        addComponent(tab);

    }
}
