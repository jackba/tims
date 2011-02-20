/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.rest;


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ContactsForm;
import com.hashthrims.domain.Contacts;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author boniface
 */
public class ContactsRESTServiceTest {

    public ContactsRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/contactsservice/");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //Test requires the Application to be running in the Container
    //
    @Test
    public void readContacts() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/contactsservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Contacts contacts = client.path("hashthrims/1770").accept("application/xml").get(Contacts.class);
        Assert.assertNotNull(contacts);
    }

    @Test
    public void updateContacts() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/contactsservice/");
        // You have to Supply the ID that is in the DB /contacts/{ID} for the Test to pass
        Contacts voucher = client.path("contacts/1770").accept("application/xml").get(Contacts.class);
        
        //Now Update the Contacts
        client.back(true);
        client.path("/contacts/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Contacts newcontacts = client.path("contacts/1770").accept("application/xml").get(Contacts.class);
        
    }


    public void printContactses(){

        //Code Invetory 

    }

    // @Test
    public void testCreateContactses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/contactsservice/");
        client.path("contacts");
        ContactsForm f = new  ContactsForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
