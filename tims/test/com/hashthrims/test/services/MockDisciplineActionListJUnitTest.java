/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.repository.jpa.DisciplineActionTypeListDAO;
import com.hashthrims.services.Impl.DisciplineActionListImplService;
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
public class MockDisciplineActionListJUnitTest {
      private static DisciplineActionTypeListDAO dao;
      private static DisciplineActionListImplService disciplineActionTypeListService;


    public MockDisciplineActionListJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(DisciplineActionTypeListDAO.class);
        disciplineActionTypeListService = new DisciplineActionListImplService();
        disciplineActionTypeListService.setDisciplineActionTypeListDAO(dao);

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
     public void createDisciplineAction()
     {
      
      DisciplineActionTypeList e = new DisciplineActionTypeList();
       e.setDisplineName("Fired");

       


        dao.persist(e);
        EasyMock.replay(dao);
        disciplineActionTypeListService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetDisciplineActionTypeList()
    {
       DisciplineActionTypeList d = new DisciplineActionTypeList();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        disciplineActionTypeListService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

