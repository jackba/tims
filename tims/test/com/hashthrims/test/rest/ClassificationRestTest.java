/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ClassificationForm;
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
public class ClassificationRestTest {

    public ClassificationRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/classification/ws/classificationservice/");
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
   public void readclassification()
   {
      WebClient client = WebClient.create("http://localhost:8084/classification/ws/classificationservice/");

      Classifications classification = client.path("classification/1770").accept("application/xml").get(Classifications.class);
      Assert.assertNotNull(classification);
   }

   @Test
   public void updateclassification()
   {
     WebClient client = WebClient.create("http://localhost:8084/classification/ws/classificationservice/");
     Classifications classification = client.path("PATH/1770").accept("application/xml").get(Classifications.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(classification);
     client.back(true);

    Classifications newclassification = client.path("PATH/1770").accept("application/xml").get(Classifications.class);

   }
   public void testCreateclassification()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/classificationservice/");
    client.path("PATH");
    ClassificationForm pf = new ClassificationForm();



    client.post(pf);
    client.back(true);


   }
   public void printclassification()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/classificationservice/");
     ClassificationForm pf = new ClassificationForm();


   }



}