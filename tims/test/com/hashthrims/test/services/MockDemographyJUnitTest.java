/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.Demography;
import com.hashthrims.repository.jpa.DemographyDAO;
import com.hashthrims.services.Impl.DemographyImplService;
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
public class MockDemographyJUnitTest {
      private static DemographyDAO dao;
   private static DemographyImplService demographyService;


    public MockDemographyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DemographyDAO.class);
        demographyService = new DemographyImplService();
        demographyService.setDemographyDAO(dao);

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
     public void createDemography()
     {
        Demography u = new Demography();
        u.setGender("Male");

        dao.persist(u);
        EasyMock.replay(dao);
        demographyService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetDemography()
    {
       Demography d = new Demography();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        demographyService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

