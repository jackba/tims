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
import com.vaadin.ui.PasswordField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class UserPasswordChangeForm {

    // Define Buttons
    private Button changePassword = new Button("Change Password");
    private Button cancel = new Button("Cancel");
    //Define Footer
    private HorizontalLayout footer;

    public UserPasswordChangeForm() {
    }

    public Form createUserForm() {
        final Form form = new Form();
        form.setCaption("Change Your Password");
        form.setImmediate(false);
        form.setFormFieldFactory(new UsersFieldFactory());

        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(changePassword);
        footer.addComponent(cancel);

        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:

        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    public List orderList() {
        final List order = new ArrayList();
        order.add("currentPassword");
        order.add("newPassword");
        order.add("repeatPassword");
       

        return order;
    }

    /**
     * @return the delete
     */
    
    /**
     * @return the save
     */
    public Button getChangePassword() {
        return changePassword;
    }

    /**
     * @param save the save to set
     */
    public void setChangePassword(Button save) {
        this.changePassword = save;
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
            if ("currentPassword".equals(propertyId)) {
                field = new PasswordField("Current Password:");
                ((PasswordField) field).setColumns(30);
                ((PasswordField) field).setNullRepresentation("");
                ((PasswordField) field).setRequired(true);

                ((PasswordField) field).setRequiredError("Please Enter Cadre");
            } else if ("newPassword".equals(propertyId)) {
                field = new PasswordField("New Password:");
                ((PasswordField) field).setColumns(30);
                ((PasswordField) field).setNullRepresentation("");
                ((PasswordField) field).setRequired(true);
                ((PasswordField) field).setRequiredError("Please Enter Cadre");
            } else if ("repeatPassword".equals(propertyId)) {
                field = new PasswordField("Confirm Password:");
                ((PasswordField) field).setColumns(30);
                ((PasswordField) field).setNullRepresentation("");
                ((PasswordField) field).setRequired(true);
                ((PasswordField) field).setRequiredError("Please Enter Cadre");
            } 
            return field;
        }
    }
}
