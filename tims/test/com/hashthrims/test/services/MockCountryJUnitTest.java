/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.CountryDAO;
import com.hashthrims.services.Impl.CountryImplService;
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
public class MockCountryJUnitTest {
      private static CountryDAO dao;
   private static CountryImplService countryService;


    public MockCountryJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(CountryDAO.class);
        countryService = new CountryImplService();
        countryService.setCountryDAO(dao);

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
     public void createCountry()
     {
        Country u = new Country();
        u.setCountryName("South Africa");

        dao.persist(u);
        EasyMock.replay(dao);
        countryService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetCountry()
    {
       Country d = new Country();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        countryService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

