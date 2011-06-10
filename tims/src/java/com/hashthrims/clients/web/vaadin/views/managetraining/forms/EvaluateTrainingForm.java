/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.util.GetPeopleInSpecificFacility;
import com.hashthrims.clients.web.vaadin.views.managetraining.forms.ManageTrainingForm.ManageTrainingGridForm;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PendingEvaluations;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.offices.Facility;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EvaluateTrainingForm {

    // Define Buttons
    // private Button save = new Button("Save");
    private Button submitEvaluation = new Button("Submit Evaluation for Course Attendees");
    // private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public EvaluateTrainingForm() {
    }

    public Form createShortCourseFrom() {
        Form form = new ManageTrainingGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new ManageTrainingFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(submitEvaluation);



        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:
        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    class ManageTrainingFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectEvaluation;
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
                field = new TextField("Evaluation ID:");
                ((TextField) field).setVisible(false);
            } else if ("evaluation".equals(propertyId)) {
                List<CompetencyEvaluation> competencyEvaluation = data.getCompetencyEvaluationService().findAll();
                selectEvaluation = new Select("Evaluation:");
                for (CompetencyEvaluation co : competencyEvaluation) {
                    selectEvaluation.addItem(co.getId());
                    selectEvaluation.setItemCaption(co.getId(), co.getCompt_type_name());
                }
                selectEvaluation.setNewItemsAllowed(false);
                selectEvaluation.addListener(this);
                selectEvaluation.setImmediate(true);
                selectEvaluation.setWidth("250");
                selectEvaluation.setRequired(true);
                return selectEvaluation;
            } else if ("evaluationDate".equals(propertyId)) {
                field = new DateField("Date Of Evaluation:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("attendees".equals(propertyId)) {
                selectTrainees = new ListSelect("Select Course Attendees To Be Evaluated:");
                selectTrainees.setImmediate(true);
                selectTrainees.setNewItemsAllowed(false);
                selectTrainees.setWidth("500");
                selectTrainees.setHeight("100");
                selectTrainees.setNullSelectionAllowed(false);
                selectTrainees.setMultiSelect(true);
                List<Person> persons = data.getPersonService().findAll();
                List<Person> personsWithPendingEvaluations = getPersonsWithPendingEvaluations(persons);
                Collections.sort(personsWithPendingEvaluations);
                for (Person attendee : personsWithPendingEvaluations) {

                    selectTrainees.addItem(attendee.getId());
                    selectTrainees.setItemCaption(attendee.getId(), attendee.getPersonSurname() + " " + attendee.getPersonName());
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
                if (property.getValue() != null) {
                    List<Person> persons = data.getPersonService().findAll();
                    fac = data.getFacilityService().find(new Long(property.getValue().toString()));
                    personsLists = personIfacility.getPeopleInFacility(persons, fac);
                    for (Person attendee : personsLists) {
                        selectTrainees.addItem(attendee.getId());
                        selectTrainees.setItemCaption(attendee.getId(), attendee.getPersonSurname() + " " + attendee.getPersonName());
                    }
                }
            }
        }

        private List<Person> getPersonsWithPendingEvaluations(List<Person> persons) {
            List<Person> pending = new ArrayList<Person>();
            for (Person person : persons) {
                
                if (person.getCourses()!=null) {
                    List<EmployeeCourses> ecs = person.getCourses();
                    for (EmployeeCourses employeeCourses : ecs) {
                        if (employeeCourses.getEvaluation() == null) {
                            pending.add(person);
                        }
                    }
                }
            }
            return pending;
        }
    }

    class ManageTrainingGridForm extends Form {

        private GridLayout layout;

        public ManageTrainingGridForm() {
            setCaption("Course Evaluation Form");
            layout = new GridLayout(2, 5);
            layout.setMargin(true);
            layout.setSpacing(true);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            PendingEvaluations eve = new PendingEvaluations();
            Label firstHeaderlabel = new Label("Number of Pending Evaluations: " + eve.getPendingEvaluations());
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);
            setLayout(layout);
        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("evaluation")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("evaluationDate")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("attendees")) {
                layout.addComponent(field, 0, 3, 1, 3);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 4);
            }
        }
    }

    /**
     * @return the cancel
     */
    public Button getSubmitEvaluation() {
        return submitEvaluation;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Button cancel) {
        this.submitEvaluation = cancel;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }
}
