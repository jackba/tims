/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.domain.Person;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class CoursePage extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public CoursePage(final Person person, HashThrimsMain app, String selectedTab) {
        main = app;
        setSizeFull();
        tab = new TabSheet();
        tab.setSizeFull();
    


        final VerticalLayout schedulesTab = new VerticalLayout();
        schedulesTab.setMargin(true);
        schedulesTab.addComponent(new ScheduledCourses(person,main));

        final VerticalLayout evaluationsTab = new VerticalLayout();
        evaluationsTab.setMargin(true);
        evaluationsTab.addComponent(new EvaluatedCourses(person,main));

        tab.addTab(schedulesTab, "Schedule Course", null);
        tab.addTab(evaluationsTab, "Evaluate Course", null);

        if (selectedTab.equals("SCH")) {
            tab.setSelectedTab(schedulesTab);
        } else if (selectedTab.equals("EVA")) {
            tab.setSelectedTab(schedulesTab);
        }

        addComponent(tab);

    }

   
}
