/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.clients.web.vaadin.views.jobs.CreateJobsStructureMenuView;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class ConfigureSystemTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object ADD_JOBS_STRUCTURE = "Create Job Structure";
    public static final Object ADD_MANAGE_POSITIONS = "Manage Positions";
    public static final Object ADD_ORGANISATION_LIST = "Organisation Information";
    public static final Object ADD_EMPLOYEE_LIST = "Employee Information";
    public static final Object ADD_GEOGRAPHICAL_INFORMATION = "Geographical Information";
    public static final Object ADD_TRAINING_INFORMATION = "Training Information";
    public static final Object ADD_TRAINING_COURSE = "Add Courses";
      public static final Object ADD_MENTORING_PROGRAM = "Mentoring Program";

    public ConfigureSystemTreeMenu(HashThrimsMain app) {
        this.main = app;
        //add Items to Menu
        addItem(ADD_GEOGRAPHICAL_INFORMATION);
        addItem(ADD_ORGANISATION_LIST);
        addItem(ADD_EMPLOYEE_LIST);
        addItem(ADD_JOBS_STRUCTURE);
        addItem(ADD_MANAGE_POSITIONS);
        addItem(ADD_TRAINING_INFORMATION);
        addItem(ADD_TRAINING_COURSE);
        addItem(ADD_MENTORING_PROGRAM);

        //Add Listeners
        addListener((ItemClickListener) this);

    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (ADD_JOBS_STRUCTURE.equals(itemId)) {
                createJobsStructureView();
            } else if (ADD_MANAGE_POSITIONS.equals(itemId)) {
                managePositionsView();
            } else if (ADD_ORGANISATION_LIST.equals(itemId)) {
                organisationListView();
            } else if (ADD_EMPLOYEE_LIST.equals(itemId)) {
                employListView();
            } else if (ADD_GEOGRAPHICAL_INFORMATION.equals(itemId)) {
                showLocationView();
            } else if (ADD_TRAINING_INFORMATION.equals(itemId)) {
                trainingInformationView();
            } else if (ADD_TRAINING_COURSE.equals(itemId)) {
                trainingCourseView();
            } else if (ADD_MENTORING_PROGRAM.equals(itemId)) {
                mentoringProgramView();
            }
        }

    }

    private void showLocationView() {
        main.mainView.setSecondComponent(new LocationMenuView(main, "COUNTRY"));
    }

    private void createJobsStructureView() {
        main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CADRE"));
    }

    private void managePositionsView() {
        main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "SOURCE"));
    }

    private void organisationListView() {
        main.mainView.setSecondComponent(new OrganisationListMenuView(main, "DEPARTMENT"));
    }

    private void employListView() {
        main.mainView.setSecondComponent(new EmployeeListMenuView(main, "DEMOGRAPHICS"));
    }

    private void trainingInformationView() {
        main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "TRAINING"));
    }

    private void trainingCourseView() {
        main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "COURSES"));
    }

    private void mentoringProgramView() {
        main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "MENTORING"));
    }
}
