/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CountryForm;
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
public class CountryRestTest {

    public CountryRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/country/ws/countryservice/");
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
   public void readCountry()
   {
      WebClient client = WebClient.create("http://localhost:8084/country/ws/countryservice/");

      Country country = client.path("country/1770").accept("application/xml").get(Country.class);
      Assert.assertNotNull(country);
   }

   @Test
   public void updateCountry()
   {
     WebClient client = WebClient.create("http://localhost:8084/country/ws/countryservice/");
     Country country = client.path("PATH/1770").accept("application/xml").get(Country.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(country);
     client.back(true);

    Country newCountry = client.path("PATH/1770").accept("application/xml").get(Country.class);

   }
   public void testCreateCountry()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashthrims/countryservice/");
    client.path("PATH");
    CountryForm pf = new CountryForm();



    client.post(pf);
    client.back(true);


   }
   public void printCountry()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashthrims/countryservice/");
     CountryForm pf = new CountryForm();


   }



}