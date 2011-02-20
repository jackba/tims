/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.positions.Positions;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PositionsForm;

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
public class PositionRestTest {

    public PositionRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/position/ws/positionservice/");
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
   public void readPositions()
   {
      WebClient client = WebClient.create("http://localhost:8084/position/ws/positionservice/");

      Positions position = client.path("position/1770").accept("application/xml").get(Positions.class);
      Assert.assertNotNull(position);
   }

   @Test
   public void updatePositions()
   {
     WebClient client = WebClient.create("http://localhost:8084/position/ws/positionservice/");
     Positions position = client.path("PATH/1770").accept("application/xml").get(Positions.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(position);
     client.back(true);

    Positions newPositions = client.path("PATH/1770").accept("application/xml").get(Positions.class);

   }
   public void testCreatePositions()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/positionservice/");
    client.path("PATH");
    PositionsForm pf = new PositionsForm();



    client.post(pf);
    client.back(true);


   }
   public void printPositions()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/positionservice/");
     PositionsForm pf = new PositionsForm();


   }



}