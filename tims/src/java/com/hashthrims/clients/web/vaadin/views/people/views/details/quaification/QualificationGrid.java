/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.quaification;

import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.*;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form.ContactsBean;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Person;
import com.vaadin.addon.chameleon.ChameleonTheme;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.GridLayout.OutOfBoundsException;
import com.vaadin.ui.GridLayout.OverlapsException;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.List;

/**
 *
 * @author boniface
 */
public class QualificationGrid implements Button.ClickListener {

    Button personalContact = new Button("Add Personal Contact");
    Button workContact = new Button("Add Work Contact");
    Button emergContact = new Button("Add Emergency Contact");
    Button otherContact = new Button("Add Other Contact");
    private ContactsWindow subwindow;
    private HashThrimsMain main;

    public void addContacts(Person person, GridLayout layout, HashThrimsMain app) throws OverlapsException, OutOfBoundsException {
        //
        main = app;
        ContactsBean bean = new ContactsBean();
        subwindow = new ContactsWindow(person, bean, main);


        //-----------Individual Section heading
        Label contctactInfoLabel = new Label("Contacts Information");
        contctactInfoLabel.addStyleName(ChameleonTheme.LABEL_H2);
        contctactInfoLabel.addStyleName(ChameleonTheme.LABEL_COLOR);
        layout.addComponent(contctactInfoLabel, 0, 6);
        // Table Headers First
        HorizontalLayout contactsToolbar = new HorizontalLayout();
        contactsToolbar.addStyleName(ChameleonTheme.COMPOUND_HORIZONTAL_LAYOUT_SEGMENT_ALTERNATE);


        //Register Listeners
        personalContact.addListener((ClickListener) this);
        workContact.addListener((ClickListener) this);
        emergContact.addListener((ClickListener) this);
        otherContact.addListener((ClickListener) this);

        personalContact.addStyleName(ChameleonTheme.BUTTON_SMALL);
        personalContact.addStyleName(ChameleonTheme.BUTTON_WIDE);

  
        emergContact.addStyleName(ChameleonTheme.BUTTON_SMALL);
        emergContact.addStyleName(ChameleonTheme.BUTTON_WIDE);

     
        otherContact.addStyleName(ChameleonTheme.BUTTON_SMALL);
        otherContact.addStyleName(ChameleonTheme.BUTTON_WIDE);

    
        workContact.addStyleName(ChameleonTheme.BUTTON_SMALL);
        workContact.addStyleName(ChameleonTheme.BUTTON_WIDE);

        //Add Buttons to the bar Conditionally
        List<Contacts> cts = person.getContacts();
        if (cts.size() < 1) {
            contactsToolbar.addComponent(personalContact);
            contactsToolbar.addComponent(workContact);
            contactsToolbar.addComponent(emergContact);
            contactsToolbar.addComponent(otherContact);
        } else {
            for (Contacts contacts : cts) {
                if (contacts.getAddressType().equals("Personal Contact")) {
                    contactsToolbar.addComponent(workContact);
                    contactsToolbar.addComponent(emergContact);
                    contactsToolbar.addComponent(otherContact);
                }
                if (contacts.getAddressType().equals("Work Contact")) {
                    contactsToolbar.addComponent(personalContact);
                    contactsToolbar.addComponent(emergContact);
                    contactsToolbar.addComponent(otherContact);
                }
                if (contacts.getAddressType().equals("Emergency Contact")) {
                    contactsToolbar.addComponent(personalContact);
                    contactsToolbar.addComponent(workContact);
                    contactsToolbar.addComponent(otherContact);
                }
                if (contacts.getAddressType().equals("Other Contact")) {
                    contactsToolbar.addComponent(personalContact);
                    contactsToolbar.addComponent(workContact);
                    contactsToolbar.addComponent(emergContact);
                }
            }
        }

        layout.addComponent(contactsToolbar, 0, 7);
        // Line header
        Label lineBreak2 = new Label("<hr />", Label.CONTENT_XHTML);
        layout.addComponent(lineBreak2, 0, 8);
        //Add the Table Here
        ContactsTable tbl = new ContactsTable();
        List<GridLayout> tbls = tbl.getContactsTable(person,main);
        for (GridLayout contactsTable : tbls) {
            layout.addComponent(contactsTable, 0, 9);
        }
        //Close off with  Line
        layout.addComponent(new Label("<hr />", Label.CONTENT_XHTML), 0, 10);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == personalContact) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        } else if (source == workContact) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        } else if (source == emergContact) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        } else if (source == otherContact) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        }
    }
}
