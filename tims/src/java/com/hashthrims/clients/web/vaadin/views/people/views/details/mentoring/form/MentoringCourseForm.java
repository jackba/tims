/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.traininglist.MentoringSession;
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
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.List;

/**
 *
 * @author boniface
 */
public class MentoringCourseForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer = new HorizontalLayout();
    private static ClientDataService data = new ClientDataService();

    public MentoringCourseForm() {
    }

    public Form createMentoringFrom() {
        Form form = new MentoringGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new MentoringFieldFactory());


        // Add Listeners


        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(update);
        footer.addComponent(cancel);


        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:

        update.setVisible(false);
        save.setVisible(false);
        form.setWriteThrough(false);
        form.setFooter(footer);
        return form;
    }

    class MentoringFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCourse;
        private Select selectFacilityType;
        private Select selectFacilities;
        private FacilityType facilityTpe;
        private List<Facility> facilities;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("id".equals(propertyId)) {
                field = new TextField("Mentoring Session  ID:");
                ((TextField) field).setVisible(false);
            } else if ("mentoringSession".equals(propertyId)) {
                List<MentoringSession> ms = data.getMentoringSessionService().findAll();
                selectCourse = new Select("Mentoring Session:");
                for (MentoringSession co : ms) {
                    selectCourse.addItem(co.getId());
                    selectCourse.setItemCaption(co.getId(),co.getSessionName());
                }
                selectCourse.setNewItemsAllowed(false);
                selectCourse.setNullSelectionAllowed(false);
                selectCourse.addListener(this);
                selectCourse.setImmediate(true);
                selectCourse.setWidth("250");
                selectCourse.setRequired(true);
                return selectCourse;
            } else if ("faciltyType".equals(propertyId)) {
                List<FacilityType> facilityTypes = data.getFacilityTypeService().findAll();
                selectFacilityType = new Select("Venue Type:");
                for (FacilityType facilityType : facilityTypes) {
                    selectFacilityType.addItem(facilityType.getId());
                     selectFacilityType.setItemCaption(facilityType.getId(),facilityType.getFacilityName());
                }
                selectFacilityType.setNewItemsAllowed(false);
                selectFacilityType.addListener(this);
                selectFacilityType.setImmediate(true);
                selectFacilityType.setWidth("250");
                selectFacilityType.setRequired(true);
                return selectFacilityType;
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
                        selectFacilities.setItemCaption(facility.getId(),facility.getFacilityName());
                    }
                } else {
                }
                return selectFacilities;
                

            } else if ("patientsInitiated".equals(propertyId)) {
                field = new TextField("Number of Patients Initiated:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("hoursSpent".equals(propertyId)) {
                field = new TextField("Number of Hours:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            }
            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();
            if (property == selectFacilityType) {
                if (property.getValue() != null) {
                    facilityTpe = data.getFacilityTypeService().find(new Long(property.getValue().toString()));
                    facilities = facilityTpe.getFacility();
//                    if (!selectFacilities.isReadOnly()) {
//                        selectFacilities.removeAllItems();
//                    }
                    for (Facility facility : facilities) {
                        selectFacilities.addItem(facility.getId());
                        selectFacilities.setItemCaption(facility.getId(),facility.getFacilityName());
                    }
                } else {
                    if (!selectFacilities.isReadOnly()) {
                        selectFacilities.removeAllItems();
                    }
                }
            }
        }
    }

    class MentoringGridForm extends Form {

        private GridLayout layout;

        public MentoringGridForm() {
            setCaption("Mentoring Session Form");
            layout = new GridLayout(2, 6);
            layout.setMargin(true);
            layout.setSpacing(true);

            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Mentoring ");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);



            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 5, 1, 5);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("mentoringSession")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("patientsInitiated")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("hoursSpent")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("faciltyType")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("venue")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("mentoringDate")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 1, 4);
            }
        }
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
}
