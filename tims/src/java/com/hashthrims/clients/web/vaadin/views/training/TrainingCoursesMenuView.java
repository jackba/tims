/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseRequesterViewPage;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseStatusViewPage;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseViewPage;
import com.hashthrims.clients.web.vaadin.views.training.views.CourseTypeViewPage;


import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class TrainingCoursesMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public TrainingCoursesMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

       final  VerticalLayout trainingCourseTab = new VerticalLayout();
        trainingCourseTab.setMargin(true);
        trainingCourseTab.addComponent(new CourseViewPage(main));

        final VerticalLayout courseTypeTab = new VerticalLayout();
        courseTypeTab.setMargin(true);
        courseTypeTab.addComponent(new CourseTypeViewPage(main));

       final  VerticalLayout courseStatusTab = new VerticalLayout();
        courseStatusTab.setMargin(true);
        courseStatusTab.addComponent(new CourseStatusViewPage(main));


       final  VerticalLayout requesterTab = new VerticalLayout();
        requesterTab.setMargin(true);
        requesterTab.addComponent(new CourseRequesterViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(courseTypeTab, "Training Course Type", null);
        tab.addTab(trainingCourseTab, " Add Training Course", null);

        //tab.addTab(courseStatusTab, "Course Status ", null);
        tab.addTab(requesterTab, "Course Requester", null);


        if (selectedTab.equals("COURSE")) {
            tab.setSelectedTab(trainingCourseTab);
        } else if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(courseTypeTab);
        } else if (selectedTab.equals("STATUS")) {
            tab.setSelectedTab(courseStatusTab);
        } else if (selectedTab.equals("REQUEST")) {
            tab.setSelectedTab(requesterTab);
        }
        addComponent(tab);
    }
}
