/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.managetraining.views.ParticipantsEnrollViewPage;
import com.hashthrims.clients.web.vaadin.views.managetraining.views.EnrolledPaticipantsViewPage;
import com.hashthrims.clients.web.vaadin.views.managetraining.views.EvaluateTrainingViewPage;
import com.hashthrims.clients.web.vaadin.views.managetraining.views.ScheduleTrainingViewPage;

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
        scheduleTab.addComponent(new ScheduleTrainingViewPage(main));
        
        
  

        VerticalLayout evaluationTab = new VerticalLayout();
        evaluationTab.setMargin(true);
        evaluationTab.addComponent(new EvaluateTrainingViewPage(main));

        VerticalLayout enrollTab = new VerticalLayout();
        enrollTab.setMargin(true);
        enrollTab.addComponent(new ParticipantsEnrollViewPage(main));
        
        VerticalLayout enrolledTab = new VerticalLayout();
        enrolledTab.setMargin(true);
        enrolledTab.addComponent(new EnrolledPaticipantsViewPage(main));
        
        
        

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(scheduleTab, "Schedule Course", null);
        tab.addTab(enrollTab, "Enroll Participants", null);
        tab.addTab(enrolledTab, "Enrolled Paticipants", null);
        //tab.addTab(evaluationTab, "Evaluations", null);

        if (selectedTab.equals("ENROLL")) {
            tab.setSelectedTab(enrollTab);
        } else if (selectedTab.equals("EVA")) {
            tab.setSelectedTab(evaluationTab);
        }else if (selectedTab.equals("SCH")) {
            tab.setSelectedTab(scheduleTab);
        }else if (selectedTab.equals("EN")) {
            tab.setSelectedTab(enrolledTab);
        }
        addComponent(tab);
    }
}
