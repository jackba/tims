/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationTypeForm;
import com.hashthrims.domain.employeelist.EducationType;
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
public class EducationTypeRestTest {

    public EducationTypeRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/educationType/ws/benefitfrequencyservice/");
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
   public void readEducationType()
   {
      WebClient client = WebClient.create("http://localhost:8084/educationType/ws/educationTypeservice/");

      EducationType educationType = client.path("educationType/1770").accept("application/xml").get(EducationType.class);
      Assert.assertNotNull(educationType);
   }

   @Test
   public void updateEducationType()
   {
     WebClient client = WebClient.create("http://localhost:8084/educationType/ws/educationTypeservice/");
     EducationType educationType = client.path("PATH/1770").accept("application/xml").get(EducationType.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(educationType);
     client.back(true);

    EducationType newEducationType = client.path("PATH/1770").accept("application/xml").get(EducationType.class);

   }
   public void testCreateEducationType()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/educationTypeservice/");
    client.path("PATH");
    EducationTypeForm pf = new EducationTypeForm();



    client.post(pf);
    client.back(true);


   }
   public void printEducationType()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/educationTypeservice/");
     EducationTypeForm pf = new EducationTypeForm();


   }



}