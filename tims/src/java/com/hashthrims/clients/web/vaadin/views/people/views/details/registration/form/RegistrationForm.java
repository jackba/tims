/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.registration.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.RegistrationBody;
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
import java.util.List;

/**
 *
 * @author boniface
 */
public class RegistrationForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public RegistrationForm() {
    }

    public Form createRegistrationFrom() {
        Form form = new RegistrationGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new RegistrationFieldFactory());


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

    class RegistrationFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectRegistrationBodyList;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("id".equals(propertyId)) {
                field = new TextField("Registration ID:");
                ((TextField) field).setVisible(false);
            } else if ("registrationBody".equals(propertyId)) {
                List<RegistrationBody> registrationBodies = data.getRegistrationBodyService().findAll();
                selectRegistrationBodyList = new Select("Registration Body:");
                for (RegistrationBody registrationBody : registrationBodies) {
                    selectRegistrationBodyList.addItem(registrationBody.getId());
                    selectRegistrationBodyList.setItemCaption(registrationBody.getId(), registrationBody.getName());
                }
                selectRegistrationBodyList.setNewItemsAllowed(false);
                selectRegistrationBodyList.addListener(this);
                selectRegistrationBodyList.setImmediate(true);
                selectRegistrationBodyList.setWidth("250");
                selectRegistrationBodyList.setRequired(true);
                return selectRegistrationBodyList;
            } else if ("registrationNumber".equals(propertyId)) {
                field = new TextField("Registration Number:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("expirationDate".equals(propertyId)) {
                field = new DateField("Expiration Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("registrationDate".equals(propertyId)) {
                field = new DateField("Registration Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("licenceNumber".equals(propertyId)) {
                field = new TextField("Licence Number:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

        }
    }

    class RegistrationGridForm extends Form {

        private GridLayout layout;

        public RegistrationGridForm() {
            setCaption("Registration Form");
            layout = new GridLayout(2, 9);
            layout.setMargin(true);
            layout.setSpacing(true);

            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Registration Information");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("registrationBody")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("registrationNumber")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("licenceNumber")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("registrationDate")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("expirationDate")) {
                layout.addComponent(field, 0, 3);
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
