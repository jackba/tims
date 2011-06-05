/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.managementoring.ManageMentoringMenuView;
import com.hashthrims.clients.web.vaadin.views.managetraining.ManageTrainingMenuView;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class ManageCoursesTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object MANAGE_COURSES = "Courses";
    public static final Object MANAGE_MENTORING = "Mentoring";

    public ManageCoursesTreeMenu(HashThrimsMain app) {
        this.main = app;

        addItem(MANAGE_COURSES);
        addItem(MANAGE_MENTORING);
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
            if (MANAGE_COURSES.equals(itemId)) {
                showManageTrainingView();
            } else if (MANAGE_MENTORING.equals(itemId)) {
                showManageMentoringView();
            }


        }


    }

    private void showManageMentoringView() {
         main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "POSITION"));

    }

    private void showManageTrainingView() {
       ManageTrainingMenuView w = new ManageTrainingMenuView(main, "POSITION");
        main.mainView.setSecondComponent(w);
    }
}
