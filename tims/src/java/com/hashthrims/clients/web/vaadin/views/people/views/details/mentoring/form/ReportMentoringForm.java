/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.traininglist.SessionType;
import com.hashthrims.domain.traininglist.Mentors;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
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
import java.util.List;

/**
 *
 * @author boniface
 */
public class ReportMentoringForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private final static ClientDataService data = new ClientDataService();

    public ReportMentoringForm() {
    }

    public Form createReportMentoringFrom() {
        final Form form = new MentoringSessionGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new MentoringSessionFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.addComponent(update);
        footer.addComponent(delete);
        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:
        edit.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    class MentoringSessionFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCompetencyType;
        private Select selectFacilityType;
        private Select selectFacilities;
        private Select selectSessionType;
        private ListSelect selectCompetencies;
        private ListSelect selectMentors;
        private List<CompetencyList> competenciesLists;
        private FacilityType facilityTpe;
        private List<Facility> facilities;
        private CompetencyType c;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {


            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Mentoring Session ID:");
                ((TextField) field).setVisible(false);
            } else if ("patientsInitiated".equals(propertyId)) {
                field = new TextField("Patients Initiated:");
                ((TextField) field).setColumns(19);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("sessionMentors".equals(propertyId)) {
                List<Mentors> mentors = data.getMentorsService().findAll();
                selectMentors = new ListSelect("Session Mentors:");
                selectMentors.setImmediate(true);
                for (Mentors mentor : mentors) {
                    selectMentors.addItem(mentor.getId());
                    selectMentors.setItemCaption(mentor.getId(), mentor.getFirstName() + " " + mentor.getLastName());
                }
                selectMentors.setNewItemsAllowed(false);
                selectMentors.setWidth("500");
                selectMentors.setHeight("100");
                selectMentors.setNullSelectionAllowed(false);
                selectMentors.setMultiSelect(true);
                selectMentors.setImmediate(true);
                return selectMentors;
            } else if ("hoursSpent".equals(propertyId)) {
                field = new TextField("Number of Hours:");
                ((TextField) field).setColumns(19);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("mentoringCompetencies".equals(propertyId)) {
                selectCompetencies = new ListSelect("Mentoring Competencies:");
                selectCompetencies.setNewItemsAllowed(false);
                selectCompetencies.setWidth("500");
                selectCompetencies.setHeight("100");
                selectCompetencies.setRequired(true);
                selectCompetencies.setNullSelectionAllowed(false);
                selectCompetencies.setMultiSelect(true);
                selectCompetencies.setImmediate(true);
                if (c != null) {
                    competenciesLists = c.getCompetencyList();
                    for (CompetencyList competencyList : competenciesLists) {
                        selectCompetencies.addItem(competencyList.getComp_name());
                    }
                } else {
                }
                return selectCompetencies;

            } else if ("faciltyType".equals(propertyId)) {
                List<FacilityType> facilityTypes = data.getFacilityTypeService().findAll();
                selectFacilityType = new Select("Venue Type:");
                for (FacilityType facilityType : facilityTypes) {
                    selectFacilityType.addItem(facilityType.getId());
                    selectFacilityType.setItemCaption(facilityType.getId(), facilityType.getFacilityName());
                }
                selectFacilityType.setNewItemsAllowed(false);
                selectFacilityType.addListener(this);
                selectFacilityType.setImmediate(true);
                selectFacilityType.setWidth("250");
                selectFacilityType.setRequired(true);
                return selectFacilityType;
            } else if ("typeOfSession".equals(propertyId)) {
                List<SessionType> sts = data.getMentoringSessionTypeService().findAll();
                selectSessionType = new Select("Session Type:");
                for (SessionType st : sts) {
                    selectSessionType.addItem(st.getId());
                    selectSessionType.setItemCaption(st.getId(), st.getSessionTypeName());
                }
                selectSessionType.setImmediate(true);
                selectSessionType.setNewItemsAllowed(true);
                selectSessionType.setWidth("250");
                selectSessionType.setRequired(true);
                return selectSessionType;
            } else if ("competencyType".equals(propertyId)) {
                List<CompetencyType> fields = data.getCompetencyTypeService().findAll();
                selectCompetencyType = new Select("Subject Area:");
                selectCompetencyType.addListener(this);
                selectCompetencyType.setImmediate(true);
                for (CompetencyType fd : fields) {
                    selectCompetencyType.addItem(fd.getId());
                    selectCompetencyType.setItemCaption(fd.getId(), fd.getComp_name_typ());
                }
                selectCompetencyType.setNewItemsAllowed(false);
                selectCompetencyType.setWidth("500");
                selectCompetencyType.setRequired(true);
                return selectCompetencyType;
            } else if ("venue".equals(propertyId)) {
                selectFacilities = new Select("Mentoring Venue:");
                selectFacilities.setNewItemsAllowed(false);
                selectFacilities.setWidth("250");
                selectFacilities.setRequired(true);
                selectFacilities.setNullSelectionAllowed(false);
                selectFacilities.setImmediate(true);
                selectFacilities.setNullSelectionAllowed(false);
                if (facilityTpe != null) {
                    facilities = facilityTpe.getFacility();
                    for (Facility facility : facilities) {
                        selectFacilities.addItem(facility.getId());
                        selectFacilities.setItemCaption(facility.getId(), facility.getFacilityName());
                    }
                } else {
                }
                return selectFacilities;
            }

            return field;
        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();
            if (property == selectCompetencyType) {
                if (property.getValue() != null) {
                    c = data.getCompetencyTypeService().find(new Long(property.getValue().toString()));
                    competenciesLists = c.getCompetencyList();
                    if (!selectCompetencies.isReadOnly()) {
                        selectCompetencies.removeAllItems();
                    }
                    for (CompetencyList list : competenciesLists) {
                        selectCompetencies.addItem(list.getComp_name());
                    }
                } else {
                    if (!selectCompetencies.isReadOnly()) {
                        selectCompetencies.removeAllItems();
                    }
                }
            }
            if (property == selectFacilityType) {
                if (property.getValue() != null) {
                    facilityTpe = data.getFacilityTypeService().find(new Long(property.getValue().toString()));
                    facilities = facilityTpe.getFacility();
                    if (!selectFacilities.isReadOnly()) {
                        selectFacilities.removeAllItems();
                    }
                    for (Facility facility : facilities) {
                        selectFacilities.addItem(facility.getId());
                        selectFacilities.setItemCaption(facility.getId(), facility.getFacilityName());
                    }
                } else {
                    if (!selectFacilities.isReadOnly()) {
                        selectFacilities.removeAllItems();
                    }
                }
            }
        }
    }

    class MentoringSessionGridForm extends Form {

        private GridLayout layout;

        public MentoringSessionGridForm() {
            setCaption("Report Mentoring Form");
            layout = new GridLayout(2, 8);
            layout.setMargin(true);
            layout.setSpacing(true);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Mentoring Session");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);
            HorizontalLayout secondHeader = new HorizontalLayout();
            secondHeader.setSizeFull();
            secondHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label secondHeaderLabel = new Label("Session");
            secondHeaderLabel.setStyleName(Runo.LABEL_H2);
            secondHeader.addComponent(secondHeaderLabel);
            setLayout(layout);
        }

        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("mentoringDate")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("patientsInitiated")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("faciltyType")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("venue")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("typeOfSession")) { 
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("hoursSpent")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("competencyType")) {
                layout.addComponent(field, 0, 4, 1, 4);
            } else if (propertyId.equals("mentoringCompetencies")) {
                layout.addComponent(field, 0, 5, 1, 5);
            } else if (propertyId.equals("sessionMentors")) {
                layout.addComponent(field, 0, 6, 1, 6);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 7);
            }


        }
    }

    /**
     * @return the delete
     */
    public Button getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(Button delete) {
        this.delete = delete;
    }

    /**
     * @return the save
     */
    public Button getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(Button save) {
        this.save = save;
    }

    /**
     * @return the edit
     */
    public Button getEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(Button edit) {
        this.edit = edit;
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

    /**
     * @return the update
     */
    public Button getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Button update) {
        this.update = update;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }
}
