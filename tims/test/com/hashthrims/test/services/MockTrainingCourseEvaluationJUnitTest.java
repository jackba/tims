/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingCourseEvaluationImplService;
import com.hashthrims.repository.jpa.TrainingCourseEvaluationDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author staff
 */
public class MockTrainingCourseEvaluationJUnitTest {

    private static TrainingCourseEvaluationDAO dao;
   private static TrainingCourseEvaluationImplService trainingCourseEvaluationService;

    public MockTrainingCourseEvaluationJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingCourseEvaluationDAO.class);
        trainingCourseEvaluationService = new TrainingCourseEvaluationImplService();
        trainingCourseEvaluationService.setTrainingCourseEvaluationDAO(dao);

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
     public void createTrainingCourseEvaluation()
     {
        TrainingCourseEvaluation t = new TrainingCourseEvaluation();
        t.setEvaluationName("Medical");




        dao.persist(t);
        EasyMock.replay(dao);
        trainingCourseEvaluationService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingCourseEvaluation()
    {
       TrainingCourseEvaluation d = new TrainingCourseEvaluation();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingCourseEvaluationService.find(new Long(1));
        EasyMock.verify(dao);


      }

}