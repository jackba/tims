/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.regionlist.County;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CountyForm;
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
public class CountyRestTest {

    public CountyRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/county/ws/countyservice/");
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
   public void readCounty()
   {
      WebClient client = WebClient.create("http://localhost:8084/county/ws/countyservice/");

      County county = client.path("county/1770").accept("application/xml").get(County.class);
      Assert.assertNotNull(county);
   }

   @Test
   public void updateCounty()
   {
     WebClient client = WebClient.create("http://localhost:8084/county/ws/countyservice/");
     County county = client.path("PATH/1770").accept("application/xml").get(County.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(county);
     client.back(true);

    County newCounty = client.path("PATH/1770").accept("application/xml").get(County.class);

   }
   public void testCreateCounty()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashthrims/countyservice/");
    client.path("PATH");
    CountyForm pf = new CountyForm();



    client.post(pf);
    client.back(true);


   }
   public void printCounty()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashthrims/countyservice/");
     CountyForm pf = new CountyForm();


   }



}