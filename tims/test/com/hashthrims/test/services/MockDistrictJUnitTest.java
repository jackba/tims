/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.regionlist.District;
import com.hashthrims.repository.jpa.DistrictDAO;
import com.hashthrims.services.Impl.DistrictImplService;
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
public class MockDistrictJUnitTest {
      private static DistrictDAO dao;
   private static DistrictImplService districtService;


    public MockDistrictJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DistrictDAO.class);
        districtService = new DistrictImplService();
        districtService.setDistrictDAO(dao);

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
     public void createDistrict()
     {
        District u = new District();
        u.setDistrictName("CBD");

        dao.persist(u);
        EasyMock.replay(dao);
        districtService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetDistrict()
    {
       District d = new District();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        districtService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

