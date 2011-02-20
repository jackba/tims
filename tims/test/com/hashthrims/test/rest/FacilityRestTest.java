/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.offices.Facility;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.FacilityForm;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author stud
 */
public class FacilityRestTest {

    public FacilityRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/facility/ws/facilityservice/");
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
   public void readFacility()
   {
      WebClient client = WebClient.create("http://localhost:8084/facility/ws/facilityservice/");

      Facility facility = client.path("facility/1770").accept("application/xml").get(Facility.class);
      Assert.assertNotNull(facility);
   }

   @Test
   public void updateFacility()
   {
     WebClient client = WebClient.create("http://localhost:8084/facility/ws/facilityservice/");
     Facility facility = client.path("PATH/1770").accept("application/xml").get(Facility.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(facility);
     client.back(true);

    Facility newFacility = client.path("PATH/1770").accept("application/xml").get(Facility.class);

   }
   public void testCreateFacility()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/facilityservice/");
    client.path("PATH");
    FacilityForm pf = new FacilityForm();



    client.post(pf);
    client.back(true);


   }
   public void printFacility()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/facilityservice/");
     FacilityForm pf = new FacilityForm();


   }



}