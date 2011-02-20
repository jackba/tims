/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.JobsForm;
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
public class JobRestTest {

    public JobRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/Jobs/ws/Jobsservice/");
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
   public void readJobs()
   {
      WebClient client = WebClient.create("http://localhost:8084/Jobs/ws/Jobsservice/");

      Jobs Jobs = client.path("Jobs/1770").accept("application/xml").get(Jobs.class);
      Assert.assertNotNull(Jobs);
   }

   @Test
   public void updateJobs()
   {
     WebClient client = WebClient.create("http://localhost:8084/Jobs/ws/Jobsservice/");
     Jobs Jobs = client.path("PATH/1770").accept("application/xml").get(Jobs.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(Jobs);
     client.back(true);

    Jobs newJobs = client.path("PATH/1770").accept("application/xml").get(Jobs.class);

   }
   public void testCreateJobs()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/Jobsservice/");
    client.path("PATH");
    JobsForm pf = new JobsForm();



    client.post(pf);
    client.back(true);


   }
   public void printJobs()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/Jobsservice/");
     JobsForm pf = new JobsForm();


   }



}