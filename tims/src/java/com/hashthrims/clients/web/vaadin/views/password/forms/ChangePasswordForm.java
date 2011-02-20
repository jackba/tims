/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.password.forms;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.models.ChangePasswordBean;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ChangePasswordForm extends Form implements ClickListener {

    private Button save = new Button("Change Password", (ClickListener) this);
    private boolean newPersonInfoMode = false;
    private HashThrimsMain main;
    private static ClientDataService data;
    //private Form form;
    private HorizontalLayout footer;
    private static final String COMMON_FIELD_WIDTH = "12em";
    private GridLayout ourLayout;

    public ChangePasswordForm(HashThrimsMain app) {


        this.main = app;
        ourLayout = new GridLayout(3, 3);
        ourLayout.setMargin(true, false, false, true);
        ourLayout.setSpacing(true);
       data = new ClientDataService();
        
        setLayout(ourLayout);
        setCaption("Password Information");
        setWriteThrough(false); // we want explicit 'apply'
        setInvalidCommitted(false); // no invalid values in datamodel
        setDescription("Use This Form to Set up the new password");
        ChangePasswordBean bean = new ChangePasswordBean();
        BeanItem item = new BeanItem(bean);
        setItemDataSource(item);
        setFormFieldFactory(new ChangePasswordFieldFactory());

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.setVisible(true);
        footer.setMargin(true);

        // Determines which properties are shown, and in which order:
        List order = new ArrayList();
        order.add("oldPassword");
        order.add("newPassword");
        order.add("confirmPassword");
      
        setVisibleItemProperties(order);
        setWriteThrough(false);

        setFooter(footer);
        setStyleName(Runo.PANEL_LIGHT);
        setVisible(true);

    }

    @Override
    protected void attachField(Object propertyId, Field field) {
        if (propertyId.equals("oldPassword")) {
            ourLayout.addComponent(field, 0, 0);
        } else if (propertyId.equals("newPassword")) {
            ourLayout.addComponent(field, 0, 1);
        } else if (propertyId.equals("confirmPassword")) {
            ourLayout.addComponent(field, 0, 2);
        } 
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == save) {
            setReadOnly(true);
            save.setVisible(false);
        } 
    }

    static class ChangePasswordFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("oldPassword".equals(propertyId)) {
                field = new TextField("Old Password:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setSecret(true);
                 field.setVisible(true);
            } else if ("newPassword".equals(propertyId)) {
                field = new TextField("New Password:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setSecret(true);
                 field.setVisible(true);

            } else if ("confirmPassword".equals(propertyId)) {
                field = new TextField("Confirm Password");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                 ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setSecret(true);
                 field.setVisible(true);
            } 

            return field;
        }
    }
}
