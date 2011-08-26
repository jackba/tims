/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.courses;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;


import com.hashthrims.clients.web.vaadin.views.uploads.courses.views.AddCourseUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.courses.views.CourseRequestorsUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.courses.views.CoursesSpreadSheetCodesViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.courses.views.SelectionCriteriaUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.courses.views.TargetGroupUploadsViewPage;
import com.hashthrims.clients.web.vaadin.views.uploads.courses.views.TrainingTypeUploadsViewPage;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class CourseViewMenuView extends VerticalLayout {

    private final HashThrimsMain main;
    private final TabSheet tab;

    public CourseViewMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout trainingTypeTabs = new VerticalLayout();
        trainingTypeTabs.setMargin(true);
        trainingTypeTabs.addComponent(new TrainingTypeUploadsViewPage(main));

        final VerticalLayout spreadSheetCodes = new VerticalLayout();
        spreadSheetCodes.setMargin(true);
        spreadSheetCodes.addComponent(new CoursesSpreadSheetCodesViewPage(main));

        final VerticalLayout addCourseTab = new VerticalLayout();
        addCourseTab.setMargin(true);
        addCourseTab.addComponent(new AddCourseUploadsViewPage(main));


        final VerticalLayout targetGroupTab = new VerticalLayout();
        targetGroupTab.setMargin(true);
        targetGroupTab.addComponent(new TargetGroupUploadsViewPage(main));

        final VerticalLayout courseRequestorsTab = new VerticalLayout();
        courseRequestorsTab.setMargin(true);
        courseRequestorsTab.addComponent(new CourseRequestorsUploadsViewPage(main));



        final VerticalLayout selectionCriteriaTab = new VerticalLayout();
        selectionCriteriaTab.setMargin(true);
        selectionCriteriaTab.addComponent(new SelectionCriteriaUploadsViewPage(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(addCourseTab, "Adding Courses", null);
        tab.addTab(trainingTypeTabs, "Type of Trainings", null);
        tab.addTab(targetGroupTab, "Target Groups", null);
        tab.addTab(selectionCriteriaTab, "Selection Creteria", null);
        tab.addTab(courseRequestorsTab, "Course Requestors", null);
        tab.addTab(spreadSheetCodes, "Spread Sheet Codes", null);




        if (selectedTab.equals("COURSE")) {
            tab.setSelectedTab(addCourseTab);
        } else if (selectedTab.equals("THEME")) {
            tab.setSelectedTab(trainingTypeTabs);
        } else if (selectedTab.equals("SESSION")) {
            tab.setSelectedTab(selectionCriteriaTab);
        } else if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(targetGroupTab);
        } else if (selectedTab.equals("ST")) {
            tab.setSelectedTab(courseRequestorsTab);
        } else if (selectedTab.equals("OBJ")) {
            tab.setSelectedTab(spreadSheetCodes);
        }
        addComponent(tab);
    }
}
