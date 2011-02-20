/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.ScheduledCourses;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.ScheduledCoursesImplService;
import com.hashthrims.repository.jpa.ScheduledCoursesDAO;
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
public class MockScheduledCoursesJUnitTest {

   private static ScheduledCoursesDAO dao;
   private static ScheduledCoursesImplService scheduledCoursesService;

    public MockScheduledCoursesJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(ScheduledCoursesDAO.class);
        scheduledCoursesService = new ScheduledCoursesImplService();
        scheduledCoursesService.setScheduledCoursesDAO(dao);

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
     public void createScheduledCourses()
     {

      ScheduledCourses s = new ScheduledCourses();
      s.setDistrict("Western");




        dao.persist(s);
        EasyMock.replay(dao);
        scheduledCoursesService.persist(s);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetScheduledCourses()
    {
       ScheduledCourses d = new ScheduledCourses();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        scheduledCoursesService.find(new Long(1));
        EasyMock.verify(dao);


      }

}