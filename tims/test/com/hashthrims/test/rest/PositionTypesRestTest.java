/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PositionTypesForm;
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
public class PositionTypesRestTest {

    public PositionTypesRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/positionTypes/ws/positionTypesservice/");
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
   public void readPositionTypes()
   {
      WebClient client = WebClient.create("http://localhost:8084/positionTypes/ws/positionTypesservice/");

      PositionTypes positionTypes = client.path("positionTypes/1770").accept("application/xml").get(PositionTypes.class);
      Assert.assertNotNull(positionTypes);
   }

   @Test
   public void updatePositionTypes()
   {
     WebClient client = WebClient.create("http://localhost:8084/positionTypes/ws/positionTypesservice/");
     PositionTypes positionTypes = client.path("PATH/1770").accept("application/xml").get(PositionTypes.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(positionTypes);
     client.back(true);

    PositionTypes newPositionTypes = client.path("PATH/1770").accept("application/xml").get(PositionTypes.class);

   }
   public void testCreatePositionTypes()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/positionTypesservice/");
    client.path("PATH");
    PositionTypesForm pf = new PositionTypesForm();



    client.post(pf);
    client.back(true);


   }
   public void printPositionTypes()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/positionTypesservice/");
     PositionTypesForm pf = new PositionTypesForm();


   }



}