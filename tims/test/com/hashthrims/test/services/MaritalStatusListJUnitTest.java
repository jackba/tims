/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.repository.jpa.MaritalStatusListDAO;
import com.hashthrims.services.Impl.MaritalStatusListImplService;
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
public class MaritalStatusListJUnitTest {
      private static MaritalStatusListDAO dao;
   private static MaritalStatusListImplService maritalStatusListService;


    public MaritalStatusListJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(MaritalStatusListDAO.class);
        maritalStatusListService = new MaritalStatusListImplService();
        maritalStatusListService.setMaritalStatusListDAO(dao);

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
     public void createMaritalStatusList()
     {
      
      MaritalStatusList e = new MaritalStatusList();
       e.setStatus_name("divorced");

       


        dao.persist(e);
        EasyMock.replay(dao);
        maritalStatusListService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetMaritalStatusList()
    {
       MaritalStatusList d = new MaritalStatusList();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        maritalStatusListService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

