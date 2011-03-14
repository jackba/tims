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
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class UpdateProfileForm {

    // Define Buttons
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;

    public UpdateProfileForm() {
    }

    public Form createUserForm() {
        final Form form = new Form();
        form.setCaption("Update Profile");
        form.setImmediate(false);
        form.setFormFieldFactory(new UsersFieldFactory());

        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);

       
        footer.addComponent(edit);
        footer.addComponent(update);
        footer.addComponent(cancel);

        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:
        edit.setVisible(true);
        update.setVisible(false);

        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    public List orderList() {
        final List order = new ArrayList();
        order.add("id");
        order.add("email");
        order.add("firstname");
        order.add("lastname");
        order.add("middlename");


        return order;
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


        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
            if ("email".equals(propertyId)) {
                field = new TextField("Username(Email Address):");
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
            } else if ("id".equals(propertyId)) {
                field = new TextField("User ID:");
                ((TextField) field).setVisible(false);
            }
            return field;
        }
    }
}
