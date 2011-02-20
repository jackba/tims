/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.WorkPlaceAccidentsForm;
import com.hashthrims.domain.WorkPlaceAccidents;
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
public class WorkPlaceAccidentsRESTServiceTest {
public WorkPlaceAccidentsRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/workplaceaccidentsservice/");
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
    public void readWorkPlaceAccidents() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/workplaceaccidentsservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        WorkPlaceAccidents workPlaceAccidents = client.path("hashthrims/1770").accept("application/xml").get(WorkPlaceAccidents.class);
        Assert.assertNotNull(workPlaceAccidents);
    }

    @Test
    public void updateWorkPlaceAccidents() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/workplaceaccidentsservice/");
        // You have to Supply the ID that is in the DB /workPlaceAccidents/{ID} for the Test to pass
        WorkPlaceAccidents voucher = client.path("workPlaceAccidents/1770").accept("application/xml").get(WorkPlaceAccidents.class);

        //Now Update the WorkPlaceAccidents
        client.back(true);
        client.path("/workPlaceAccidents/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        WorkPlaceAccidents newworkPlaceAccidents = client.path("workPlaceAccidents/1770").accept("application/xml").get(WorkPlaceAccidents.class);

    }


    public void printWorkPlaceAccidentses(){

        //Code Invetory

    }

    // @Test
    public void testCreateWorkPlaceAccidentses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/workplaceaccidentsservice/");
        client.path("workPlaceAccidents");
        WorkPlaceAccidentsForm f = new  WorkPlaceAccidentsForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
