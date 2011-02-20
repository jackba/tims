/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyEvaluationForm;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
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
public class CompetencyEvaluationRestTest {

    public CompetencyEvaluationRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/competencyEvaluation/ws/competencyEvaluationservice/");
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
   public void readCompetencyEvaluation()
   {
      WebClient client = WebClient.create("http://localhost:8084/competencyEvaluation/ws/competencyEvaluationservice/");

      CompetencyEvaluation competencyEvaluation = client.path("competencyEvaluation/1770").accept("application/xml").get(CompetencyEvaluation.class);
      Assert.assertNotNull(competencyEvaluation);
   }

   @Test
   public void updateCompetencyEvaluation()
   {
     WebClient client = WebClient.create("http://localhost:8084/competencyEvaluation/ws/competencyEvaluationservice/");
     CompetencyEvaluation competencyEvaluation = client.path("PATH/1770").accept("application/xml").get(CompetencyEvaluation.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(competencyEvaluation);
     client.back(true);

    CompetencyEvaluation newCompetencyEvaluation = client.path("PATH/1770").accept("application/xml").get(CompetencyEvaluation.class);

   }
   public void testCreateCompetencyEvaluation()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/competencyEvaluationservice/");
    client.path("PATH");
    CompetencyEvaluationForm pf = new CompetencyEvaluationForm();



    client.post(pf);
    client.back(true);


   }
   public void printCompetencyEvaluation()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/benefittypeservice/");
     CompetencyEvaluationForm pf = new CompetencyEvaluationForm();


   }



}