/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.repository.jpa.CompetencyEvaluationDAO;
import com.hashthrims.services.Impl.CompetencyEvaluationImplService;
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
public class MockCompetencyEvaluationJUnitTest {
      private static CompetencyEvaluationDAO dao;
   private static CompetencyEvaluationImplService competencyEvaluationService;


    public MockCompetencyEvaluationJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(CompetencyEvaluationDAO.class);
        competencyEvaluationService = new CompetencyEvaluationImplService();
        competencyEvaluationService.setCompetencyEvaluationDAO(dao);

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
     public void createCompetencyEvaluation()
     {
      
      CompetencyEvaluation e = new CompetencyEvaluation();
       e.setCompt_type_name("Knowlegde");

       


        dao.persist(e);
        EasyMock.replay(dao);
        competencyEvaluationService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetCompetencyEvaluation()
    {
       CompetencyEvaluation d = new CompetencyEvaluation();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        competencyEvaluationService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

