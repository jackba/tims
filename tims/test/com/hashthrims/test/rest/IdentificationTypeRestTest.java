/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.IdentificationTypeForm;
import com.hashthrims.domain.employeelist.IdentificationType;
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
public class IdentificationTypeRestTest {

    public IdentificationTypeRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/identificationType/ws/identificationTypeservice/");
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
   public void readIdentificationType()
   {
      WebClient client = WebClient.create("http://localhost:8084/identificationType/ws/identificationTypeservice/");

      IdentificationType identificationType = client.path("identificationType/1770").accept("application/xml").get(IdentificationType.class);
      Assert.assertNotNull(identificationType);
   }

   @Test
   public void updateIdentificationType()
   {
     WebClient client = WebClient.create("http://localhost:8084/identificationType/ws/identificationTypeservice/");
     IdentificationType identificationType = client.path("PATH/1770").accept("application/xml").get(IdentificationType.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(identificationType);
     client.back(true);

    IdentificationType newIdentificationType = client.path("PATH/1770").accept("application/xml").get(IdentificationType.class);

   }
   public void testCreateIdentificationType()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/identificationTypeservice/");
    client.path("PATH");
    IdentificationTypeForm pf = new IdentificationTypeForm();



    client.post(pf);
    client.back(true);


   }
   public void printIdentificationType()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/identificationTypeservice/");
     IdentificationTypeForm pf = new IdentificationTypeForm();


   }



}