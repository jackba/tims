/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationHistoryForm;
import com.hashthrims.domain.EducationHistory;
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
public class EducationHistoryRESTServiceTest {
public EducationHistoryRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/educationhistoryservice/");
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
    public void readEducationHistory() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/educationhistoryservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EducationHistory educationHistory = client.path("hashthrims/1770").accept("application/xml").get(EducationHistory.class);
        Assert.assertNotNull(educationHistory);
    }

    @Test
    public void updateEducationHistory() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/educationhistoryservice/");
        // You have to Supply the ID that is in the DB /educationHistory/{ID} for the Test to pass
        EducationHistory voucher = client.path("educationHistory/1770").accept("application/xml").get(EducationHistory.class);

        //Now Update the EducationHistory
        client.back(true);
        client.path("/educationHistory/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EducationHistory neweducationHistory = client.path("educationHistory/1770").accept("application/xml").get(EducationHistory.class);

    }


    public void printEducationHistories(){

        //Code Invetory

    }

    // @Test
    public void testCreateEducationHistories() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/educationhistoryservice/");
        client.path("educationHistory");
        EducationHistoryForm f = new  EducationHistoryForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
