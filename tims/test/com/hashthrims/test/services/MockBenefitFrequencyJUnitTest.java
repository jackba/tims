/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.repository.jpa.BenefitFrequencyDAO;
import com.hashthrims.services.Impl.BenefitFrequencyImplService;
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
public class MockBenefitFrequencyJUnitTest {
      private static BenefitFrequencyDAO dao;
   private static BenefitFrequencyImplService benefitFrequencyService;


    public MockBenefitFrequencyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(BenefitFrequencyDAO.class);
        benefitFrequencyService = new BenefitFrequencyImplService();
        benefitFrequencyService.setBenefitFrequencyDAO(dao);

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
     public void createBenefitFrequency()
     {
      
      BenefitFrequency e = new BenefitFrequency();
      e.setFrequency("15");

       


        dao.persist(e);
        EasyMock.replay(dao);
        benefitFrequencyService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetBenefitFrequency()
    {
       BenefitFrequency d = new BenefitFrequency();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        benefitFrequencyService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

