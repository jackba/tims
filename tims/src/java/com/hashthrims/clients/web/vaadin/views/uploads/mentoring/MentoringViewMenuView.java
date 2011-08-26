/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.mentoring;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;


import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views.MentoringAreasUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views.MentoringObjectUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views.MentoringSessionTypeUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views.MentoringSessionsUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views.MentoringSpreadSheetCodesViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views.MentoringThemeUploadsViewPage;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class MentoringViewMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public MentoringViewMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout mentoringSessionType = new VerticalLayout();
        mentoringSessionType.setMargin(true);
        mentoringSessionType.addComponent(new MentoringSessionTypeUploadsViewPage(main));

        final VerticalLayout mentoringThemeTab = new VerticalLayout();
        mentoringThemeTab.setMargin(true);
        mentoringThemeTab.addComponent(new MentoringThemeUploadsViewPage(main));

        final VerticalLayout codeSpreadSheetTab = new VerticalLayout();
        codeSpreadSheetTab.setMargin(true);
        codeSpreadSheetTab.addComponent(new MentoringSpreadSheetCodesViewPage(main));


        final VerticalLayout mentoringObjective = new VerticalLayout();
        mentoringObjective.setMargin(true);
        mentoringObjective.addComponent(new MentoringObjectUploadsViewPage(main));

        final VerticalLayout mentoringSessions = new VerticalLayout();
        mentoringSessions.setMargin(true);
        mentoringSessions.addComponent(new MentoringSessionsUploadsViewPage(main));

        final VerticalLayout mentoringAreasTab = new VerticalLayout();
        mentoringAreasTab.setMargin(true);
        mentoringAreasTab.addComponent(new MentoringAreasUploadsViewPage(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(mentoringThemeTab, "Mentoring Theme", null);
        tab.addTab(mentoringSessionType, "Mentorinmg Session Type", null);
        tab.addTab(mentoringObjective, "Mentoring Objective", null);
        tab.addTab(mentoringAreasTab, "Areas of Mentoring", null);
        tab.addTab(mentoringSessions, "Mentoring Sessions", null);
        tab.addTab(codeSpreadSheetTab, "Spread Sheet Codes", null);




        if (selectedTab.equals("MENT")) {
            tab.setSelectedTab(mentoringThemeTab);
        } else if (selectedTab.equals("THEME")) {
            tab.setSelectedTab(mentoringSessionType);
        } else if (selectedTab.equals("SESSION")) {
            tab.setSelectedTab(codeSpreadSheetTab);
        } else if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(mentoringObjective);
        } else if (selectedTab.equals("ST")) {
            tab.setSelectedTab(mentoringSessions);
        } else if (selectedTab.equals("OBJ")) {
            tab.setSelectedTab(mentoringAreasTab);
        }
        addComponent(tab);
    }
}
