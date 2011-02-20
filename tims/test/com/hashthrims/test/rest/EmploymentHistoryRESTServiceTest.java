/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmploymentHistoryForm;
import com.hashthrims.domain.EmploymentHistory;
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
public class EmploymentHistoryRESTServiceTest {
public EmploymentHistoryRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employmenthistoryservice/");
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
    public void readEmploymentHistory() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employmenthistoryservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EmploymentHistory employmenthistory = client.path("hashthrims/1770").accept("application/xml").get(EmploymentHistory.class);
        Assert.assertNotNull(employmenthistory);
    }

    @Test
    public void updateEmploymentHistory() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employmenthistoryservice/");
        // You have to Supply the ID that is in the DB /employmenthistory/{ID} for the Test to pass
        EmploymentHistory voucher = client.path("employmentHistory/1770").accept("application/xml").get(EmploymentHistory.class);

        //Now Update the EmploymentHistory
        client.back(true);
        client.path("/employmentHistory/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EmploymentHistory newemploymenthistory = client.path("employmentHistory/1770").accept("application/xml").get(EmploymentHistory.class);

    }


    public void printEmploymentHistories(){

        //Code Invetory

    }

    // @Test
    public void testCreateEmploymentHistories() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/employmenthistoryservice/");
        client.path("employmentHistory");
        EmploymentHistoryForm f = new  EmploymentHistoryForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
