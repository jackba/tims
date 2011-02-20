/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingCourseRequestorsImplService;
import com.hashthrims.repository.jpa.TrainingCourseRequestorsDAO;
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
public class MockTrainingCourseRequestorsJUnitTest {

    private static TrainingCourseRequestorsDAO dao;
   private static TrainingCourseRequestorsImplService trainingCourseRequestorsService;

    public MockTrainingCourseRequestorsJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingCourseRequestorsDAO.class);
        trainingCourseRequestorsService = new TrainingCourseRequestorsImplService();
        trainingCourseRequestorsService.setTrainingCourseRequestorsDAO(dao);

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
     public void createTrainingCourseRequestors()
     {
        TrainingCourseRequestors t = new TrainingCourseRequestors();
        t.setRequestorName("Hadi");




        dao.persist(t);
        EasyMock.replay(dao);
        trainingCourseRequestorsService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingCourseRequestors()
    {
       TrainingCourseRequestors d = new TrainingCourseRequestors();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingCourseRequestorsService.find(new Long(1));
        EasyMock.verify(dao);


      }

}