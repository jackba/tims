/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmployeeForm;
import com.hashthrims.domain.Employee;
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
public class EmployeeRESTServiceTest {
public EmployeeRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeeservice/");
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
    public void readEmployee() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeeservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Employee employee = client.path("hashthrims/1770").accept("application/xml").get(Employee.class);
        Assert.assertNotNull(employee);
    }

    @Test
    public void updateEmployee() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/employeeservice/");
        // You have to Supply the ID that is in the DB /employee/{ID} for the Test to pass
        Employee voucher = client.path("employee/1770").accept("application/xml").get(Employee.class);

        //Now Update the Employee
        client.back(true);
        client.path("/employee/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Employee newemployee = client.path("employee/1770").accept("application/xml").get(Employee.class);

    }


    public void printEmployees(){

        //Code Invetory

    }

    // @Test
    public void testCreateEmployees() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/employeeservice/");
        client.path("employee");
        EmployeeForm f = new  EmployeeForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
