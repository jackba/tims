/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyListForm;
import com.hashthrims.domain.employeelist.CompetencyList;
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
public class CompetencyListRestTest {

    public CompetencyListRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/competencyList/ws/competencyListservice/");
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
   public void readCompetencyList()
   {
      WebClient client = WebClient.create("http://localhost:8084/competencyList/ws/competencyListservice/");

      CompetencyList competencyList = client.path("competencyList/1770").accept("application/xml").get(CompetencyList.class);
      Assert.assertNotNull(competencyList);
   }

   @Test
   public void updateCompetencyList()
   {
     WebClient client = WebClient.create("http://localhost:8084/competencyList/ws/competencyListservice/");
     CompetencyList competencyList = client.path("PATH/1770").accept("application/xml").get(CompetencyList.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(competencyList);
     client.back(true);

    CompetencyList newCompetencyList = client.path("PATH/1770").accept("application/xml").get(CompetencyList.class);

   }
   public void testCreateCompetencyList()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/competencyListservice/");
    client.path("PATH");
    CompetencyListForm pf = new CompetencyListForm();



    client.post(pf);
    client.back(true);


   }
   public void printCompetencyList()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/competencyListservice/");
     CompetencyListForm pf = new CompetencyListForm();


   }



}