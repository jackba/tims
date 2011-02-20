/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.repository.jpa.BenefitTypeDAO;
import com.hashthrims.services.Impl.BenefitTypeImplService;
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
public class MockBenefitTypeJUnitTest {
      private static BenefitTypeDAO dao;
   private static BenefitTypeImplService benefitTypeService;


    public MockBenefitTypeJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(BenefitTypeDAO.class);
        benefitTypeService = new BenefitTypeImplService();
        benefitTypeService.setBenefitTypeDAO(dao);

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
     public void createBenefits()
     {
      
      BenefitType e = new BenefitType();
       e.setBenefit_type_name("Insurence");

       


        dao.persist(e);
        EasyMock.replay(dao);
        benefitTypeService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetBenefitType()
    {
       BenefitType d = new BenefitType();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        benefitTypeService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

