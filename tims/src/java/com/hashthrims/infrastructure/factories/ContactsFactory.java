/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.ContactsService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class ContactsFactory {

private ContactsService contactsService;



    private ApplicationContext ctx = GetContext.getApplicationContext();


    public Contacts createContacts(String addressType, String cellNumber, String email, Facility facility, String faxNumber, String mailingAddress, String notes, String telephoneNumber){
        Contacts c = new Contacts();
        c.setAddressType(addressType);
        c.setCellnumber(cellNumber);
        c.setEmail(email);
        //c.setFacility(facility);
        c.setFaxnumber(faxNumber);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);
        return c;
    }

    public Contacts loadContacts(Long id) {
        contactsService = (ContactsService) ctx.getBean("contactsService");
        Contacts c = contactsService.find(id);
        return c;
    }
}
