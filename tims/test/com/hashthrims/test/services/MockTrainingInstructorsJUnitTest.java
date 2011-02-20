/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingInstructors;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingInstructorsImplService;
import com.hashthrims.repository.jpa.TrainingInstructorsDAO;
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
public class MockTrainingInstructorsJUnitTest {

    private static TrainingInstructorsDAO dao;
   private static TrainingInstructorsImplService trainingInstructorsService;

    public MockTrainingInstructorsJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingInstructorsDAO.class);
        trainingInstructorsService = new TrainingInstructorsImplService();
        trainingInstructorsService.setTrainingInstructorsDAO(dao);

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
     public void createTrainingInstructors()
     {
        TrainingInstructors t = new TrainingInstructors();
        t.setInstructorName("Emeal");




        dao.persist(t);
        EasyMock.replay(dao);
        trainingInstructorsService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingInstructors()
    {
       TrainingInstructors d = new TrainingInstructors();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingInstructorsService.find(new Long(1));
        EasyMock.verify(dao);


      }

}