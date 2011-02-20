/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingForm;
import com.hashthrims.domain.EmployeeMentoring;
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
public class TrainingRESTServiceTest {
 public TrainingRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/trainingservice/");
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
    public void readTraining() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/trainingservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EmployeeMentoring training = client.path("hashthrims/1770").accept("application/xml").get(EmployeeMentoring.class);
        Assert.assertNotNull(training);
    }

    @Test
    public void updateTraining() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/trainingservice/");
        // You have to Supply the ID that is in the DB /training/{ID} for the Test to pass
        EmployeeMentoring voucher = client.path("training/1770").accept("application/xml").get(EmployeeMentoring.class);

        //Now Update the EmployeeMentoring
        client.back(true);
        client.path("/training/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EmployeeMentoring newtraining = client.path("training/1770").accept("application/xml").get(EmployeeMentoring.class);

    }


    public void printTraininges(){

        //Code Invetory

    }

    // @Test
    public void testCreateTraininges() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/trainingservice/");
        client.path("training");
        TrainingForm f = new  TrainingForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
