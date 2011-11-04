/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.reports.courses.CoursesReportsMenuView;
import com.hashthrims.clients.web.vaadin.views.reports.facilities.FacilitiesReportsMenuView;
import com.hashthrims.clients.web.vaadin.views.reports.people.PeopleReportsMenuView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class GenerateReportsTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object PEOPLE_REPORTS = "People REPORTS";
    public static final Object COURSE_REPORTS = "Course REPORTS";
    public static final Object FACILITY_REPORTS = "Facility REPORTS";

    public GenerateReportsTreeMenu(HashThrimsMain app) {
        this.main = app;

        addItem(PEOPLE_REPORTS);
        addItem(COURSE_REPORTS);
        addItem(FACILITY_REPORTS);
        /*
         * We want items to be selectable but do not want the user to be able to
         * de-select an item.
         */
        setSelectable(true);
        setNullSelectionAllowed(false);

        // Make application handle item click events
        addListener((ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {

        Object itemId = event.getItemId();
        if (itemId != null) {
            if (PEOPLE_REPORTS.equals(itemId)) {
                showPeopleReportsTabViewPage();
            } else if (COURSE_REPORTS.equals(itemId)) {
                showCourseReportsTabViewPage();
            } else if (FACILITY_REPORTS.equals(itemId)) {
                showFacilityReportsTabViewPage();
            }
        }


    }

    private void showPeopleReportsTabViewPage() {
        PeopleReportsMenuView w = new PeopleReportsMenuView(main, "POSITION");
        main.mainView.setSecondComponent(w);
    }

    private void showCourseReportsTabViewPage() {
        CoursesReportsMenuView w = new CoursesReportsMenuView(main, "POSITION");
        main.mainView.setSecondComponent(w);
    }

    private void showFacilityReportsTabViewPage() {
        FacilitiesReportsMenuView w = new FacilitiesReportsMenuView(main, "POSITION");
        main.mainView.setSecondComponent(w);
    }
}
