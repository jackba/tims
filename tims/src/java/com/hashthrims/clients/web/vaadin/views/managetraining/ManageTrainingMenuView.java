/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.managetraining.views.EvalauteTrainingViewPage;
import com.hashthrims.clients.web.vaadin.views.managetraining.views.ManageTrainingViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageTrainingMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageTrainingMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout scheduleTab = new VerticalLayout();
        scheduleTab.setMargin(true);
        scheduleTab.addComponent(new ManageTrainingViewPage(main));

        VerticalLayout evaluationTab = new VerticalLayout();
        evaluationTab.setMargin(true);
        evaluationTab.addComponent(new EvalauteTrainingViewPage(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(scheduleTab, "Schedule Course", null);
        tab.addTab(evaluationTab, "Pending Evaluations", null);

        if (selectedTab.equals("SCH")) {
            tab.setSelectedTab(scheduleTab);
        } else if (selectedTab.equals("EVA")) {
            tab.setSelectedTab(evaluationTab);
        }
        addComponent(tab);
    }
}
