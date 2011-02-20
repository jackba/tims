/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
import com.hashthrims.domain.EmployeeCourses;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author abismail
 */
public class CompetencyRESTServiceTest {
public CompetencyRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/competencyservice/");
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

    //Test requires the Application to be running in the Container
    //
    @Test
    public void readCompetency() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/competencyservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EmployeeCourses competency = client.path("hashthrims/1770").accept("application/xml").get(EmployeeCourses.class);
        Assert.assertNotNull(competency);
    }

    @Test
    public void updateCompetency() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/competencyservice/");
        // You have to Supply the ID that is in the DB /competency/{ID} for the Test to pass
        EmployeeCourses voucher = client.path("competency/1770").accept("application/xml").get(EmployeeCourses.class);

        //Now Update the EmployeeCourses
        client.back(true);
        client.path("/competency/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EmployeeCourses newcompetency = client.path("competency/1770").accept("application/xml").get(EmployeeCourses.class);

    }


    public void printCompetencies(){

        //Code Invetory

    }

    // @Test
    public void testCreateCompetencies() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/competencyservice/");
        client.path("competency");
        CompetencyForm f = new  CompetencyForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
