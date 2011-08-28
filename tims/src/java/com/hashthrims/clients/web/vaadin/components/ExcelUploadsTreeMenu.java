/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;

import com.hashthrims.clients.web.vaadin.views.uploads.competencies.CompetenciesViewMenuView;
import com.hashthrims.clients.web.vaadin.views.uploads.courses.CourseViewMenuView;
import com.hashthrims.clients.web.vaadin.views.uploads.mentoring.MentoringViewMenuView;
import com.hashthrims.clients.web.vaadin.views.uploads.people.PeopleViewMenuView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class ExcelUploadsTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object PEOPLE = "People";
    public static final Object COURSES = "Courses";
    public static final Object COMPETENCIES = "Competencies";
    public static final Object MENTORING = "Mentoring";

    public ExcelUploadsTreeMenu(HashThrimsMain app) {
        this.main = app;

        //add Items to Menu
        addItem(COURSES);
        addItem(PEOPLE);
        addItem(COMPETENCIES);
        //addItem(MENTORING);

        //Add Listeners
        addListener((ItemClickListener) this);

    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (COURSES.equals(itemId)) {
                uploadCourseView();
            } else if (PEOPLE.equals(itemId)) {
                uploadPeopleView();
            } else if (COMPETENCIES.equals(itemId)) {
                uploadCompetenciesView();
            } else if (MENTORING.equals(itemId)) {
                uploadMentoringView();
            }
        }

    }

    private void uploadCourseView() {
        main.mainView.setSecondComponent(new CourseViewMenuView(main, "COURSE"));
    }

    private void uploadPeopleView() {
        main.mainView.setSecondComponent(new PeopleViewMenuView(main, "PEOPLE"));
    }

    private void uploadCompetenciesView() {
        main.mainView.setSecondComponent(new CompetenciesViewMenuView(main, "SUBJ"));
    }

    private void uploadMentoringView() {
        main.mainView.setSecondComponent(new MentoringViewMenuView(main, "MENT"));
    }
}
