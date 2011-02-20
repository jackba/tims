/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingCourseStatusImplService;
import com.hashthrims.repository.jpa.TrainingCourseStatusDAO;
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
public class MockTrainingCourseStatusJUnitTest {

    private static TrainingCourseStatusDAO dao;
   private static TrainingCourseStatusImplService trainingCourseStatusService;

    public MockTrainingCourseStatusJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingCourseStatusDAO.class);
        trainingCourseStatusService = new TrainingCourseStatusImplService();
        trainingCourseStatusService.setTrainingCourseStatusDAO(dao);

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
     public void createTrainingCourseStatus()
     {

      TrainingCourseStatus t = new TrainingCourseStatus();
      t.setStatusName("To be done");




        dao.persist(t);
        EasyMock.replay(dao);
        trainingCourseStatusService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingCourseStatus()
    {
       TrainingCourseStatus d = new TrainingCourseStatus();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingCourseStatusService.find(new Long(1));
        EasyMock.verify(dao);


      }

}