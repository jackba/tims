/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.IdentitiesForm;
import com.hashthrims.domain.Identities;
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
public class IdentitiesRESTServiceTest {
public IdentitiesRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/identitiesservice/");
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
    public void readIdentities() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/identitiesservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Identities identities = client.path("hashthrims/1770").accept("application/xml").get(Identities.class);
        Assert.assertNotNull(identities);
    }

    @Test
    public void updateIdentities() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/identitiesservice/");
        // You have to Supply the ID that is in the DB /identities/{ID} for the Test to pass
        Identities voucher = client.path("identities/1770").accept("application/xml").get(Identities.class);

        //Now Update the Identities
        client.back(true);
        client.path("/identities/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Identities newidentities = client.path("identities/1770").accept("application/xml").get(Identities.class);

    }


    public void printIdentitieses(){

        //Code Invetory

    }

    // @Test
    public void testCreateIdentitieses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/identitiesservice/");
        client.path("identities");
        IdentitiesForm f = new  IdentitiesForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
