/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.regionlist.Currency;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CurrencyForm;
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
public class CurrencyRestTest {

    public CurrencyRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/currency/ws/currencyservice/");
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
   public void readCurrency()
   {
      WebClient client = WebClient.create("http://localhost:8084/currency/ws/currencyservice/");

      Currency currency = client.path("currency/1770").accept("application/xml").get(Currency.class);
      Assert.assertNotNull(currency);
   }

   @Test
   public void updateCurrency()
   {
     WebClient client = WebClient.create("http://localhost:8084/currency/ws/currencyservice/");
     Currency currency = client.path("PATH/1770").accept("application/xml").get(Currency.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(currency);
     client.back(true);

    Currency newCurrency = client.path("PATH/1770").accept("application/xml").get(Currency.class);

   }
   public void testCreateCurrency()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashthrims/currencyservice/");
    client.path("PATH");
    CurrencyForm pf = new CurrencyForm();



    client.post(pf);
    client.back(true);


   }
   public void printCurrency()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashthrims/currencyservice/");
     CurrencyForm pf = new CurrencyForm();


   }



}