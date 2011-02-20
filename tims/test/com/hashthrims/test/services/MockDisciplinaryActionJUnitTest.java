/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.DisciplinaryAction;
import com.hashthrims.repository.jpa.DisciplinaryActionDAO;
import com.hashthrims.services.Impl.DisciplinaryActionImplService;
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
public class MockDisciplinaryActionJUnitTest {
      private static DisciplinaryActionDAO dao;
   private static DisciplinaryActionImplService facilityService;


    public MockDisciplinaryActionJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DisciplinaryActionDAO.class);
        facilityService = new DisciplinaryActionImplService();
        facilityService.setDisciplinaryActionDAO(dao);

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
     public void createDisciplinaryAction()
     {
        DisciplinaryAction d = new DisciplinaryAction();
        d.setNotes("not");
        dao.persist(d);
        EasyMock.replay(dao);
        facilityService.persist(d);
        EasyMock.verify(dao);

    }

    @Test
    public void testGetDisciplinaryAction()
    {
       DisciplinaryAction d = new DisciplinaryAction();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        facilityService.find(new Long(1));
        EasyMock.verify(dao);
    }
}