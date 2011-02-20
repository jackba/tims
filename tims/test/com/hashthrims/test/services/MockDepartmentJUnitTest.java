/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.repository.jpa.DepartmentDAO;
import com.hashthrims.services.Impl.DepartmentImplService;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author stud
 */
public class MockDepartmentJUnitTest {
      private static DepartmentDAO dao;
   private static DepartmentImplService departmentService;


    public MockDepartmentJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DepartmentDAO.class);
        departmentService = new DepartmentImplService();
        departmentService.setDepartmentDAO(dao);

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
     public void createDepartment()
     {
        Department d = new Department();
        d.setDeptName("I.T");
      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(d);
        EasyMock.replay(dao);
        departmentService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetDepartment()
    {
       Department d = new Department();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        departmentService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

