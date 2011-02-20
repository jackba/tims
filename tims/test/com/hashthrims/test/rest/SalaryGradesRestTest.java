/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.SalaryGradesForm;
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
public class SalaryGradesRestTest {

    public SalaryGradesRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/salaryGrades/ws/salaryGradesservice/");
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
   public void readSalaryGrades()
   {
      WebClient client = WebClient.create("http://localhost:8084/salaryGrades/ws/salaryGradesservice/");

      SalaryGrade salaryGrades = client.path("salaryGrades/1770").accept("application/xml").get(SalaryGrade.class);
      Assert.assertNotNull(salaryGrades);
   }

   @Test
   public void updateSalaryGrades()
   {
     WebClient client = WebClient.create("http://localhost:8084/salaryGrades/ws/salaryGradesservice/");
     SalaryGrade salaryGrades = client.path("PATH/1770").accept("application/xml").get(SalaryGrade.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(salaryGrades);
     client.back(true);

    SalaryGrade newSalaryGrades = client.path("PATH/1770").accept("application/xml").get(SalaryGrade.class);

   }
   public void testCreateSalaryGrades()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/salaryGradesservice/");
    client.path("PATH");
    SalaryGradesForm pf = new SalaryGradesForm();



    client.post(pf);
    client.back(true);


   }
   public void printSalaryGrades()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/salaryGradesservice/");
     SalaryGradesForm pf = new SalaryGradesForm();


   }



}