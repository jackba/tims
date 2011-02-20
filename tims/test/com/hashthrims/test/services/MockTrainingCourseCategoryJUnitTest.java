/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.services.Impl.TrainingCourseCategoryImplService;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.DisciplineActionListImplService;
import com.hashthrims.repository.jpa.TrainingCourseCategoryDAO;
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
public class MockTrainingCourseCategoryJUnitTest {

    private static TrainingCourseCategoryDAO dao;
    private static TrainingCourseCategoryImplService trainingCourseCategoryService;

    public MockTrainingCourseCategoryJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingCourseCategoryDAO.class);
        trainingCourseCategoryService = new TrainingCourseCategoryImplService();
        trainingCourseCategoryService.setTrainingCourseCategoryDAO(dao);

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

      TrainingCourseCategory t = new TrainingCourseCategory();
       t.setCategoryName("Technical");

       


        dao.persist(t);
        EasyMock.replay(dao);
        trainingCourseCategoryService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingCourseCategory()
    {
       TrainingCourseCategory d = new TrainingCourseCategory();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingCourseCategoryService.find(new Long(1));
        EasyMock.verify(dao);


      }

}