/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.repository.jpa.DepartureReasonsDAO;
import com.hashthrims.services.Impl.DepartureReasonsImplService;
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
public class MockDepartureReasonsJUnitTest {
      private static DepartureReasonsDAO dao;
   private static DepartureReasonsImplService departureReasonsService;


    public MockDepartureReasonsJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DepartureReasonsDAO.class);
        departureReasonsService = new DepartureReasonsImplService();
        departureReasonsService.setDepartureReasonsDAO(dao);

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
     public void createDepartureReasons()
     {
      
      DepartureReasons e = new DepartureReasons();
       e.setReason_name("suspended");

       


        dao.persist(e);
        EasyMock.replay(dao);
        departureReasonsService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetDepartureReasons()
    {
       DepartureReasons d = new DepartureReasons();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        departureReasonsService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

