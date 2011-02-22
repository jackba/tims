/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.contacts;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form.ContactsBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form.ContactsForm;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;

/**
 *
 * @author boniface
 */
public class ContactsWindow extends Window implements ClickListener {

    private ContactsForm pform = new ContactsForm();
    private HashThrimsMain main;
    private Person person;
    private static ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private Form formData;

    public ContactsWindow(Person p, ContactsBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        Form form = pform.createContactsFrom();
        BeanItem item = new BeanItem(bean);

        pform.getSave().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        form.setItemDataSource(item);
        formData = form;
        setModal(true);
        setClosable(true);
        setHeight("500px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Add or Edit Contacts");
        if (bean.getId() != null) {
            pform.getUpdate().setVisible(true);
        } else {
            pform.getSave().setVisible(true);
        }
        addComponent(form);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == pform.getCancel()) {
            close();
        } else if (source == pform.getUpdate()) {
            updateContatcs(formData);
            main.mainView.setSecondComponent(new PersonDetailsView(person, main,"CONTACTS"));
            close();

        } else if (source == pform.getSave()) {
            saveContacts(formData);
            main.mainView.setSecondComponent(new PersonDetailsView(person, main,"CONTACTS"));
            close();

        }

    }

    private void updateContatcs(Form formData) {
        Long personId = Long.parseLong(formData.getField("id").getValue().toString());

        String mailingAddress = fieldValues.getStringFields(formData.getField("mailingAddress").getValue());
        String telephoneNumber = fieldValues.getStringFields(formData.getField("telephoneNumber").getValue());
        String cellnumber = fieldValues.getStringFields(formData.getField("cellnumber").getValue());
        String faxnumber = fieldValues.getStringFields(formData.getField("faxnumber").getValue());
        String email = fieldValues.getStringFields(formData.getField("email").getValue());
        String notes = fieldValues.getStringFields(formData.getField("notes").getValue());
        String addressType = fieldValues.getStringFields(formData.getField("addressType").getValue());

        Contacts c = data.getContactsService().find(personId);
        c.setAddressType(addressType);
        c.setCellnumber(cellnumber);
        c.setEmail(email);
        c.setFaxnumber(faxnumber);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);

        person.getContacts().add(c);
        data.getPersonService().merge(person);
       

    }

    private void saveContacts(Form formData) {
        String mailingAddress = fieldValues.getStringFields(formData.getField("mailingAddress").getValue());
        String telephoneNumber = fieldValues.getStringFields(formData.getField("telephoneNumber").getValue());
        String cellnumber = fieldValues.getStringFields(formData.getField("cellnumber").getValue());
        String faxnumber = fieldValues.getStringFields(formData.getField("faxnumber").getValue());
        String email = fieldValues.getStringFields(formData.getField("email").getValue());
        String notes = fieldValues.getStringFields(formData.getField("notes").getValue());
        String addressType = fieldValues.getStringFields(formData.getField("addressType").getValue());

        Contacts c = new Contacts();
        c.setAddressType(addressType);
        c.setCellnumber(cellnumber);
        c.setEmail(email);
        c.setFaxnumber(faxnumber);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);
        person.getContacts().add(c);
        data.getPersonService().merge(person);
    }
}
