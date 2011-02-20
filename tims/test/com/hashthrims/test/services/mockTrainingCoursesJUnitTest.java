/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingCourses;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingCoursesImplService;
import com.hashthrims.repository.jpa.TrainingCoursesDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author staff
 */
public class mockTrainingCoursesJUnitTest {

    private static TrainingCoursesDAO dao;
    private static TrainingCoursesImplService trainingCoursesService;

    public mockTrainingCoursesJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingCoursesDAO.class);
        trainingCoursesService = new TrainingCoursesImplService();
        trainingCoursesService.setTrainingCoursesDAO(dao);

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
     public void createTrainingCourses()
     {
        TrainingCourses t = new TrainingCourses();
        t.setCourseName("Food Tech");




        dao.persist(t);
        EasyMock.replay(dao);
        trainingCoursesService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingCourses()
    {
       TrainingCourses d = new TrainingCourses();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingCoursesService.find(new Long(1));
        EasyMock.verify(dao);


      }
}
