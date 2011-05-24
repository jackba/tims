/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.contacts;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form.ContactsBean;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Person;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ContactsTable {

    private Person person;
    private GridLayout table;
    private List<GridLayout> tables;
    private HashThrimsMain main;
    private static ClientDataService data = new ClientDataService();
      private ContactsWindow subwindow;

    public List<GridLayout> getContactsTable(final Person person, HashThrimsMain app) {

        main=app;
        this.person = person;
        //Create Table and Style it
        table = new GridLayout(3, 50);
        table.setSizeFull();


        //Create Arrays for tables;
        tables = new ArrayList<GridLayout>();

        List<Contacts> contacts = person.getContacts();

        int y = -1;

        for (final Contacts contact : contacts) {


            //Register Listerners


            y++;

            //Row 1: Headers
            Label editInfo = new Label("Manage This Information");
            editInfo.addStyleName(Reindeer.LABEL_H2);
            table.addComponent(editInfo, 0, y); //(0,0)


            Label addressType = new Label(contact.getAddressType());
            addressType.addStyleName(Reindeer.LABEL_H2);
            table.addComponent(addressType, 1, y, 2, y);

            //Row 2 Horizontal Line
            y++;
            table.addComponent(new Label("<hr />", Label.CONTENT_XHTML), 0, y, 2, y);

            //Row 3 First Record and Edit Information
            y++;
            Button edit = new Button("Update This Information", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    ContactsBean bean = getBean();
                     subwindow = new ContactsWindow(person, bean, main);
                     main.getMainWindow().addWindow(subwindow);

                }

                private ContactsBean getBean() {
                    ContactsBean bean = new ContactsBean();
                    bean.setAddressType(contact.getAddressType());
                    bean.setCellnumber(contact.getCellnumber());
                    bean.setEmail(contact.getEmail());
                    bean.setFaxnumber(contact.getFaxnumber());
                    bean.setId(contact.getId());
                    bean.setMailingAddress(contact.getMailingAddress());
                    bean.setNotes(contact.getNotes());
                    bean.setTelephoneNumber(contact.getTelephoneNumber());
                    return bean;
                }

            });
            edit.addStyleName(Reindeer.BUTTON_LINK);
            table.addComponent(edit, 0, y);
            table.addComponent(new Label("Cell Number: "), 1, y);
            Label cellNumber = new Label(contact.getCellnumber());
            cellNumber.addStyleName(Reindeer.LABEL_SMALL);
            cellNumber.addStyleName(Reindeer.LABEL_SMALL);
            table.addComponent(cellNumber, 2, y);

            //Row 3 and Delete Information
            y++;
            Button delete = new Button("Delete This Information", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                   data.getContactsService().remove(contact);
                   tables.clear();
                   main.mainView.setSecondComponent(new PersonDetailsView(person,main,""));

                }
            });
            delete.setData(contact.getId());
            delete.addStyleName("link");
            table.addComponent(delete, 0, y);
            table.addComponent(new Label("Landline : "), 1, y);
            Label landLine = new Label(contact.getTelephoneNumber());
            landLine.addStyleName(Reindeer.LABEL_SMALL);
          
            table.addComponent(landLine, 2, y);

            //Row 4
            y++;
            table.addComponent(new Label("Fax Number: "), 1, y);
            Label faxNumber = new Label(contact.getFaxnumber());
            faxNumber.addStyleName(Reindeer.LABEL_SMALL);
           
            table.addComponent(faxNumber, 2, y);

            //Row 5
            y++;
            table.addComponent(new Label("Email: "), 1, y);
            Label email = new Label(contact.getEmail());
            email.addStyleName(Reindeer.LABEL_SMALL);
           
            table.addComponent(email, 2, y);

            //Row 6
            y++;
            table.addComponent(new Label("Mailing Address: "), 1, y);
            Label mailingAddress = new Label(contact.getMailingAddress());
            mailingAddress.addStyleName(Reindeer.LABEL_SMALL);
           
            table.addComponent(mailingAddress, 2, y);

            //Row 7
            y++;
            table.addComponent(new Label("Notes: "), 1, y);
            Label notes = new Label(contact.getNotes());
            notes.addStyleName(Reindeer.LABEL_SMALL);
           
            table.addComponent(notes, 2, y);

            //Add Grid to tables

        }

        tables.add(table);

        return tables;

    }


    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the table
     */
    public GridLayout getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(GridLayout table) {
        this.table = table;
    }

    /**
     * @return the tables
     */
    public List<GridLayout> getTables() {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(List<GridLayout> tables) {
        this.tables = tables;
    }
}
