/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationForm;
import com.hashthrims.domain.Education;
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
public class EducationRESTServiceTest {
public EducationRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/educationservice/");
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
    public void readEducation() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/educationservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Education education = client.path("hashthrims/1770").accept("application/xml").get(Education.class);
        Assert.assertNotNull(education);
    }

    @Test
    public void updateEducation() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/educationservice/");
        // You have to Supply the ID that is in the DB /education/{ID} for the Test to pass
        Education voucher = client.path("education/1770").accept("application/xml").get(Education.class);

        //Now Update the Education
        client.back(true);
        client.path("/education/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Education neweducation = client.path("education/1770").accept("application/xml").get(Education.class);

    }


    public void printEducations(){

        //Code Invetory

    }

    // @Test
    public void testCreateEducations() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/educationservice/");
        client.path("education");
        EducationForm f = new  EducationForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
