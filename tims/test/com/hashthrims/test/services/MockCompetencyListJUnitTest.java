/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.CompetencyListDAO;
import com.hashthrims.services.Impl.CompetencyListImplService;
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
public class MockCompetencyListJUnitTest {
      private static CompetencyListDAO dao;
   private static CompetencyListImplService competencyListService;


    public MockCompetencyListJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(CompetencyListDAO.class);
        competencyListService = new CompetencyListImplService();
        competencyListService.setCompetencyListDAO(dao);

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
     public void createCompetencyList()
     {
      
      CompetencyList e = new CompetencyList();
       e.setComp_name("communication");
       CompetencyType c = new CompetencyType();
       c.setComp_name_typ("managerial");


        dao.persist(e);
        EasyMock.replay(dao);
        competencyListService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetCompetencyList()
    {
       CompetencyList d = new CompetencyList();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        competencyListService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

