/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingFunder;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingFunderImplService;
import com.hashthrims.repository.jpa.TrainingFunderDAO;
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
public class MockTrainingFunderJUnitTest {

    private static TrainingFunderDAO dao;
   private static TrainingFunderImplService trainingFunderService;

    public MockTrainingFunderJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingFunderDAO.class);
        trainingFunderService = new TrainingFunderImplService();
        trainingFunderService.setTrainingFunderDAO(dao);

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
     public void createBenefits()
     {

      TrainingFunder t = new TrainingFunder();
      t.setTrainingFunderName("Eustin");




        dao.persist(t);
        EasyMock.replay(dao);
        trainingFunderService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingFunder()
    {
       TrainingFunder d = new TrainingFunder();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingFunderService.find(new Long(1));
        EasyMock.verify(dao);


      }

}