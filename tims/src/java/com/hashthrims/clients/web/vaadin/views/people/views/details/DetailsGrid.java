/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.ContactsPage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.CoursePage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.demographics.DemographicsGrid;
import com.hashthrims.clients.web.vaadin.views.people.views.details.education.EducationHistoryPage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.identitification.IdentitiesPage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.languages.LangaugesPage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.MentoringPage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.position.EmployeePositionPage;
import com.hashthrims.clients.web.vaadin.views.people.views.details.registration.ProfessionalRegistrationPage;
import com.hashthrims.domain.Person;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author boniface
 */
public class DetailsGrid {

    public static GridLayout detailsGrid(Person person, HashThrimsMain app, String selectedTab) {
        final GridLayout outer = new GridLayout(1, 2);
        final TabSheet tab = new TabSheet();
        outer.setSizeFull();
        outer.setSpacing(true);
        outer.setMargin(true);
        //Header Information for the Deatails Page
        final Label personLabel = new Label("Viewing Details For:" + person.getPersonName() + "  " + person.getPersonSurname());
        personLabel.addStyleName(Reindeer.LABEL_H1);

        outer.addComponent(personLabel, 0, 0);



        final VerticalLayout demographicsTab = new VerticalLayout();
        demographicsTab.setMargin(true);
        demographicsTab.addComponent(new DemographicsGrid().getDemographics(person, app));

        final VerticalLayout identitiesTabs = new VerticalLayout();
        identitiesTabs.setMargin(true);
        identitiesTabs.addComponent(new IdentitiesPage(person, app));

        final VerticalLayout contatcsTabs = new VerticalLayout();
        contatcsTabs.setMargin(true);
        contatcsTabs.addComponent(new ContactsPage(person, app));

        final VerticalLayout mentoringTabs = new VerticalLayout();
        mentoringTabs.setMargin(true);
        mentoringTabs.addComponent(new MentoringPage(person, app));

        final VerticalLayout coursesTab = new VerticalLayout();
        coursesTab.setMargin(true);
        coursesTab.addComponent(new CoursePage(person, app,"EVA"));


        final VerticalLayout educationTab = new VerticalLayout();
        educationTab.setMargin(true);
        educationTab.addComponent(new EducationHistoryPage(person, app));


        final VerticalLayout employmentTab = new VerticalLayout();
        employmentTab.setMargin(true);
        employmentTab.addComponent(new IdentitiesPage(person, app));

        final VerticalLayout registrationTab = new VerticalLayout();
        registrationTab.setMargin(true);
        registrationTab.addComponent(new ProfessionalRegistrationPage(person, app));

        final VerticalLayout positionTab = new VerticalLayout();
        positionTab.setMargin(true);
        positionTab.addComponent(new EmployeePositionPage(person, app));

        final VerticalLayout languagesTab = new VerticalLayout();
        languagesTab.setMargin(true);
        languagesTab.addComponent(new LangaugesPage(person, app));

        final VerticalLayout qualificationsTab = new VerticalLayout();
        qualificationsTab.setMargin(true);
        qualificationsTab.addComponent(new LangaugesPage(person, app));


        tab.addTab(demographicsTab, "Demographics", null);
        tab.addTab(identitiesTabs, "Identities", null);
        tab.addTab(contatcsTabs, "Contatcs", null);
        tab.addTab(mentoringTabs, "Mentoring", null);
        tab.addTab(coursesTab, "Courses", null);
        tab.addTab(educationTab, "Education History", null);

        tab.addTab(registrationTab, "Registration", null);
        tab.addTab(positionTab, "Position", null);
        tab.addTab(languagesTab, "Languages", null);
       // tab.addTab(qualificationsTab, "Qualification", null);

        if (selectedTab.equals("IDS")) {
            tab.setSelectedTab(identitiesTabs);
        } else if (selectedTab.equals("DEMO")) {
            tab.setSelectedTab(demographicsTab);
        } else if (selectedTab.equals("CONTACTS")) {
            tab.setSelectedTab(contatcsTabs);
        } else if (selectedTab.equals("MENT")) {
            tab.setSelectedTab(mentoringTabs);
        } else if (selectedTab.equals("COURSE")) {
            tab.setSelectedTab(coursesTab);
        } else if (selectedTab.equals("EDU")) {
            tab.setSelectedTab(educationTab);
        } else if (selectedTab.equals("EMP")) {
            tab.setSelectedTab(employmentTab);
        } else if (selectedTab.equals("REG")) {
            tab.setSelectedTab(registrationTab);
        } else if (selectedTab.equals("POS")) {
            tab.setSelectedTab(positionTab);
        } else if (selectedTab.equals("LANG")) {
            tab.setSelectedTab(languagesTab);
        } else if (selectedTab.equals("QUAL")) {
            tab.setSelectedTab(qualificationsTab);
        }




        outer.addComponent(tab);



        return outer;
    }
}
