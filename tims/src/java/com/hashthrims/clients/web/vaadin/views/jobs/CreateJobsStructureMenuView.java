/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.jobs.views.CadresViewPage;
import com.hashthrims.clients.web.vaadin.views.jobs.views.JobClassificationViewPage;
import com.hashthrims.clients.web.vaadin.views.jobs.views.JobsViewPage;
import com.hashthrims.clients.web.vaadin.views.jobs.views.SalaryGradeViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class CreateJobsStructureMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public CreateJobsStructureMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout jobTab = new VerticalLayout();
        jobTab.setMargin(true);
        jobTab.addComponent(new JobsViewPage(app));


        final VerticalLayout cadreTab = new VerticalLayout();
        cadreTab.setMargin(true);
        cadreTab.addComponent(new CadresViewPage(main));

        final VerticalLayout classificationsTab = new VerticalLayout();
        classificationsTab.setMargin(true);
        classificationsTab.addComponent(new JobClassificationViewPage(main));

        final VerticalLayout salaryGrageTab = new VerticalLayout();
        salaryGrageTab.setMargin(true);
        salaryGrageTab.addComponent(new SalaryGradeViewPage(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

       
        tab.addTab(cadreTab, "Cadres", null);
        tab.addTab(classificationsTab, "Job Classifications", null);
        //tab.addTab(salaryGrageTab, "Salary Grade", null);
        tab.addTab(jobTab, "Job Title", null);

        if (selectedTab.equals("JOBS")) {
            tab.setSelectedTab(jobTab);
        } else if (selectedTab.equals("CLASSIFICATION")) {
            tab.setSelectedTab(classificationsTab);
        } else if (selectedTab.equals("GRADE")) {
            tab.setSelectedTab(salaryGrageTab);
        } else if (selectedTab.equals("CADRE")) {
            tab.setSelectedTab(cadreTab);
        }
        addComponent(tab);
    }
}
