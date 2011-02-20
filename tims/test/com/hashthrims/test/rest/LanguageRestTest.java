/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguageForm;
import com.hashthrims.domain.employeelist.Language;
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
public class LanguageRestTest {

    public LanguageRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/language/ws/languageservice/");
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
   public void readLanguage()
   {
      WebClient client = WebClient.create("http://localhost:8084/language/ws/languageservice/");

      Language language = client.path("language/1770").accept("application/xml").get(Language.class);
      Assert.assertNotNull(language);
   }

   @Test
   public void updateLanguage()
   {
     WebClient client = WebClient.create("http://localhost:8084/language/ws/languageservice/");
     Language language = client.path("PATH/1770").accept("application/xml").get(Language.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(language);
     client.back(true);

    Language newLanguage = client.path("PATH/1770").accept("application/xml").get(Language.class);

   }
   public void testCreateLanguage()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/languageservice/");
    client.path("PATH");
    LanguageForm pf = new LanguageForm();



    client.post(pf);
    client.back(true);


   }
   public void printLanguage()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/languageservice/");
     LanguageForm pf = new LanguageForm();


   }



}