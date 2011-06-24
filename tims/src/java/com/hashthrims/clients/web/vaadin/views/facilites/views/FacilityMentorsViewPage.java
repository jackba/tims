/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.invient.vaadin.charts.InvientChartsConfig.Legend.Layout;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
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
    private final ListSelect facilitiesList = new ListSelect("Please Select Facility");

    public FacilityMentorsViewPage(HashThrimsMain app) {
        layout.setSizeFull();
        layout.setMargin(true);
        List<Person> mentors = getMentors(data.getPersonService().findAll());
        Collections.sort(mentors);

        addMentorsLabel.setStyleName(Reindeer.LABEL_H2);
        facilityMentorsLabel.setStyleName(Reindeer.LABEL_H2);

        addMentorsButton.setStyleName(Reindeer.BUTTON_DEFAULT);
        addMentorsHeader.addComponent(addMentorsLabel);
        facilityMentorsHeader.addComponent(facilityMentorsLabel);


        layout.addComponent(addMentorsHeader, 0, 0, 1, 0);
        layout.setComponentAlignment(addMentorsHeader, Alignment.MIDDLE_CENTER);

        facilitiesList.setRows(20);
        facilitiesList.setNullSelectionAllowed(true);
        facilitiesList.setMultiSelect(true);
        facilitiesList.setImmediate(true);
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
        mentorsPanel.setFirstComponent(mentorsList);

        for (Person person : mentors) {
            
            mentorsRolesList.addItem(person.getId());
            mentorsRolesList.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }

        mentorsRolesList.setNullSelectionAllowed(true);
        mentorsRolesList.setMultiSelect(true);
        mentorsRolesList.setImmediate(true);
        mentorsRolesList.setSizeFull();
        mentorsPanel.setSecondComponent(mentorsRolesList);
        topLeftPanel.addComponent(mentorsPanel);

        for (Person person : mentors) {
            mentorsExpertiseList.addItem(person.getId());
            mentorsExpertiseList.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }
        mentorsExpertiseList.setNullSelectionAllowed(true);
        mentorsExpertiseList.setMultiSelect(true);
        mentorsExpertiseList.setImmediate(true);
        mentorsExpertiseList.setSizeFull();
        topLeftPanel.addComponent(mentorsExpertiseList);

        layout.addComponent(topLeftPanel, 1, 1);


        layout.addComponent(addMentorsButton, 0, 2, 1, 2);
        layout.setComponentAlignment(addMentorsButton, Alignment.MIDDLE_CENTER);
        layout.addComponent(facilityMentorsHeader, 0, 3, 1, 3);
        layout.setComponentAlignment(facilityMentorsHeader, Alignment.MIDDLE_CENTER);
        layout.addComponent(new Button("List of Facility Mentors Based on Selection"), 0, 4);
        bottomLeftPanel.setFirstComponent(new Button("Mentor's Roles "));
        bottomLeftPanel.setSecondComponent(new Button("Mentor's Exertise"));
        layout.addComponent(bottomLeftPanel, 1, 4);

        addComponent(layout);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == mentorsList) {
            
            
            
            
            
            
        }

        if (property == mentorsRolesList) {
        }

        if (property == facilitiesList) {
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
}
