/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyTypeForm;
import com.hashthrims.domain.employeelist.CompetencyType;
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
public class CompetencyTypeRestTest {

    public CompetencyTypeRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/competencyType/ws/competencyTypeservice/");
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
   public void readCompetencyType()
   {
      WebClient client = WebClient.create("http://localhost:8084/competencyType/ws/competencyTypeservice/");

      CompetencyType competencyType = client.path("competencyType/1770").accept("application/xml").get(CompetencyType.class);
      Assert.assertNotNull(competencyType);
   }

   @Test
   public void updateCompetencyType()
   {
     WebClient client = WebClient.create("http://localhost:8084/competencyType/ws/competencyTypeservice/");
     CompetencyType competencyType = client.path("PATH/1770").accept("application/xml").get(CompetencyType.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(competencyType);
     client.back(true);

    CompetencyType newCompetencyType = client.path("PATH/1770").accept("application/xml").get(CompetencyType.class);

   }
   public void testCreateCompetencyType()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/competencyTypeservice/");
    client.path("PATH");
    CompetencyTypeForm pf = new CompetencyTypeForm();



    client.post(pf);
    client.back(true);


   }
   public void printCompetencyType()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/competencyTypeservice/");
     CompetencyTypeForm pf = new CompetencyTypeForm();


   }



}