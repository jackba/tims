/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.facilites.views.DepartmentViewPage;
import com.hashthrims.clients.web.vaadin.views.facilites.views.FacilityMentorsViewPage;
import com.hashthrims.clients.web.vaadin.views.facilites.views.FacilityNodeClusterViewPAge;
import com.hashthrims.clients.web.vaadin.views.facilites.views.FacilityTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.facilites.views.FacilityViewPage;
import com.hashthrims.clients.web.vaadin.views.facilites.views.RegistrationBodyViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class OrganisationListMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public OrganisationListMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout departmenttab = new VerticalLayout();
        departmenttab.setMargin(true);
        departmenttab.addComponent(new DepartmentViewPage(main));

        final VerticalLayout facilityTab = new VerticalLayout();
        facilityTab.setMargin(true);
        facilityTab.addComponent(new FacilityViewPage(main));

        final VerticalLayout facilityTypeTab = new VerticalLayout();
        facilityTypeTab.setMargin(true);
        facilityTypeTab.addComponent(new FacilityTypeViewPage(main));

        final VerticalLayout registrationBodyTab = new VerticalLayout();
        registrationBodyTab.setMargin(true);
        registrationBodyTab.addComponent(new RegistrationBodyViewPage(app));

        final VerticalLayout nodeClusterTab = new VerticalLayout();
        nodeClusterTab.setMargin(true);
        nodeClusterTab.addComponent(new FacilityNodeClusterViewPAge(app));


        final VerticalLayout mentorsTab = new VerticalLayout();
        mentorsTab.setMargin(true);
        mentorsTab.addComponent(new FacilityMentorsViewPage(app));





        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(departmenttab, "Department", null);
        tab.addTab(facilityTypeTab, "FacilityType", null);
        tab.addTab(facilityTab, "Facility", null);

        tab.addTab(registrationBodyTab, "Registration Body", null);
        tab.addTab(nodeClusterTab, "Add Node and Cluster", null);
        tab.addTab(mentorsTab, "Add Facility Mentor", null);

        if (selectedTab.equals("DEPARTMENT")) {
            tab.setSelectedTab(departmenttab);
        } else if (selectedTab.equals("FACILITY")) {
            tab.setSelectedTab(facilityTab);
        } else if (selectedTab.equals("FACILITYTYPE")) {
            tab.setSelectedTab(facilityTypeTab);
        } else if (selectedTab.equals("REGISTRATIONBODY")) {
            tab.setSelectedTab(registrationBodyTab);
        } else if (selectedTab.equals("NODE")) {
            tab.setSelectedTab(nodeClusterTab);
        } else if (selectedTab.equals("MENTORS")) {
            tab.setSelectedTab(mentorsTab);
        }
        addComponent(tab);
    }
}
