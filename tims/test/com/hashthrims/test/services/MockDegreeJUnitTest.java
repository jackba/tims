/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.DegreeDAO;
import com.hashthrims.services.Impl.DegreeImplService;
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
public class MockDegreeJUnitTest {
      private static DegreeDAO dao;
   private static DegreeImplService degreeService;


    public MockDegreeJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DegreeDAO.class);
        degreeService = new DegreeImplService();
        degreeService.setDegreeDAO(dao);

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
     public void createDegree()
     {
        Degree d = new Degree();
        d.setDegree_name("Accounting");
      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(d);
        EasyMock.replay(dao);
        degreeService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetDegree()
    {
       Degree d = new Degree();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        degreeService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

