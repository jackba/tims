/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.MaritalStatusListForm;
import com.hashthrims.domain.employeelist.MaritalStatusList;
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
public class MaritalStatusListRestTest {

    public MaritalStatusListRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/maritalStatusList/ws/maritalStatusListservice/");
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
   public void readMaritalStatusList()
   {
      WebClient client = WebClient.create("http://localhost:8084/maritalStatusList/ws/maritalStatusListservice/");

      MaritalStatusList maritalStatusList = client.path("maritalStatusList/1770").accept("application/xml").get(MaritalStatusList.class);
      Assert.assertNotNull(maritalStatusList);
   }

   @Test
   public void updateMaritalStatusList()
   {
     WebClient client = WebClient.create("http://localhost:8084/maritalStatusList/ws/maritalStatusListservice/");
     MaritalStatusList maritalStatusList = client.path("PATH/1770").accept("application/xml").get(MaritalStatusList.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(maritalStatusList);
     client.back(true);

    MaritalStatusList newMaritalStatusList = client.path("PATH/1770").accept("application/xml").get(MaritalStatusList.class);

   }
   public void testCreateMaritalStatusList()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/maritalStatusListservice/");
    client.path("PATH");
    MaritalStatusListForm pf = new MaritalStatusListForm();



    client.post(pf);
    client.back(true);


   }
   public void printMaritalStatusList()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/maritalStatusListservice/");
     MaritalStatusListForm pf = new MaritalStatusListForm();


   }



}