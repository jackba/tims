/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DepartureReasonsForm;
import com.hashthrims.domain.employeelist.DepartureReasons;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author stud
 */
public class DepartureReasonsRestTest {

    public DepartureReasonsRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/departureReasons/ws/departureReasonsservice/");
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
   @Test
   public void readDepartureReasons()
   {
      WebClient client = WebClient.create("http://localhost:8084/departureReasons/ws/departureReasonsservice/");

      DepartureReasons departureReasons = client.path("departureReasons/1770").accept("application/xml").get(DepartureReasons.class);
      Assert.assertNotNull(departureReasons);
   }

   @Test
   public void updateDepartureReasons()
   {
     WebClient client = WebClient.create("http://localhost:8084/departureReasons/ws/departureReasonsservice/");
     DepartureReasons departureReasons = client.path("PATH/1770").accept("application/xml").get(DepartureReasons.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(departureReasons);
     client.back(true);

    DepartureReasons newDepartureReasons = client.path("PATH/1770").accept("application/xml").get(DepartureReasons.class);

   }
   public void testCreateDepartureReasons()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/departureReasonsservice/");
    client.path("PATH");
    DepartureReasonsForm pf = new DepartureReasonsForm();



    client.post(pf);
    client.back(true);


   }
   public void printDepartureReasons()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/departureReasonsservice/");
     DepartureReasonsForm pf = new DepartureReasonsForm();


   }



}