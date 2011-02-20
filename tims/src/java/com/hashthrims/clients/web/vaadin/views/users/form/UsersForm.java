/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.form;

import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class UsersForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;

    public UsersForm() {
    }

    public Form createCadreFrom() {
        final Form form = new Form();
        form.setCaption("Users");
        form.setImmediate(false);
        form.setFormFieldFactory(new UsersFieldFactory());

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

    public List orderList() {
        final List order = new ArrayList();
        order.add("id");
        order.add("email");
        order.add("passWord");
        order.add("firstname");
        order.add("lastname");
        order.add("middlename");
        order.add("enabled");
        order.add("roles");

        return order;
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

    static class UsersFieldFactory extends DefaultFieldFactory {

        private ListSelect selectTrainees;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
            if ("email".equals(propertyId)) {
                field = new TextField("Email Address:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Cadre");
            } else if ("passwd".equals(propertyId)) {
                field = new TextField("Password:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Cadre");
            } else if ("firstname".equals(propertyId)) {
                field = new TextField("First Name:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Cadre");
            } else if ("lastname".equals(propertyId)) {
                field = new TextField("Last Name:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Cadre");
            } else if ("middlename".equals(propertyId)) {
                field = new TextField("Middle Name:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Cadre");
            } else if ("enabled".equals(propertyId)) {
                field = new TextField("Activate Account:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Cadre");
            } else if ("roles".equals(propertyId)) {

                selectTrainees = new ListSelect("Select Roles:");
                selectTrainees.setImmediate(true);

                selectTrainees.addItem("ROLE_ADMIN");
                selectTrainees.setItemCaption("ROLE_ADMIN", "Administrator");

                selectTrainees.addItem("ROLE_USER");
                selectTrainees.setItemCaption("ROLE_USER", "System User");

                selectTrainees.addItem("ROLE_MANAGER");
                selectTrainees.setItemCaption("ROLE_MANAGER", "System Manager");

                selectTrainees.addItem("ROLE_ADMIN");
                selectTrainees.setItemCaption("ROLE_TRAINER", "Trainer");

                selectTrainees.setNewItemsAllowed(false);
                selectTrainees.setWidth("500");
                selectTrainees.setHeight("100");
                selectTrainees.setNullSelectionAllowed(false);
                selectTrainees.setMultiSelect(true);
                selectTrainees.setImmediate(true);
                return selectTrainees;
            } else if ("id".equals(propertyId)) {
                field = new TextField("Cadre ID:");
                ((TextField) field).setVisible(false);
            }
            return field;
        }
    }
}
