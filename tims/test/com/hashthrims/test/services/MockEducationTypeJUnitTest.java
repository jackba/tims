/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.EducationTypeDAO;
import com.hashthrims.services.Impl.EducationTypeImplService;
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
public class MockEducationTypeJUnitTest {
      private static EducationTypeDAO dao;
   private static EducationTypeImplService educationTypeService;


    public MockEducationTypeJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(EducationTypeDAO.class);
        educationTypeService = new EducationTypeImplService();
        educationTypeService.setEducationTypeDAO(dao);

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
     public void createEducationType()
     {
      
      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(e);
        EasyMock.replay(dao);
        educationTypeService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetEducationType()
    {
       EducationType d = new EducationType();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        educationTypeService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

