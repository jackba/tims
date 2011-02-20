/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.views.CompetencyEvaluationViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.views.CompetencyTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.views.CompetencyListViewPage;
import com.hashthrims.clients.web.vaadin.views.jobs.views.CadresViewPage;
import com.hashthrims.clients.web.vaadin.views.jobs.views.JobsViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class CompetencyMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public CompetencyMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout competencyTypeTab = new VerticalLayout();
        competencyTypeTab.setMargin(true);
        competencyTypeTab.addComponent(new CompetencyTypeViewPage(main));

        VerticalLayout competencyView = new VerticalLayout();
        competencyView.setMargin(true);
        competencyView.addComponent(new CompetencyListViewPage(main));

        VerticalLayout competencyEvaluationTab = new VerticalLayout();
        competencyEvaluationTab.setMargin(true);
        competencyEvaluationTab.addComponent(new CompetencyEvaluationViewPage(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

       
        tab.addTab(competencyTypeTab, "Subject Area", null);
        tab.addTab(competencyView, "Competency", null);
        tab.addTab(competencyEvaluationTab, "Competency Evaluation", null);


        if (selectedTab.equals("COMPETENCY")) {
            tab.setSelectedTab(competencyView);
        } else if (selectedTab.equals("COMPETENCY EVALUATION")) {
            tab.setSelectedTab(competencyEvaluationTab);
        } else if (selectedTab.equals("COMPETENCY TYPE")) {
            tab.setSelectedTab(competencyTypeTab);
        }
        addComponent(tab);
    }
}
