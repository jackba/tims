/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmployeePositionForm;
import com.hashthrims.domain.EmployeePosition;
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
public class EmployeePositionsRESTServiceTest {
public EmployeePositionsRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeepositionservice/");
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
    public void readEmployeePositions() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeepositionservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EmployeePosition employeepositions = client.path("hashthrims/1770").accept("application/xml").get(EmployeePosition.class);
        Assert.assertNotNull(employeepositions);
    }

    @Test
    public void updateEmployeePositions() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeepositionservice/");
        // You have to Supply the ID that is in the DB /employeepositions/{ID} for the Test to pass
        EmployeePosition voucher = client.path("employeePositions/1770").accept("application/xml").get(EmployeePosition.class);

        //Now Update the EmployeePositions
        client.back(true);
        client.path("/employeePosition/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EmployeePosition newemployeepositions = client.path("employeePosition/1770").accept("application/xml").get(EmployeePosition.class);

    }


    public void printEmployeePositionses(){

        //Code Invetory

    }

    // @Test
    public void testCreateEmployeePositionses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/employeepositionsservice/");
        client.path("employeePosition");
        EmployeePositionForm f = new  EmployeePositionForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
