/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CadresForm;
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
public class CadresRestTest {

    public CadresRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/Cadres/ws/Cadresservice/");
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
   public void readCadres()
   {
      WebClient client = WebClient.create("http://localhost:8084/Cadres/ws/Cadresservice/");

      Cadres Cadres = client.path("Cadres/1770").accept("application/xml").get(Cadres.class);
      Assert.assertNotNull(Cadres);
   }

   @Test
   public void updateCadres()
   {
     WebClient client = WebClient.create("http://localhost:8084/Cadres/ws/Cadresservice/");
     Cadres Cadres = client.path("PATH/1770").accept("application/xml").get(Cadres.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(Cadres);
     client.back(true);

    Cadres newCadres = client.path("PATH/1770").accept("application/xml").get(Cadres.class);

   }
   public void testCreateCadres()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/Cadresservice/");
    client.path("PATH");
    CadresForm pf = new CadresForm();



    client.post(pf);
    client.back(true);


   }
   public void printCadres()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/Cadresservice/");
     CadresForm pf = new CadresForm();


   }



}