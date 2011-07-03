/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.domain.MentorExpertiseArea;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author boniface
 */
public class AddMentorsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private static ClientDataService data = new ClientDataService();
    private final HorizontalSplitPanel topForm = new HorizontalSplitPanel();
    private final VerticalLayout leftWindow = new VerticalLayout();
    private final HorizontalLayout footer = new HorizontalLayout();
    private final VerticalLayout rightWindow = new VerticalLayout();
    private final ComboBox facilitiesList = new ComboBox("Select Facility ");
    private final ListSelect peopleList = new ListSelect("Please select Mentors");
    private final ListSelect expertiseList = new ListSelect("Please Area of Expertise");
    private final Label expertiseArea = new Label("Area of Expertise");
    private final Label mentorsRole = new Label("Mentors Roles");
    private final Panel checkBoxpanel = new Panel("Mentors Roles");
    private final Button addMentors = new Button("Add Selected Mentors");
    private final Button cancelProcess = new Button("Cancel Process");
    private Collection<Long> mentors;
    private Collection<Long> expertise;
    private final Set<String> mentorsRoles = new HashSet<String>();
    private final HashThrimsMain main;

    public AddMentorsViewPage(HashThrimsMain app) {

        main = app;

        addMentors.addListener((ClickListener) this);
        cancelProcess.addListener((ClickListener) this);
        topForm.setSplitPosition(360, UNITS_PIXELS);
        topForm.addStyleName(Reindeer.SPLITPANEL_SMALL);
        topForm.setLocked(true);
        facilitiesList.setWidth("350px");
        rightWindow.setMargin(true);
        List<Facility> facilities = data.getFacilityService().findAll();
        for (Facility facility : facilities) {
            facilitiesList.setItemCaption(facility.getId(), facility.getFacilityName());
            facilitiesList.addItem(facility.getId());
        }
        List<Person> persons = data.getPersonService().findAll();
        Collections.sort(persons);
        for (Person person : persons) {
            peopleList.addItem(person.getId());
            peopleList.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }

        peopleList.setRows(20);
        peopleList.setWidth("350px");
        peopleList.setNullSelectionAllowed(true);
        peopleList.setMultiSelect(true);
        peopleList.setImmediate(true);
        peopleList.addListener((ValueChangeListener) this);

        leftWindow.addComponent(facilitiesList);
        leftWindow.addComponent(peopleList);
        topForm.setFirstComponent(leftWindow);

        topForm.setSecondComponent(rightWindow);
        footer.addComponent(addMentors);
        footer.setComponentAlignment(addMentors, Alignment.MIDDLE_CENTER);
        footer.addComponent(cancelProcess);
        footer.setComponentAlignment(cancelProcess, Alignment.MIDDLE_CENTER);
        addComponent(topForm);
        addComponent(footer);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (event.getComponent().getCaption().equalsIgnoreCase("Resource Person")) {
            if (event.getButton().booleanValue()) {
                mentorsRoles.add(event.getComponent().getCaption());
                List<TrainingCourseCategory> categories = data.getTrainingCourseCategoryService().findAll();
                expertiseArea.setStyleName(Runo.LABEL_H2);
                rightWindow.addComponent(expertiseArea);
                expertiseList.setWidth("350px");
                expertiseList.removeAllItems();
                expertiseList.setColumns(20);
                expertiseList.setMultiSelect(true);
                expertiseList.setImmediate(true);
                expertiseList.addListener((ValueChangeListener) this);
                for (TrainingCourseCategory trainingCourseCategory : categories) {
                    expertiseList.addItem(trainingCourseCategory.getId());
                    expertiseList.setItemCaption(trainingCourseCategory.getId(), trainingCourseCategory.getCategoryName());
                    rightWindow.addComponent(expertiseList);
                }
            } else {
                mentorsRoles.remove(event.getComponent().getCaption());
                expertise = null;
                rightWindow.removeComponent(expertiseArea);
                rightWindow.removeComponent(expertiseList);
            }
        } else {
            if (event.getButton().booleanValue()) {
                mentorsRoles.add(event.getComponent().getCaption());

            } else {
                mentorsRoles.remove(event.getComponent().getCaption());

            }
        }

        if (source == addMentors) {            
            addValuesToMentors(mentors, mentorsRoles, expertise);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"MENTORS"));


        }

        if (source == cancelProcess) {
              main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"MENTORS"));
        }



    }

    @Override
    public void valueChange(ValueChangeEvent event) {

        mentorsRole.setStyleName(Runo.LABEL_H2);
        Property property = event.getProperty();
        if (property == peopleList) {
            rightWindow.removeAllComponents();
            mentors = (Collection<Long>) property.getValue();
            checkBoxpanel.removeAllComponents();
            List<MentorsRoles> roles = data.getMentorsRolesService().findAll();
            for (MentorsRoles role : roles) {
                CheckBox cb = new CheckBox(role.getMentorsRolesName());
                cb.setDescription(role.getMentorsRolesName());
                cb.setImmediate(true);
                cb.addListener((ClickListener) this);
                checkBoxpanel.addComponent(cb);
            }

            rightWindow.addComponent(checkBoxpanel);

        }

        if (property == expertiseList) {
            expertise = (Collection<Long>) property.getValue();
        }

    }

    private void addValuesToMentors(Collection<Long> mentors, Set<String> mentorsRoles, Collection<Long> expertise) {
        for (Long mentorId : mentors) {
            Person mentor = data.getPersonService().find(mentorId);
            addRoles(mentor,mentorsRoles);
            addExpertise(mentor,expertise);
           
            
        }
    }
    private void addRoles(Person mentor, Set<String> mentorsRoles) {
        PersonRoles role= new PersonRoles();
        if (mentorsRoles!=null) {
            for (String string : mentorsRoles) {
                role.setRoleName(string);
                mentor.getPersonRoles().add(role); 
                data.getPersonService().merge(mentor);
            }
        }
    }
    private void addExpertise(Person mentor, Collection<Long> expertise) {
        MentorExpertiseArea area = new MentorExpertiseArea();
        if (expertise!=null) {
            for (Long id : expertise) {
                TrainingCourseCategory expert = data.getTrainingCourseCategoryService().find(id);
                area.setExpertiseAreaName(expert.getCategoryName());
                mentor.getExpertiseArea().add(area);
                data.getPersonService().merge(mentor);
            }
        }
    }
}
