/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ApplicationsForm;
import com.hashthrims.domain.Applications;
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
public class ApplicationsRESTServiceTest {
    public ApplicationsRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/applicationsservice/");
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
    public void readApplications() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/applicationsservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the project name or the class name
        Applications applications = client.path("hashthrims/1770").accept("application/xml").get(Applications.class);
        Assert.assertNotNull(applications);
    }

    @Test
    public void updateApplications() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/applicationsservice/");
        // You have to Supply the ID that is in the DB /applications/{ID} for the Test to pass
        Applications voucher = client.path("applications/1770").accept("application/xml").get(Applications.class);

        //Now Update the Applications
        client.back(true);
        client.path("/applications/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Applications newapplications = client.path("applications/1770").accept("application/xml").get(Applications.class);

    }


    public void printApplicationses(){

        //Code Invetory

    }

    // @Test
    public void testCreateApplicationses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/applicationsservice/");
        client.path("applications");
        ApplicationsForm f = new  ApplicationsForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
