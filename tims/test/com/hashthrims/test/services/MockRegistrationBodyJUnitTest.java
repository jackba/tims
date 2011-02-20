/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.repository.jpa.RegistrationBodyDAO;
import com.hashthrims.services.Impl.RegistrationBodyImplService;
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
public class MockRegistrationBodyJUnitTest {
      private static RegistrationBodyDAO dao;
   private static RegistrationBodyImplService registrationBodyService;


    public MockRegistrationBodyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(RegistrationBodyDAO.class);
        registrationBodyService = new RegistrationBodyImplService();
        registrationBodyService.setRegistrationBodyDAO(dao);

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
     public void createRegistrationBody()
     {
        RegistrationBody d = new RegistrationBody();
        d.setName("UWC");
      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(d);
        EasyMock.replay(dao);
        registrationBodyService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetRegistrationBody()
    {
       RegistrationBody d = new RegistrationBody();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        registrationBodyService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

