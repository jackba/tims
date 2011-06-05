/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.infrastructure.util.GetUserCredentials;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author boniface
 */
public class TabAccordian extends Accordion {

    private final HashThrimsMain main;
    public static final String MANAGE_PEOPLE = "Manage PEOPLE ";
    public static final String REPORTS = "Generate REPORTS";
    public static final String CONFIGURE_SYSTEM = "Setup SYSTEM";
    public static final String SYSTEM_USERS = "Manage USERS";
    public static final String COURSES = " Manage TRAINING";
    public static final String CHANGE_PASSWORD = "Change PASSWORD";
    public static final String MENTORING_NIMART = "Mentoring NIMART";
    private final GetUserCredentials user;

    public TabAccordian(HashThrimsMain app) {
        SecurityContextHolder.getContext().setAuthentication(app.getAuth());
        main = app;
        user = new GetUserCredentials();
        setSizeFull();

        //Configure Manage People Menu
        VerticalLayout managePeople = new VerticalLayout();
        ManagePeopleTreeMenu managePeopleTree = new ManagePeopleTreeMenu(main);
        managePeople.addComponent(managePeopleTree);

        addTab(managePeople, MANAGE_PEOPLE, null);

        // Manage Courses
        VerticalLayout courses = new VerticalLayout();
        ManageCoursesTreeMenu manageCoursesTree = new ManageCoursesTreeMenu(main);
        courses.addComponent(manageCoursesTree);
        addTab(courses, COURSES, null);

        // Niamrt Details 
        VerticalLayout nimartLayout = new VerticalLayout();
        MentoringNimartTreeMenu nimartTree = new MentoringNimartTreeMenu(app);
        nimartLayout.addComponent(nimartTree);
        addTab(nimartLayout, MENTORING_NIMART, null);

        // Manage Reports
        VerticalLayout reports = new VerticalLayout();
        GenerateReportsTreeMenu reportTree = new GenerateReportsTreeMenu(app);
        reports.addComponent(reportTree);
        //addTab(reports, REPORTS, null);

        // Change Your  Details
        VerticalLayout changeYourDetails = new VerticalLayout();
        ChangeUserDetailsTreeMenu userDatailsTree = new ChangeUserDetailsTreeMenu(app);
        changeYourDetails.addComponent(userDatailsTree);
        addTab(changeYourDetails, CHANGE_PASSWORD, null);



        // Configure System Menu
        VerticalLayout configureSystem = new VerticalLayout();
        ConfigureSystemTreeMenu configureTree = new ConfigureSystemTreeMenu(main);
        configureSystem.addComponent(configureTree);

        if (user.isUserWithRole("ROLE_ADMIN")) {
            addTab(configureSystem, CONFIGURE_SYSTEM, null);
        }

        //Configure System Users
        VerticalLayout systemUsers = new VerticalLayout();
        ManageSystemUsersTreeMenu systemUsersTree = new ManageSystemUsersTreeMenu(app);
        systemUsers.addComponent(systemUsersTree);

        if (user.isUserWithRole("ROLE_ADMIN")) {
            addTab(systemUsers, SYSTEM_USERS, null);
        }
    }
}
