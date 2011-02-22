/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.contacts;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form.ContactsBean;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Person;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ContactsPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button personalContact = ButtonLink.getButton("Add Personal Contact");
//    private final Button workContact = ButtonLink.getButton("Add Work Contact");
//    private final Button emergContact = ButtonLink.getButton("Add Emergency Contact");
//    private final Button otherContact = ButtonLink.getButton("Add Other Contact");
    private final Button editLink = ButtonLink.getButton("Edit");
    private final Button deleteLink = ButtonLink.getButton("Delete");
    private ContactsWindow subwindow;
    private final ClientDataService data = new ClientDataService();

    public ContactsPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        personalContact.addListener((ClickListener) this);
//        workContact.addListener((ClickListener) this);
//        emergContact.addListener((ClickListener) this);
//        otherContact.addListener((ClickListener) this);


        ContactsBean bean = new ContactsBean();
        subwindow = new ContactsWindow(person, bean, main);

        final Label headerLabel = new Label("Contacts :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(personalContact);
//        menu.addComponent(workContact);
//        menu.addComponent(emergContact);
//        menu.addComponent(otherContact);

        menu.setSpacing(true);

        Table table = new Table();
        table.removeAllItems();
        table.setSizeFull();


        List<Contacts> list = null;
        if (person.getContacts()!=null) {
            list = person.getContacts();
        } else {
            list = new ArrayList<Contacts>();
        }
        table.addContainerProperty("Address Type", String.class, null);
        table.addContainerProperty("Telephone Number", String.class, null);
        table.addContainerProperty("Cell Number", String.class, null);
        table.addContainerProperty("Email Address", String.class, null);
        table.addContainerProperty("Fax Number", String.class, null);
        table.addContainerProperty("Mailing Address", String.class, null);
        table.addContainerProperty("Notes", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final Contacts values : list) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    ContactsBean bean = getBean();
                    subwindow = new ContactsWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private ContactsBean getBean() {
                    ContactsBean bean = new ContactsBean();
                    bean.setAddressType(values.getAddressType());
                    bean.setCellnumber(values.getCellnumber());
                    bean.setEmail(values.getEmail());
                    bean.setFaxnumber(values.getFaxnumber());
                    bean.setMailingAddress(values.getMailingAddress());
                    bean.setId(values.getId());
                    bean.setNotes(values.getNotes());
                    bean.setTelephoneNumber(values.getTelephoneNumber());
                    return bean;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    person.getContacts().remove(values);
                    data.getPersonService().merge(person);
                    final PersonDetailsView view = new PersonDetailsView(person, main, "CONTACTS");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        values.getAddressType(),
                        values.getTelephoneNumber(),
                        values.getCellnumber(),
                        values.getEmail(),
                        values.getFaxnumber(),
                        values.getMailingAddress(),
                        values.getNotes(),
                        edit,
                        delete}, values.getId());

        }

        addComponent(menu);
        addComponent(table);
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
        } else if (source == editLink) {
        } else if (source == deleteLink) {
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
