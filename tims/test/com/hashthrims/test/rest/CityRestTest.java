/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.regionlist.City;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CityForm;
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
public class CityRestTest {

    public CityRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/city/ws/cityservice/");
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
   public void readCity()
   {
      WebClient client = WebClient.create("http://localhost:8084/city/ws/cityservice/");

      City city = client.path("city/1770").accept("application/xml").get(City.class);
      Assert.assertNotNull(city);
   }

   @Test
   public void updateCity()
   {
     WebClient client = WebClient.create("http://localhost:8084/city/ws/cityservice/");
     City city = client.path("PATH/1770").accept("application/xml").get(City.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(city);
     client.back(true);

    City newCity = client.path("PATH/1770").accept("application/xml").get(City.class);

   }
   public void testCreateCity()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashthrims/cityservice/");
    client.path("PATH");
    CityForm pf = new CityForm();



    client.post(pf);
    client.back(true);


   }
   public void printCity()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashthrims/cityservice/");
     CityForm pf = new CityForm();


   }



}