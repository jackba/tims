/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.domain.offices.Department;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DepartmentForm;
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
public class DepartmentRestTest {

    public DepartmentRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/department/ws/departmentservice/");
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
   public void readDepartment()
   {
      WebClient client = WebClient.create("http://localhost:8084/department/ws/departmentservice/");

      Department department = client.path("department/1770").accept("application/xml").get(Department.class);
      Assert.assertNotNull(department);
   }

   @Test
   public void updateDepartment()
   {
     WebClient client = WebClient.create("http://localhost:8084/department/ws/departmentservice/");
     Department department = client.path("PATH/1770").accept("application/xml").get(Department.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(department);
     client.back(true);

    Department newDepartment = client.path("PATH/1770").accept("application/xml").get(Department.class);

   }
   public void testCreateDepartment()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/departmentservice/");
    client.path("PATH");
    DepartmentForm pf = new DepartmentForm();



    client.post(pf);
    client.back(true);


   }
   public void printDepartment()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/departmentservice/");
     DepartmentForm pf = new DepartmentForm();


   }



}