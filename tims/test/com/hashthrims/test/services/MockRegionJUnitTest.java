/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.repository.jpa.RegionDAO;
import com.hashthrims.services.Impl.RegionImplService;
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
public class MockRegionJUnitTest {
      private static RegionDAO dao;
   private static RegionImplService regionService;


    public MockRegionJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(RegionDAO.class);
        regionService = new RegionImplService();
        regionService.setRegionDAO(dao);

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
     public void createRegion()
     {
        Province u = new Province();
        u.setRegionName("Western Cape");

        dao.persist(u);
        EasyMock.replay(dao);
        regionService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetRegion()
    {
       Province d = new Province();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        regionService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

