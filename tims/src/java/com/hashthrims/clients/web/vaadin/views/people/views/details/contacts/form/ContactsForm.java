/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.AddressType;
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
public class ContactsForm {


 // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
   
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public ContactsForm() {
    }

    public Form createContactsFrom() {
        Form form = new ContactsGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new ContactsFieldFactory());


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

    class ContactsFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectAddressTypeList;


       
        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("id".equals(propertyId)) {
                field = new TextField("Contacts ID:");
                ((TextField) field).setVisible(false);
            } else if ("mailingAddress".equals(propertyId)) {
                field = new TextField("Mailing Address:");
              
               ((TextField) field).setColumns(20);
                ((TextField) field).setRows(4);
                ((TextField) field).setNullRepresentation("");
               
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Contacts Name");
            }  else if ("addressType".equals(propertyId)) {
                 List<AddressType> addressTypes = data.getAddressTypeService().findAll();
                selectAddressTypeList = new Select("Address Type:");
                for (AddressType addr : addressTypes) {
                    selectAddressTypeList.addItem(addr.getAddressTypeName());
                }
                selectAddressTypeList.setNewItemsAllowed(false);
                selectAddressTypeList.addListener(this);
                selectAddressTypeList.setImmediate(true);
                selectAddressTypeList.setWidth("250");
                selectAddressTypeList.setRequired(true);
                return selectAddressTypeList;
            } else if ("telephoneNumber".equals(propertyId)) {
                field = new TextField("Telephone Number:");
               
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("cellnumber".equals(propertyId)) {
                field = new TextField("Cell Number:");
               
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
               
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("faxnumber".equals(propertyId)) {
                field = new TextField("fax Number:");
                
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                 ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("email".equals(propertyId)) {
                field = new TextField("Email :");
        
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
             
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("notes".equals(propertyId)) {
                field = new TextField("Notes:");
              
               ((TextField) field).setColumns(20);
                ((TextField) field).setRows(4);
                ((TextField) field).setNullRepresentation("");
             
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Contacts Name");
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

        }
    }

    class ContactsGridForm extends Form {

        private GridLayout layout;



        public ContactsGridForm() {
            setCaption("Contacts Form");
            layout = new GridLayout(2, 7);
            layout.setMargin(true);
            layout.setSpacing(true);

            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Contacts Information");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);



            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 6, 1, 6);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("telephoneNumber")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("cellnumber")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("faxnumber")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("email")) {
                layout.addComponent(field, 1,2);
            } else if (propertyId.equals("mailingAddress")) {
                layout.addComponent(field, 0,3,0,4);
            } else if (propertyId.equals("notes")) {
                layout.addComponent(field, 1, 3,1,4);
            } else if (propertyId.equals("addressType")) {
                layout.addComponent(field, 0, 5);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 1, 5);
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

