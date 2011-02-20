/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.BenefitFrequencyForm;
import com.hashthrims.domain.employeelist.BenefitFrequency;
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
public class BenefitFrequencyRestTest {

    public BenefitFrequencyRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/benefitFrequency/ws/benefitfrequencyservice/");
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
   public void readBenefitFrequency()
   {
      WebClient client = WebClient.create("http://localhost:8084/benefitFrequency/ws/benefitfrequencyservice/");

      BenefitFrequency benefitFrequency = client.path("benefitFrequency/1770").accept("application/xml").get(BenefitFrequency.class);
      Assert.assertNotNull(benefitFrequency);
   }

   @Test
   public void updateBenefitFrequency()
   {
     WebClient client = WebClient.create("http://localhost:8084/benefitFrequency/ws/benefitFrequencyservice/");
     BenefitFrequency benefitFrequency = client.path("PATH/1770").accept("application/xml").get(BenefitFrequency.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(benefitFrequency);
     client.back(true);

    BenefitFrequency newBenefitFrequency = client.path("PATH/1770").accept("application/xml").get(BenefitFrequency.class);

   }
   public void testCreateBenefitFrequency()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/benefitFrequencyservice/");
    client.path("PATH");
    BenefitFrequencyForm pf = new BenefitFrequencyForm();



    client.post(pf);
    client.back(true);


   }
   public void printBenefitFrequency()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/persservice/");
     BenefitFrequencyForm pf = new BenefitFrequencyForm();


   }



}