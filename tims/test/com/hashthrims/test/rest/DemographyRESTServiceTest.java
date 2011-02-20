/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DemographyForm;
import com.hashthrims.domain.Demography;
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
public class DemographyRESTServiceTest {
public DemographyRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/demographyservice/");
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
    public void readDemography() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/demographyservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Demography demography = client.path("hashthrims/1770").accept("application/xml").get(Demography.class);
        Assert.assertNotNull(demography);
    }

    @Test
    public void updateDemography() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/demographyservice/");
        // You have to Supply the ID that is in the DB /demography/{ID} for the Test to pass
        Demography voucher = client.path("demography/1770").accept("application/xml").get(Demography.class);

        //Now Update the Demography
        client.back(true);
        client.path("/demography/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Demography newdemography = client.path("demography/1770").accept("application/xml").get(Demography.class);

    }


    public void printDemographies(){

        //Code Invetory

    }

    // @Test
    public void testCreateDemographies() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/demographyservice/");
        client.path("demography");
        DemographyForm f = new  DemographyForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
