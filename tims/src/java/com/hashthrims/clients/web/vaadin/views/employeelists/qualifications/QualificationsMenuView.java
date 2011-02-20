/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.views.DegreeViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.views.EducationTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.views.LanguageViewPage;
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
public class QualificationsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public QualificationsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout jobTab = new VerticalLayout();
        jobTab.setMargin(true);
        jobTab.addComponent(new JobsViewPage(app));


        VerticalLayout degreeTab = new VerticalLayout();
        degreeTab.setMargin(true);
        degreeTab.addComponent(new DegreeViewPage(main));

        VerticalLayout educationTypeTab = new VerticalLayout();
        educationTypeTab.setMargin(true);
        educationTypeTab.addComponent(new EducationTypeViewPage(main));

        VerticalLayout LanguageTab = new VerticalLayout();
        LanguageTab.setMargin(true);
        LanguageTab.addComponent(new LanguageViewPage(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

       tab.addTab(educationTypeTab, "Education Type", null);
        tab.addTab(degreeTab, "Degree", null);
        tab.addTab(LanguageTab, "Language", null);


        if (selectedTab.equals("JOBS")) {
            tab.setSelectedTab(jobTab);
        } else if (selectedTab.equals("EDUCATION TYPE")) {
            tab.setSelectedTab(educationTypeTab);
        } else if (selectedTab.equals("LANGUAGE")) {
            tab.setSelectedTab(LanguageTab);
        } else if (selectedTab.equals("DEGREE")) {
            tab.setSelectedTab(degreeTab);
        }
        addComponent(tab);
    }
}
