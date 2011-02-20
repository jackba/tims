/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RegionForm;
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
public class RegionRestTest {

    public RegionRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/region/ws/regionservice/");
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
   public void readRegion()
   {
      WebClient client = WebClient.create("http://localhost:8084/region/ws/regionservice/");

      Province region = client.path("region/1770").accept("application/xml").get(Province.class);
      Assert.assertNotNull(region);
   }

   @Test
   public void updateRegion()
   {
     WebClient client = WebClient.create("http://localhost:8084/region/ws/regionservice/");
     Province region = client.path("PATH/1770").accept("application/xml").get(Province.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(region);
     client.back(true);

    Province newRegion = client.path("PATH/1770").accept("application/xml").get(Province.class);

   }
   public void testCreateRegion()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashthrims/regionservice/");
    client.path("PATH");
    RegionForm pf = new RegionForm();



    client.post(pf);
    client.back(true);


   }
   public void printRegion()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashthrims/regionservice/");
     RegionForm pf = new RegionForm();


   }



}