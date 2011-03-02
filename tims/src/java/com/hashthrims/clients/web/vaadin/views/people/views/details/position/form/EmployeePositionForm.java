/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.position.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.util.TimsUtil;
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
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EmployeePositionForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public EmployeePositionForm() {
    }

    public Form createEmployeePositionFrom() {
        Form form = new EmployeePositionGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new EmployeePositionFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
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

    class EmployeePositionFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectPosition;
        private Select selectStatus;
        private Facility facility;
        private List<Positions> positions;
        private Select selectFacilityList;
        private Select selectPositionList;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("id".equals(propertyId)) {
                field = new TextField("Position ID:");
                ((TextField) field).setVisible(false);
            } else if ("status".equals(propertyId)) {
                List<Status> statuses = data.getStatusService().findAll();

                selectStatus = new Select("Postion Status");
                for (Status status : statuses) {
                    if ((status.getStatus().equals("CURRENT") || status.getStatus().equals("OLD"))) {
                        selectStatus.addItem(status.getStatus());
                    }
                }
                selectStatus.setNewItemsAllowed(false);
                selectStatus.addListener(this);
                selectStatus.setImmediate(true);
                selectStatus.setWidth("250");
                selectStatus.setRequired(true);
                return selectStatus;
            } else if ("facility".equals(propertyId)) {
                List<Facility> facilities = data.getFacilityService().findAll();
                Collections.sort(facilities);
                selectFacilityList = new Select("Facility");
                for (Facility fac : facilities) {
                    selectFacilityList.addItem(fac.getId());
                    selectFacilityList.setItemCaption(fac.getId(), fac.getFacilityName());
                }
                selectFacilityList.setNewItemsAllowed(false);
                selectFacilityList.addListener(this);
                selectFacilityList.setImmediate(true);
                selectFacilityList.setWidth("250");

                return selectFacilityList;
            } else if ("position".equals(propertyId)) {

                selectPositionList = new Select("Position");
                selectPositionList.setNewItemsAllowed(false);
                selectPositionList.addListener(this);
                if (facility != null) {
                    positions = facility.getPositions();
                    for (Positions pos : positions) {

                        if (st.checkPositionAvalaibality(pos)) {
                            selectPositionList.addItem(pos.getId());
                            selectPositionList.setItemCaption(pos.getId(), pos.getPositionCode());
                        }
                    }
                } else {
                }
                selectPositionList.setImmediate(true);
                selectPositionList.setWidth("250");

                return selectPositionList;
            } else if ("startDate".equals(propertyId)) {
                field = new DateField("Date Started:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("enddate".equals(propertyId)) {
                field = new DateField("Date Ended:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();
            if (property == selectFacilityList) {
                if (property.getValue() != null) {
                    facility = data.getFacilityService().find(Long.parseLong(property.getValue().toString()));
                    positions = facility.getPositions();
                    if (!selectPositionList.isReadOnly()) {
                        selectPositionList.removeAllItems();
                    }
                    for (Positions pos : positions) {
                        if (st.checkPositionAvalaibality(pos)) {
                            selectPositionList.addItem(pos.getId());
                            selectPositionList.setItemCaption(pos.getId(), pos.getPositionCode());
                        }
                    }
                } else {
                    if (!selectPositionList.isReadOnly()) {
                        selectPositionList.removeAllItems();
                    }
                }
            }

        }
    }

    class EmployeePositionGridForm extends Form {

        private GridLayout layout;

        public EmployeePositionGridForm() {
            setCaption("Set Position Form");
            layout = new GridLayout(2, 9);
            layout.setMargin(true);
            layout.setSpacing(true);

            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Employee Position Information");
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

            if (propertyId.equals("position")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("status")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("startDate")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("enddate")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("facility")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 1, 3);
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

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }
}
