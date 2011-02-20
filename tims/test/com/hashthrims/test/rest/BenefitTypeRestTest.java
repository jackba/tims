/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.BenefitTypeForm;
import com.hashthrims.domain.employeelist.BenefitType;
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
public class BenefitTypeRestTest {

    public BenefitTypeRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/benefitType/ws/benefittypeservice/");
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
   public void readBenefitType()
   {
      WebClient client = WebClient.create("http://localhost:8084/benefitType/ws/benefittypeservice/");

      BenefitType benefitType = client.path("benefitType/1770").accept("application/xml").get(BenefitType.class);
      Assert.assertNotNull(benefitType);
   }

   @Test
   public void updateBenefitType()
   {
     WebClient client = WebClient.create("http://localhost:8084/benefitType/ws/benefittypeservice/");
     BenefitType benefitType = client.path("PATH/1770").accept("application/xml").get(BenefitType.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(benefitType);
     client.back(true);

    BenefitType newBenefitType = client.path("PATH/1770").accept("application/xml").get(BenefitType.class);

   }
   public void testCreateBenefitType()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/persservice/");
    client.path("PATH");
    BenefitTypeForm pf = new BenefitTypeForm();



    client.post(pf);
    client.back(true);


   }
   public void printBenefitType()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/benefittypeservice/");
     BenefitTypeForm pf = new BenefitTypeForm();


   }



}