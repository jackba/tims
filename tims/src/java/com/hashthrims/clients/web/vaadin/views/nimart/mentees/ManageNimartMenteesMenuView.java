/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.nimart.mentees;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.nimart.mentees.views.EvaluateMenteeViewPage;
import com.hashthrims.clients.web.vaadin.views.nimart.mentees.views.MentessListViewPage;
import com.hashthrims.clients.web.vaadin.views.users.views.RolesViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageNimartMenteesMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageNimartMenteesMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final  VerticalLayout menteeListTab = new VerticalLayout();
        menteeListTab.setMargin(true);
        menteeListTab.addComponent(new MentessListViewPage(main));

        final VerticalLayout evaluateMenteeTab = new VerticalLayout();
        evaluateMenteeTab.setMargin(true);
        evaluateMenteeTab.addComponent(new EvaluateMenteeViewPage(main));
        
        final VerticalLayout findMentorTab = new VerticalLayout();
        findMentorTab.setMargin(true);
        findMentorTab.addComponent(new RolesViewPage(main));

      
        

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(menteeListTab, "Mentees List", null);
        tab.addTab(evaluateMenteeTab, "Evaluate Mentee", null);
        tab.addTab(findMentorTab, "Find Mentor For Mentee", null);
       

        if (selectedTab.equals("MENTEE")) {
            tab.setSelectedTab(menteeListTab);
        } else if (selectedTab.equals("EVALUATE")) {
            tab.setSelectedTab(evaluateMenteeTab);
        }else if (selectedTab.equals("MENTOR")) {
            tab.setSelectedTab(findMentorTab);
        }
        addComponent(tab);
    }
}
