/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DisciplineActionTypeListForm;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
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
public class DisciplineActionTypeListRestTest {

    public DisciplineActionTypeListRestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/disciplineActionTypeList/ws/disciplineActionTypeListservice/");
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
   public void readDisciplineActionTypeList()
   {
      WebClient client = WebClient.create("http://localhost:8084/disciplineActionTypeList/ws/disciplineActionTypeListservice/");

      DisciplineActionTypeList disciplineActionTypeList = client.path("disciplineActionTypeList/1770").accept("application/xml").get(DisciplineActionTypeList.class);
      Assert.assertNotNull(disciplineActionTypeList);
   }

   @Test
   public void updateDisciplineActionTypeList()
   {
     WebClient client = WebClient.create("http://localhost:8084/disciplineActionTypeList/ws/disciplineActionTypeListservice/");
     DisciplineActionTypeList disciplineActionTypeList = client.path("PATH/1770").accept("application/xml").get(DisciplineActionTypeList.class);

     client.back(true);
     client.path("/PATH/update");
     client.put(disciplineActionTypeList);
     client.back(true);

    DisciplineActionTypeList newDisciplineActionTypeList = client.path("PATH/1770").accept("application/xml").get(DisciplineActionTypeList.class);

   }
   public void testCreateDisciplineActionTypeList()
   {
    WebClient client = WebClient.create("http://localhost:8084/hashpay/disciplineActionTypeListservice/");
    client.path("PATH");
    DisciplineActionTypeListForm pf = new DisciplineActionTypeListForm();



    client.post(pf);
    client.back(true);


   }
   public void printDisciplineActionTypeList()
   {
     WebClient client = WebClient.create("http://localhost:8084/hashpay/disciplineActionTypeListservice/");
     DisciplineActionTypeListForm pf = new DisciplineActionTypeListForm();


   }



}