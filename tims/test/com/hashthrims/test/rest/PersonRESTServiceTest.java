/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PersonForm;
import com.hashthrims.domain.Person;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author abismail
 */
public class PersonRESTServiceTest {
public PersonRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/personservice/");
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
    public void readPerson() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/personservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Person person = client.path("hashthrims/1770").accept("application/xml").get(Person.class);
        Assert.assertNotNull(person);
    }

    @Test
    public void updatePerson() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/personservice/");
        // You have to Supply the ID that is in the DB /person/{ID} for the Test to pass
        Person voucher = client.path("person/1770").accept("application/xml").get(Person.class);

        //Now Update the Person
        client.back(true);
        client.path("/person/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Person newperson = client.path("person/1770").accept("application/xml").get(Person.class);

    }


    public void printPersons(){

        //Code Invetory

    }

    // @Test
    public void testCreatePersons() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/personservice/");
        client.path("person");
        PersonForm f = new  PersonForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
