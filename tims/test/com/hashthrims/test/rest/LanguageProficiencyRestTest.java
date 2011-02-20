/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguageProficiencyForm;
import com.hashthrims.domain.employeelist.LanguageProficiency;
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
public class LanguageProficiencyRestTest {

    public LanguageProficiencyRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/languageProficiency/ws/languageProficiencyservice/");
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
   public void readLanguageProficiency()
   {
      WebClient client = WebClient.create("http://localhost:8084/languageProficiency/ws/languageProficiencyservice/");

      LanguageProficiency languageProficiency = client.path("languageProficiency/1770").accept("application/xml").get(LanguageProficiency.class);
      Assert.assertNotNull(languageProficiency);
   }

   @Test
   public void updateLanguageProficiency()
   {
     WebClient client = WebClient.create("http://localhost:8084/languageProficiency/ws/languageProficiencyservice/");
     LanguageProficiency languageProficiency = client.path("PATH/1770").accept("application/xml").get(LanguageProficiency.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(languageProficiency);
     client.back(true);

    LanguageProficiency newLanguageProficiency = client.path("PATH/1770").accept("application/xml").get(LanguageProficiency.class);

   }
   public void testCreateLanguageProficiency()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/languageProficiencyservice/");
    client.path("PATH");
    LanguageProficiencyForm pf = new LanguageProficiencyForm();



    client.post(pf);
    client.back(true);


   }
   public void printLanguageProficiency()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/languageProficiencyservice/");
     LanguageProficiencyForm pf = new LanguageProficiencyForm();


   }



}