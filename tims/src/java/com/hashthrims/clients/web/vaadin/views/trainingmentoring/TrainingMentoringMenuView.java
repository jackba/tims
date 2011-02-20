/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.views.MentoringSessionTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.views.MentoringSessionViewPage;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.views.MentoringThemeViewPage;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.views.MentorsViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class TrainingMentoringMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public TrainingMentoringMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout themesTab = new VerticalLayout();
        themesTab.setMargin(true);
        themesTab.addComponent(new MentoringThemeViewPage(main));

        final VerticalLayout mentorsTab = new VerticalLayout();
        mentorsTab.setMargin(true);
        mentorsTab.addComponent(new MentorsViewPage(main));

        final VerticalLayout sessionTab = new VerticalLayout();
        sessionTab.setMargin(true);
        sessionTab.addComponent(new MentoringSessionViewPage(main));


        final VerticalLayout typeTab = new VerticalLayout();
        typeTab.setMargin(true);
        typeTab.addComponent(new MentoringSessionTypeViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(mentorsTab, "Mentors", null);
        tab.addTab(themesTab, "Add Theme", null);
        tab.addTab(typeTab, "Mentoring Session Type", null);
        tab.addTab(sessionTab, "Add Session", null);
        


        if (selectedTab.equals("MENTORS")) {
            tab.setSelectedTab(mentorsTab);
        } else if (selectedTab.equals("THEME")) {
            tab.setSelectedTab(themesTab);
        } else if (selectedTab.equals("SESSION")) {
            tab.setSelectedTab(sessionTab);
        } else if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(typeTab);
        }
        addComponent(tab);
    }
}
