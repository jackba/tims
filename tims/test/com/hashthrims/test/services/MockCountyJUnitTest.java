/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.regionlist.County;
import com.hashthrims.repository.jpa.CountyDAO;
import com.hashthrims.services.Impl.CountyImplService;
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
public class MockCountyJUnitTest {
      private static CountyDAO dao;
   private static CountyImplService countyService;


    public MockCountyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(CountyDAO.class);
        countyService = new CountyImplService();
        countyService.setCountyDAO(dao);

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
     public void createCounty()
     {
        County u = new County();
        u.setCountyName("Cape Town");

        dao.persist(u);
        EasyMock.replay(dao);
        countyService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetCounty()
    {
       County d = new County();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        countyService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

