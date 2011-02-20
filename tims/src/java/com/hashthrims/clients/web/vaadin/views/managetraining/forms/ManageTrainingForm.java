/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.util.GetPeopleInSpecificFacility;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ManageTrainingForm {
    // Define Buttons

    private Button submitListofAttendant = new Button("Submit the List of Selected Attendants");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public ManageTrainingForm() {
    }

    public Form createShortCourseFrom() {
        Form form = new ManageTrainingGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new ManageTrainingFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(submitListofAttendant);



        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:

        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    class ManageTrainingFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCourse;
        private Select selectRetraining;
        private Select selectRequestors;
        private Select selectFacilities;
        private ListSelect selectTrainees;
        private List<Person> personsLists;
        private Facility fac;
        private final GetPeopleInSpecificFacility personIfacility = new GetPeopleInSpecificFacility();

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Short Course ID:");
                ((TextField) field).setVisible(false);
            } else if ("course".equals(propertyId)) {
                List<TrainingCourses> courses = data.getTrainingCoursesService().findAll();
                selectCourse = new Select("Course:");
                for (TrainingCourses co : courses) {
                    selectCourse.addItem(co.getId());
                    selectCourse.setItemCaption(co.getId(), co.getCourseName());
                }
                selectCourse.setNewItemsAllowed(false);
                selectCourse.addListener(this);
                selectCourse.setImmediate(true);
                selectCourse.setWidth("250");
                selectCourse.setRequired(true);
                return selectCourse;
            } else if ("retraining".equals(propertyId)) {
                selectRetraining = new Select("Retraining ? :");
                selectRetraining.addItem("YES");
                selectRetraining.addItem("NO");
                selectRetraining.setNewItemsAllowed(false);
                selectRetraining.addListener(this);
                selectRetraining.setImmediate(true);
                selectRetraining.setWidth("250");
                selectRetraining.setRequired(true);
                return selectRetraining;
            } else if ("requestor".equals(propertyId)) {
                List<TrainingCourseRequestors> requestors = data.getTrainingCourseRequestorsType().findAll();
                selectRequestors = new Select("Requestors:");
                for (TrainingCourseRequestors request : requestors) {
                    selectRequestors.addItem(request.getId());
                    selectRequestors.setItemCaption(request.getId(), request.getRequestorName());
                }
                selectRequestors.setNewItemsAllowed(false);
                selectRequestors.addListener(this);
                selectRequestors.setImmediate(true);
                selectRequestors.setWidth("250");
                selectRequestors.setRequired(true);
                return selectRequestors;
            } else if ("dateRequested".equals(propertyId)) {
                field = new DateField("Date Training Requested:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("courseEndDate".equals(propertyId)) {
                field = new DateField("Date Course Ends:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("courseStartDate".equals(propertyId)) {
                field = new DateField("Date Course Starts:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("facultyId".equals(propertyId)) {
                List<Facility> facilities = data.getFacilityService().findAll();
                selectFacilities = new Select("Select Facility:");
                for (Facility facility : facilities) {
                    selectFacilities.addItem(facility.getId());
                    selectFacilities.setItemCaption(facility.getId(), facility.getFacilityName());
                }
                selectFacilities.setNewItemsAllowed(false);
                selectFacilities.addListener(this);
                selectFacilities.setImmediate(true);
                selectFacilities.setWidth("500");
                selectFacilities.setRequired(true);
                return selectFacilities;
            } else if ("trainees".equals(propertyId)) {
                selectTrainees = new ListSelect("Select Course Attendees:");
                selectTrainees.setImmediate(true);
                selectTrainees.setNewItemsAllowed(false);
                selectTrainees.setWidth("500");
                selectTrainees.setHeight("100");
                selectTrainees.setNullSelectionAllowed(true);
                selectTrainees.setMultiSelect(true);
                List<Person> persons = data.getPersonService().findAll();
                Collections.sort(persons);
                if (fac != null) {
                    
                    personsLists = personIfacility.getPeopleInFacility(persons, fac);
                    for (Person attendee : personsLists) {
                        selectTrainees.addItem(attendee.getId());
                        selectTrainees.setItemCaption(attendee.getId(), attendee.getPersonName() + " " + attendee.getPersonSurname());
                    }
                } else {
                    for (Person person : persons) {
                        selectTrainees.addItem(person.getId());
                        selectTrainees.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
                    }
                }
                selectTrainees.setImmediate(true);
                return selectTrainees;
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();
            if (property == selectFacilities) {
                List<Person> persons = data.getPersonService().findAll();
          
                Collections.sort(persons);
                if (property.getValue() != null) {
                    fac = data.getFacilityService().find(new Long(property.getValue().toString()));
                    personsLists = personIfacility.getPeopleInFacility(persons, fac);
                    selectTrainees.removeAllItems();
                    for (Person attendee : personsLists) {
                        selectTrainees.addItem(attendee.getId());
                        selectTrainees.setItemCaption(attendee.getId(), attendee.getPersonSurname() + " " + attendee.getPersonName());
                    }
                } else {
                    

                }
            }

        }
    }

    class ManageTrainingGridForm extends Form {

        private GridLayout layout;

        public ManageTrainingGridForm() {
            setCaption("Schedule Training Course Form");
            layout = new GridLayout(2, 7);
            layout.setMargin(true);
            layout.setSpacing(true);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Schedule Training Course");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);
            setLayout(layout);
        }

        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("course")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("retraining")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("requestor")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("dateRequested")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("courseStartDate")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("courseEndDate")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("facultyId")) {
                layout.addComponent(field, 0, 4, 1, 4);
            } else if (propertyId.equals("trainees")) {
                layout.addComponent(field, 0, 5, 1, 5);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 6);
            }
        }
    }

    /**
     * @return the cancel
     */
    public Button getSubmitListofAttendant() {
        return submitListofAttendant;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setSubmitListofAttendant(Button cancel) {
        this.submitListofAttendant = cancel;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }
}
