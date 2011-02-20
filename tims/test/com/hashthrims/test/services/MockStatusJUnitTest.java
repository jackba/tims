/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.StatusDAO;
import com.hashthrims.services.Impl.StatusImplService;
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
public class MockStatusJUnitTest {
      private static StatusDAO dao;
   private static StatusImplService statusService;


    public MockStatusJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(StatusDAO.class);
        statusService = new StatusImplService();
        statusService.setStatusDAO(dao);

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
     public void createStatus()
     {
        Status d = new Status();
        d.setStatus("Passed");
      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(d);
        EasyMock.replay(dao);
        statusService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetStatus()
    {
       Status d = new Status();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        statusService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

