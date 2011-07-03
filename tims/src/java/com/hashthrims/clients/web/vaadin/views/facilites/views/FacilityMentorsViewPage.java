/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.domain.MentorExpertiseArea;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityMentors;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class FacilityMentorsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final GridLayout layout = new GridLayout(2, 5);
    private final HorizontalLayout addMentorsHeader = new HorizontalLayout();
    private final HorizontalLayout facilityMentorsHeader = new HorizontalLayout();
    private final Button addMentorsButton = new Button(" Add Mentors to Facility");
    private final Label addMentorsLabel = new Label(" Add Mentors To Facilities");
    private final Label facilityMentorsLabel = new Label(" Current Facility Mentors");
    private final ClientDataService data = new ClientDataService();
    private final VerticalLayout topLeftPanel = new VerticalLayout();
    private final HorizontalSplitPanel bottomLeftPanel = new HorizontalSplitPanel();
    private final HorizontalSplitPanel mentorsPanel = new HorizontalSplitPanel();
    private final ListSelect mentorsList = new ListSelect("Select Mentors");
    private final ListSelect mentorsRolesList = new ListSelect("Mentors Roles");
    private final ListSelect mentorsExpertiseList = new ListSelect("Mentors Expertise");
    
     private final ListSelect facilitiesMentorsRolesList = new ListSelect("Facility Mentors Roles");
    private final ListSelect facilitiesMentorsExpertiseList = new ListSelect("Facility Mentors Expertise");
    
    private final ListSelect facilitiesList = new ListSelect("Please Select Facility");
    private final ListSelect facilitiesMentorsList = new ListSelect("Facility Mentors");
    private final Table facilityMentorsTable = new Table();
    private final HashThrimsMain main;
    private Long personWithThisRole;
    private Collection<Long> idsForFacilities;
    private Collection<Long> idsForMentors;

    public FacilityMentorsViewPage(HashThrimsMain app) {
        main = app;
        layout.setSizeFull();
        layout.setMargin(true);
        List<Person> mentors = getMentors(data.getPersonService().findAll());
        Collections.sort(mentors);

        addMentorsLabel.setStyleName(Reindeer.LABEL_H2);
        facilityMentorsLabel.setStyleName(Reindeer.LABEL_H2);

        addMentorsButton.setStyleName(Reindeer.BUTTON_DEFAULT);
        addMentorsHeader.addComponent(addMentorsLabel);
        facilityMentorsHeader.addComponent(facilityMentorsLabel);
        addMentorsButton.addListener((ClickListener) this);


        layout.addComponent(addMentorsHeader, 0, 0, 1, 0);
        layout.setComponentAlignment(addMentorsHeader, Alignment.MIDDLE_CENTER);

        facilitiesList.setRows(20);
        facilitiesList.setWidth("400px");
        facilitiesList.setNullSelectionAllowed(true);
        facilitiesList.setMultiSelect(true);
        facilitiesList.setImmediate(true);
        facilitiesList.addListener((ValueChangeListener) this);
        List<Facility> facilities = data.getFacilityService().findAll();
        Collections.sort(facilities);
        for (Facility facility : facilities) {
            facilitiesList.setItemCaption(facility.getId(), facility.getFacilityName());
            facilitiesList.addItem(facility.getId());
        }
        layout.addComponent(facilitiesList, 0, 1);
        for (Person person : mentors) {
            mentorsList.addItem(person.getId());
            mentorsList.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }
        mentorsList.setNullSelectionAllowed(true);
        mentorsList.setMultiSelect(true);
        mentorsList.setImmediate(true);
        mentorsList.setSizeFull();
        mentorsList.addListener((ValueChangeListener) this);

        mentorsPanel.setFirstComponent(mentorsList);


        mentorsRolesList.setNullSelectionAllowed(true);
        mentorsRolesList.setMultiSelect(true);
        mentorsRolesList.setImmediate(true);
        mentorsRolesList.setSizeFull();
        mentorsRolesList.addListener((ValueChangeListener) this);
        mentorsPanel.setSecondComponent(mentorsRolesList);
        topLeftPanel.addComponent(mentorsPanel);


        mentorsExpertiseList.setNullSelectionAllowed(true);
        mentorsExpertiseList.setMultiSelect(true);
        mentorsExpertiseList.setImmediate(true);
        mentorsExpertiseList.setSizeFull();
        topLeftPanel.addComponent(mentorsExpertiseList);

        layout.addComponent(topLeftPanel, 1, 1);
        
        facilitiesMentorsExpertiseList.setSizeFull();
        facilitiesMentorsExpertiseList.setHeight("200px");
        facilitiesMentorsRolesList.setSizeFull();
        facilitiesMentorsRolesList.setHeight("200px");

        layout.addComponent(addMentorsButton, 0, 2, 1, 2);
        layout.setComponentAlignment(addMentorsButton, Alignment.MIDDLE_CENTER);
        layout.addComponent(facilityMentorsHeader, 0, 3, 1, 3);
        layout.setComponentAlignment(facilityMentorsHeader, Alignment.MIDDLE_CENTER);
        layout.addComponent(facilityMentorsTable, 0, 4);
        bottomLeftPanel.setFirstComponent(facilitiesMentorsRolesList );
        bottomLeftPanel.setSecondComponent(facilitiesMentorsExpertiseList);
        layout.addComponent(bottomLeftPanel, 1, 4);

        addComponent(layout);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == addMentorsButton) {
            Collection<Long> facilities = idsForFacilities;
            for (Long facId : facilities) {
                Facility facility = data.getFacilityService().find(facId);
                Collection<Long> mentorsIds = idsForMentors;
                for (Long mn : mentorsIds) {
                    FacilityMentors mentor = new FacilityMentors();
                    Person p = data.getPersonService().find(mn);
                    mentor.setFirstName(p.getPersonName());
                    mentor.setLastName(p.getPersonSurname());
                    mentor.setMentorId(p.getId());
                    facility.getFacilityMentors().add(mentor);
                }
                data.getFacilityService().merge(facility);
            }
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "MENTORS"));
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == mentorsList) {
            Collection<Long> mentorsIds = (Collection<Long>) property.getValue();
            idsForMentors = mentorsIds;
            mentorsExpertiseList.removeAllItems();
            if (mentorsIds.size() != 1) {
                mentorsRolesList.removeAllItems();
            } else {
                for (Long mentorId : mentorsIds) {
                    personWithThisRole = mentorId;
                    Person person = data.getPersonService().find(mentorId);
                    List<PersonRoles> personRoles = person.getPersonRoles();
                    mentorsRolesList.removeAllItems();
                    for (PersonRoles roles : personRoles) {
                        MentorsRoles role = data.getMentorsRolesService().getByPropertyName("mentorsRolesName", roles.getRoleName());
                        mentorsRolesList.addItem(role.getId());
                        mentorsRolesList.setItemCaption(role.getId(), role.getMentorsRolesName());
                    }
                }
            }
        }

        if (property == mentorsRolesList) {

            mentorsExpertiseList.removeAllItems();

            Collection<Long> mentorsIds = (Collection<Long>) property.getValue();
            for (Long mentorRoleId : mentorsIds) {
                MentorsRoles mentorRole = data.getMentorsRolesService().find(mentorRoleId);
                if (isPersonResouce(mentorRole)) {
                    Person person = data.getPersonService().find(personWithThisRole);
                    List<MentorExpertiseArea> expertiseArea = person.getExpertiseArea();
                    for (MentorExpertiseArea expertise : expertiseArea) {
                        mentorsExpertiseList.addItem(expertise.getId());
                        mentorsExpertiseList.setItemCaption(expertise.getId(), expertise.getExpertiseAreaName());
                    }
                }
            }

        }

        if (property == facilitiesList) {
            facilityMentorsTable.removeAllItems();
            creatementorsTable(facilityMentorsTable);
            idsForFacilities = (Collection<Long>) property.getValue();
            if (idsForFacilities.size() == 1) {
                for (Long facId : idsForFacilities) {
                    Facility facility = data.getFacilityService().find(facId);
                    List<FacilityMentors> ments = facility.getFacilityMentors();
                    for (FacilityMentors ment : ments) {
                        facilityMentorsTable.addItem(new Object[] {ment.getFirstName(),ment.getLastName()},ment.getId()); 
                    }
                }
            } else {
                facilityMentorsTable.removeAllItems();
                facilitiesMentorsList.removeAllItems();
            }
        }
    }

    private List<Person> getMentors(List<Person> people) {
        List<Person> mentors = new ArrayList<Person>();
        for (Person person : people) {
            if (person.getPersonRoles().size() > 0) {
                mentors.add(person);
            }
        }
        return mentors;
    }

    private boolean isPersonResouce(MentorsRoles mentorRole) {
        boolean isReosurce = false;
        if (mentorRole.getMentorsRolesName() != null) {
            if (mentorRole.getMentorsRolesName().equalsIgnoreCase("Resource Person")) {
                isReosurce = true;
            }
        }
        return isReosurce;
    }

    private void creatementorsTable(Table table) {
        // Define the names and data types of columns.
        table.addContainerProperty("First Name", String.class, null);
        table.addContainerProperty("Last Name", String.class, null);
        table.setWidth("400px");
        table.setHeight("200px");
    }
}
