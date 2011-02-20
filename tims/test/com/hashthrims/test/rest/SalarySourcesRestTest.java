/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.SalarySourcesForm;
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
public class SalarySourcesRestTest {

    public SalarySourcesRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/salarySources/ws/salarySourcesservice/");
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
   public void readSalarySources()
   {
      WebClient client = WebClient.create("http://localhost:8084/salarySources/ws/salarySourcesservice/");

      SalarySources salarySources = client.path("salarySources/1770").accept("application/xml").get(SalarySources.class);
      Assert.assertNotNull(salarySources);
   }

   @Test
   public void updateSalarySources()
   {
     WebClient client = WebClient.create("http://localhost:8084/salarySources/ws/salarySourcesservice/");
     SalarySources salarySources = client.path("PATH/1770").accept("application/xml").get(SalarySources.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(salarySources);
     client.back(true);

    SalarySources newSalarySources = client.path("PATH/1770").accept("application/xml").get(SalarySources.class);

   }
   public void testCreateSalarySources()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/salarySourcesservice/");
    client.path("PATH");
    SalarySourcesForm pf = new SalarySourcesForm();



    client.post(pf);
    client.back(true);


   }
   public void printSalarySources()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/salarySourcesservice/");
     SalarySourcesForm pf = new SalarySourcesForm();


   }



}