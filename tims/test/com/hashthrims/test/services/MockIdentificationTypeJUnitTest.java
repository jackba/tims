/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.repository.jpa.IdentificationTypeDAO;
import com.hashthrims.services.Impl.IdentificationTypeImplService;
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
public class MockIdentificationTypeJUnitTest {
      private static IdentificationTypeDAO dao;
   private static IdentificationTypeImplService identificationTypeService;


    public MockIdentificationTypeJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(IdentificationTypeDAO.class);
        identificationTypeService = new IdentificationTypeImplService();
        identificationTypeService.setIdentificationTypeDAO(dao);

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
     public void createIdentifications()
     {
      
      IdentificationType e = new IdentificationType();
       e.setIdentity_name_type("Licence");

       


        dao.persist(e);
        EasyMock.replay(dao);
        identificationTypeService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetIdentificationType()
    {
       IdentificationType d = new IdentificationType();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        identificationTypeService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

