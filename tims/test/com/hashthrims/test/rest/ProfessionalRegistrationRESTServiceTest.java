/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ProfessionalRegistrationForm;
import com.hashthrims.domain.ProfessionalRegistration;
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
public class ProfessionalRegistrationRESTServiceTest {
public ProfessionalRegistrationRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/professionalregistrationservice/");
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
    public void readProfessionalRegistration() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/professionalregistrationservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        ProfessionalRegistration professionalRegistration = client.path("hashthrims/1770").accept("application/xml").get(ProfessionalRegistration.class);
        Assert.assertNotNull(professionalRegistration);
    }

    @Test
    public void updateProfessionalRegistration() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/professionalregistrationservice/");
        // You have to Supply the ID that is in the DB /professionalRegistration/{ID} for the Test to pass
        ProfessionalRegistration voucher = client.path("professionalRegistration/1770").accept("application/xml").get(ProfessionalRegistration.class);

        //Now Update the ProfessionalRegistration
        client.back(true);
        client.path("/professionalRegistration/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        ProfessionalRegistration newprofessionalRegistration = client.path("professionalRegistration/1770").accept("application/xml").get(ProfessionalRegistration.class);

    }


    public void printProfessionalRegistrations(){

        //Code Invetory

    }

    // @Test
    public void testCreateProfessionalRegistrations() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/professionalregistrationservice/");
        client.path("professionalRegistration");
        ProfessionalRegistrationForm f = new  ProfessionalRegistrationForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
