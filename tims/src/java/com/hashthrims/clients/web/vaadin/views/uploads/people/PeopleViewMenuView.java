/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.people;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;


import com.hashthrims.clients.web.vaadin.views.uploads.people.views.AddPeopleSpreadSheetCodesViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.people.views.AddPeopleUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.people.views.AssignPositionsUploadsViewPage;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class PeopleViewMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public PeopleViewMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout addPeopleTab = new VerticalLayout();
        addPeopleTab.setMargin(true);
        addPeopleTab.addComponent(new AddPeopleUploadsViewPage(main));



        final VerticalLayout assignPositionsTab = new VerticalLayout();
        assignPositionsTab.setMargin(true);
        assignPositionsTab.addComponent(new AssignPositionsUploadsViewPage(main));



        final VerticalLayout spreadSheetCodeTabs = new VerticalLayout();
        spreadSheetCodeTabs.setMargin(true);
        spreadSheetCodeTabs.addComponent(new AddPeopleSpreadSheetCodesViewPage(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");


        tab.addTab(addPeopleTab, "Add People", null);
        tab.addTab(assignPositionsTab, "Position Codes", null);
        tab.addTab(spreadSheetCodeTabs, "Spread Sheet Codes", null);

        if (selectedTab.equals("PEOPLE")) {
            tab.setSelectedTab(addPeopleTab);
        } else if (selectedTab.equals("POSITIONS")) {
            tab.setSelectedTab(assignPositionsTab);
        } else if (selectedTab.equals("SHEET")) {
            tab.setSelectedTab(spreadSheetCodeTabs);
        }
        addComponent(tab);
    }
}
