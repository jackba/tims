/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmployeeBenefitsForm;
import com.hashthrims.domain.EmployeeBenefits;
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
public class EmployeeBenefitsRESTServiceTest {
public EmployeeBenefitsRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeebenefitsservice/");
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
    public void readEmployeeBenefits() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeebenefitsservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EmployeeBenefits employeebenefits = client.path("hashthrims/1770").accept("application/xml").get(EmployeeBenefits.class);
        Assert.assertNotNull(employeebenefits);
    }

    @Test
    public void updateEmployeeBenefits() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeebenefitsservice/");
        // You have to Supply the ID that is in the DB /employeebenefits/{ID} for the Test to pass
        EmployeeBenefits voucher = client.path("employeeBenefits/1770").accept("application/xml").get(EmployeeBenefits.class);

        //Now Update the EmployeeBenefits
        client.back(true);
        client.path("/employeeBenefits/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EmployeeBenefits newemployeebenefits = client.path("employeeBenefits/1770").accept("application/xml").get(EmployeeBenefits.class);

    }


    public void printEmployeeBenefitss(){

        //Code Invetory

    }

    // @Test
    public void testCreateEmployeeBenefitss() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/employeebenefitsservice/");
        client.path("employeeBenefits");
        EmployeeBenefitsForm f = new  EmployeeBenefitsForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
