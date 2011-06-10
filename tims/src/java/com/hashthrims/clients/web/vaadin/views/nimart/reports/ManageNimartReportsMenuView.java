/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.nimart.reports;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.nimart.reports.views.NimartFacilityReportViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.reports.views.NimartMenteeReviewViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.reports.views.NimartReportsViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageNimartReportsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageNimartReportsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

       final  VerticalLayout nimartReportsTab = new VerticalLayout();
        nimartReportsTab.setMargin(true);
        nimartReportsTab.addComponent(new NimartReportsViewPage(main));

        final VerticalLayout nimartFacilityReportTab = new VerticalLayout();
        nimartFacilityReportTab.setMargin(true);
        nimartFacilityReportTab.addComponent(new NimartFacilityReportViewPage(main));
        
        final VerticalLayout nimartMenteeReport = new VerticalLayout();
        nimartMenteeReport.setMargin(true);
        nimartMenteeReport.addComponent(new NimartMenteeReviewViewPage(main));

      
        

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(nimartReportsTab, "Nimart Reports", null);
        tab.addTab(nimartMenteeReport, "Nimart Facility Reports ", null);
        tab.addTab(nimartMenteeReport, "Mentee Review Report", null);
       

        if (selectedTab.equals("REPO")) {
            tab.setSelectedTab(nimartReportsTab);
        } else if (selectedTab.equals("FAC")) {
            tab.setSelectedTab(nimartFacilityReportTab);
        }else if (selectedTab.equals("MENT")) {
            tab.setSelectedTab(nimartMenteeReport);
        }
        addComponent(tab);
    }
}
