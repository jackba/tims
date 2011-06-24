/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.AddMentorsViewPage;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.ContinueEducationViewPage;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.MentoringFieldViewPage;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.MentorsRolesViewPage;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.TrainingCourseCategoryViewPage;

import com.hashthrims.clients.web.vaadin.views.traininginformation.views.TrainingCourseEvaluationViewPage;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.TrainingFunderViewPage;
import com.hashthrims.clients.web.vaadin.views.traininginformation.views.TrainingInstitutionViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class TrainingInformationMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public TrainingInformationMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout trainingInstitutionTab = new VerticalLayout();
        trainingInstitutionTab.setMargin(true);
        trainingInstitutionTab.addComponent(new TrainingInstitutionViewPage(main));

        VerticalLayout evaluationTab = new VerticalLayout();
        evaluationTab.setMargin(true);
        evaluationTab.addComponent(new TrainingCourseEvaluationViewPage(main));

        VerticalLayout funderTab = new VerticalLayout();
        funderTab.setMargin(true);
        funderTab.addComponent(new TrainingFunderViewPage(main));


        VerticalLayout continuingEducationTab = new VerticalLayout();
        continuingEducationTab.setMargin(true);
        continuingEducationTab.addComponent(new ContinueEducationViewPage(main));

        VerticalLayout categoryTab = new VerticalLayout();
        categoryTab.setMargin(true);
        categoryTab.addComponent(new TrainingCourseCategoryViewPage(main));

        VerticalLayout mentoringFieldTab = new VerticalLayout();
        mentoringFieldTab.setMargin(true);
        mentoringFieldTab.addComponent(new MentoringFieldViewPage(main));

        VerticalLayout mentorsRolesTab = new VerticalLayout();
        mentorsRolesTab.setMargin(true);
        mentorsRolesTab.addComponent(new MentorsRolesViewPage(main));

        VerticalLayout addMentorsTab = new VerticalLayout();
        addMentorsTab.setMargin(true);
        addMentorsTab.addComponent(new AddMentorsViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(trainingInstitutionTab, "Training Institution", null);
        // tab.addTab(evaluationTab, "Evaluation", null);
        tab.addTab(funderTab, "Training Funder", null);
        //tab.addTab(continuingEducationTab, "Continue Education", null);
        tab.addTab(categoryTab, "Training Field", null);
        tab.addTab(mentoringFieldTab, "Mentoring Subject Area", null);
        tab.addTab(mentorsRolesTab, "Mentors Roles", null);
        tab.addTab(addMentorsTab, "Add Mentors", null);

        if (selectedTab.equals("TRAINING")) {
            tab.setSelectedTab(trainingInstitutionTab);
        } else if (selectedTab.equals("EVALUATION")) {
            tab.setSelectedTab(evaluationTab);
        } else if (selectedTab.equals("FUNDER")) {
            tab.setSelectedTab(funderTab);
        } else if (selectedTab.equals("EDUCATION")) {
            tab.setSelectedTab(continuingEducationTab);
        } else if (selectedTab.equals("CATEGORY")) {
            tab.setSelectedTab(categoryTab);
        } else if (selectedTab.equals("MENTORING")) {
            tab.setSelectedTab(mentoringFieldTab);
        } else if (selectedTab.equals("ROLES")) {
            tab.setSelectedTab(mentorsRolesTab);
        } else if (selectedTab.equals("MENTORS")) {
            tab.setSelectedTab(addMentorsTab);
        }
        addComponent(tab);
    }
}
