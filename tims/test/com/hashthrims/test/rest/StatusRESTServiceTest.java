/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.StatusForm;
import com.hashthrims.domain.positions.Status;
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
public class StatusRESTServiceTest {
public StatusRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/statusservice/");
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
    public void readStatus() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/statusservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Status status = client.path("hashthrims/1770").accept("application/xml").get(Status.class);
        Assert.assertNotNull(status);
    }

    @Test
    public void updateStatus() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/statusservice/");
        // You have to Supply the ID that is in the DB /status/{ID} for the Test to pass
        Status voucher = client.path("status/1770").accept("application/xml").get(Status.class);

        //Now Update the Status
        client.back(true);
        client.path("/status/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Status newstatus = client.path("status/1770").accept("application/xml").get(Status.class);

    }


    public void printStatuses(){

        //Code Invetory

    }

    // @Test
    public void testCreateStatuses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/statusservice/");
        client.path("status");
        StatusForm f = new  StatusForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
