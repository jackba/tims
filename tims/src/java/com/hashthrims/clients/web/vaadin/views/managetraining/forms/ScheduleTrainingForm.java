/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.util.GetPeopleInSpecificFacility;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.validator.IntegerValidator;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ScheduleTrainingForm {
    // Define Buttons

    private Button submitListofAttendant = new Button("Schedule the Course");
    private Button updateCources = new Button("Update the Course");
    private Button cancel = new Button("Cancel");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public ScheduleTrainingForm() {
    }

    public Form createShortCourseFrom() {
        Form form = new ManageTrainingGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new ManageTrainingFieldFactory());


        // Add Listeners
        updateCources.setVisible(false);
        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(submitListofAttendant);
        footer.addComponent(updateCources);
        footer.addComponent(cancel);



        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:

        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    /**
     * @return the updateCources
     */
    public Button getUpdateCources() {
        return updateCources;
    }

    /**
     * @param updateCources the updateCources to set
     */
    public void setUpdateCources(Button updateCources) {
        this.updateCources = updateCources;
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    class ManageTrainingFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCourse;
        private Select selectVenue;
        private Select selectRequestors;
        private Select selectFacilities;
        private ListSelect selectTrainers;
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
            } else if ("venue".equals(propertyId)) {
                List<Facility> facilities = data.getFacilityService().findAll();
                Collections.sort(facilities);
                selectVenue = new Select("Venue :");
                for (Facility facility : facilities) {
                    selectVenue.addItem(facility.getId());
                    selectVenue.setItemCaption(facility.getId(), facility.getFacilityName());
                }

                selectVenue.setNewItemsAllowed(false);
                selectVenue.addListener(this);
                selectVenue.setImmediate(true);
                selectVenue.setWidth("250");
                selectVenue.setRequired(true);
                return selectVenue;
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
                ((DateField) field).setDateFormat("dd-MMMM-yyyy");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("courseCapacity".equals(propertyId)) {
                field = new TextField("Maximum Attendees:");
                ((TextField) field).setRequired(true);
                ((TextField) field).addValidator(new IntegerValidator("Value Has to be whole Number"));
                ((TextField) field).setRequiredError("Please Enter Value");
                ((TextField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("courseEndDate".equals(propertyId)) {
                field = new DateField("Date Course Ends:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
                ((DateField) field).setDateFormat("dd-MMMM-yyyy");
            } else if ("courseStartDate".equals(propertyId)) {
                field = new DateField("Date Course Starts:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
                ((DateField) field).setDateFormat("dd-MMMM-yyyy");
            } else if ("trainers".equals(propertyId)) {
                List<Person> trainers = getTrainers(data.getPersonService().findAll());
                Collections.sort(trainers);
                selectTrainers = new ListSelect("Select Trainers:");
                for (Person trainer : trainers) {
                    selectTrainers.addItem(trainer.getId());
                    selectTrainers.setItemCaption(trainer.getId(), trainer.getPersonName() + " " + trainer.getPersonSurname());
                }
                selectTrainers.setNewItemsAllowed(false);
                selectTrainers.setNullSelectionAllowed(false);
                selectTrainers.setMultiSelect(true);
                selectTrainers.addListener(this);
                selectTrainers.setImmediate(true);
                selectTrainers.setWidth("500");
                selectTrainers.setRequired(true);
                return selectTrainers;

            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
        }

        private List<Person> getTrainers(List<Person> people) {
            List<Person> trainers = new ArrayList<Person>();
            for (Person person : people) {
                if (isTrainer(person)) {
                    trainers.add(person);
                }

            }
            return trainers;
        }

        private boolean isTrainer(Person person) {
            boolean isTrainer = false;
            List<PersonRoles> roles = person.getPersonRoles();
            for (PersonRoles role : roles) {
                if (role.getRoleName() != null) {
                    if ("Trainer".equalsIgnoreCase(role.getRoleName())) {
                        isTrainer = true;
                    }
                }
            }
            return isTrainer;
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
            } else if (propertyId.equals("venue")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("requestor")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("dateRequested")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("courseStartDate")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("courseEndDate")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("trainers")) {
                layout.addComponent(field, 0, 5, 1, 5);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 6);
            } else if (propertyId.equals("courseCapacity")) {
                layout.addComponent(field, 0, 4);
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
