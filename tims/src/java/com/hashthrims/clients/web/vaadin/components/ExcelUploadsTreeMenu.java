/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;

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

    public ExcelUploadsTreeMenu(HashThrimsMain app) {
        this.main = app;

        //add Items to Menu
        addItem(COURSES);
        addItem(PEOPLE);
        addItem(COMPETENCIES);

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
            }
        }

    }


    private void uploadCourseView() {
          main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
    }

    private void uploadPeopleView() {
        main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
    }

    private void uploadCompetenciesView() {
         main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
    }
}
