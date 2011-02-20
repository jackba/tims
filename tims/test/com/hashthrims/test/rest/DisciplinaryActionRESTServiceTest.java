/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DisciplinaryActionForm;
import com.hashthrims.domain.DisciplinaryAction;
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
public class DisciplinaryActionRESTServiceTest {
 public DisciplinaryActionRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/disciplinaryActionservice/");
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
    public void readDisciplinaryAction() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/disciplinaryactionservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        DisciplinaryAction disciplinaryAction = client.path("hashthrims/1770").accept("application/xml").get(DisciplinaryAction.class);
        Assert.assertNotNull(disciplinaryAction);
    }

    @Test
    public void updateDisciplinaryAction() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/disciplinaryactionservice/");
        // You have to Supply the ID that is in the DB /disciplinaryAction/{ID} for the Test to pass
        DisciplinaryAction voucher = client.path("disciplinaryAction/1770").accept("application/xml").get(DisciplinaryAction.class);

        //Now Update the DisciplinaryAction
        client.back(true);
        client.path("/disciplinaryAction/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        DisciplinaryAction newdisciplinaryAction = client.path("disciplinaryAction/1770").accept("application/xml").get(DisciplinaryAction.class);

    }


    public void printDisciplinaryActiones(){

        //Code Invetory

    }

    // @Test
    public void testCreateDisciplinaryActiones() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/disciplinaryactionservice/");
        client.path("disciplinaryAction");
        DisciplinaryActionForm f = new  DisciplinaryActionForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
