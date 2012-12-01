/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.ClientMentoringSessionViewPage;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.EnrollMenteesViewPage;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.ReportMentoringViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageMentoringMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageMentoringMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout enrollMenteesTab = new VerticalLayout();
        enrollMenteesTab.setMargin(true);
        enrollMenteesTab.addComponent(new EnrollMenteesViewPage(main));

        VerticalLayout reportMentoringTab = new VerticalLayout();
        reportMentoringTab.setMargin(true);
        reportMentoringTab.addComponent(new ReportMentoringViewPage(main));
        
         final VerticalLayout createMentoringSessionTab = new VerticalLayout();
        createMentoringSessionTab.setMargin(true);
        createMentoringSessionTab.addComponent(new ClientMentoringSessionViewPage(main));
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(createMentoringSessionTab, "Create Mentoring Session", null);
        tab.addTab(enrollMenteesTab, "Enroll Mentees", null);
        tab.addTab(reportMentoringTab, "Report Mentoring Session", null);
        if (selectedTab.equals("CREATE")) {
            tab.setSelectedTab(createMentoringSessionTab);
        } else if (selectedTab.equals("ENROLL")) {
            tab.setSelectedTab(enrollMenteesTab);
        } else if (selectedTab.equals("REPORT")) {
            tab.setSelectedTab(reportMentoringTab);
        }
        addComponent(tab);
    }
}
