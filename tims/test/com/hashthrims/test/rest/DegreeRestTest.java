/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DegreeForm;
import com.hashthrims.domain.employeelist.Degree;
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
public class DegreeRestTest {

    public DegreeRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/degree/ws/degreeservice/");
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
   public void readDegree()
   {
      WebClient client = WebClient.create("http://localhost:8084/degree/ws/degreeservice/");

      Degree degree = client.path("degree/1770").accept("application/xml").get(Degree.class);
      Assert.assertNotNull(degree);
   }

   @Test
   public void updateDegree()
   {
     WebClient client = WebClient.create("http://localhost:8084/degree/ws/degreeservice/");
     Degree degree = client.path("PATH/1770").accept("application/xml").get(Degree.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(degree);
     client.back(true);

    Degree newDegree = client.path("PATH/1770").accept("application/xml").get(Degree.class);

   }
   public void testCreateDegree()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/degreeservice/");
    client.path("PATH");
    DegreeForm pf = new DegreeForm();



    client.post(pf);
    client.back(true);


   }
   public void printDegree()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/degreeservice/");
     DegreeForm pf = new DegreeForm();


   }



}