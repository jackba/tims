
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.repository.jpa.EmployeeLanguagesDAO;
import com.hashthrims.services.Impl.EmployeeLanguagesImplService;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author |\/|0(|<_G3N
 */
public class MockLanguagesJUnitTest {
      private static EmployeeLanguagesDAO dao;
   private static EmployeeLanguagesImplService facilityService;


    public MockLanguagesJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(EmployeeLanguagesDAO.class);
        facilityService = new EmployeeLanguagesImplService();
        facilityService.setLanguagesDAO(dao);

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
     public void createLanguages()
     {
        EmployeeLanguages d = new EmployeeLanguages();
        d.setWriting("tets");
        dao.persist(d);
        EasyMock.replay(dao);
        facilityService.persist(d);
        EasyMock.verify(dao);

    }

    @Test
    public void testGetLanguages()
    {
       EmployeeLanguages d = new EmployeeLanguages();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        facilityService.find(new Long(1));
        EasyMock.verify(dao);
    }
}