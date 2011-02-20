/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.FacilityTypeForm;
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
public class FacilityTypeRestTest {

    public FacilityTypeRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/facilityType/ws/facilityTypeservice/");
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
   public void readFacilityType()
   {
      WebClient client = WebClient.create("http://localhost:8084/facilityType/ws/facilityTypeservice/");

      FacilityType facilityType = client.path("facilityType/1770").accept("application/xml").get(FacilityType.class);
      Assert.assertNotNull(facilityType);
   }

   @Test
   public void updateFacilityType()
   {
     WebClient client = WebClient.create("http://localhost:8084/facilityType/ws/facilityTypeservice/");
     FacilityType facilityType = client.path("PATH/1770").accept("application/xml").get(FacilityType.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(facilityType);
     client.back(true);

    FacilityType newFacilityType = client.path("PATH/1770").accept("application/xml").get(FacilityType.class);

   }
   public void testCreateFacilityType()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/facilityTypeservice/");
    client.path("PATH");
    FacilityTypeForm pf = new FacilityTypeForm();



    client.post(pf);
    client.back(true);


   }
   public void printFacilityType()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/facilityTypeservice/");
     FacilityTypeForm pf = new FacilityTypeForm();


   }



}