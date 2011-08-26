/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.competencies;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;


import com.hashthrims.clients.web.vaadin.views.uploads.competencies.views.CompetenciesCodesUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.competencies.views.CompetenciesUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.competencies.views.SubjectAreaUploadsViewPage;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class CompetenciesViewMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public CompetenciesViewMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout subjectAreaTabs = new VerticalLayout();
        subjectAreaTabs.setMargin(true);
        subjectAreaTabs.addComponent(new SubjectAreaUploadsViewPage(main));


        final VerticalLayout competenciesTab = new VerticalLayout();
        competenciesTab.setMargin(true);
        competenciesTab.addComponent(new CompetenciesUploadsViewPage(main));

        final VerticalLayout spreadSheetTabs = new VerticalLayout();
        spreadSheetTabs.setMargin(true);
        spreadSheetTabs.addComponent(new CompetenciesCodesUploadsViewPage(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(subjectAreaTabs, "Subject Areas", null);
        tab.addTab(competenciesTab, "Competencies", null);
        tab.addTab(spreadSheetTabs, "Spread Sheet Codes", null);




        if (selectedTab.equals("COMP")) {
            tab.setSelectedTab(competenciesTab);
        } else if (selectedTab.equals("SUBJ")) {
            tab.setSelectedTab(subjectAreaTabs);
        } else if (selectedTab.equals("CODE")) {
            tab.setSelectedTab(spreadSheetTabs);
        } 
        addComponent(tab);
    }
}
