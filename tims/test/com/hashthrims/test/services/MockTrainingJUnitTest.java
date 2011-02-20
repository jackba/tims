/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.EmployeeMentoring;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingImplService;
import com.hashthrims.repository.jpa.TrainingDAO;
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
public class MockTrainingJUnitTest {
//
//    private static TrainingDAO dao;
//   private static TrainingImplService trainingService;
//
//    public MockTrainingJUnitTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//        IMocksControl mockCreator = EasyMock.createControl();
//        dao = mockCreator.createMock(TrainingDAO.class);
//        trainingService = new TrainingImplService();
//        trainingService.setTrainingDAO(dao);
//
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//     @Test
//     public void createBenefits()
//     {
//
//      EmployeeMentoring t = new EmployeeMentoring();
//      t.setNotes("staff training");
//
//
//
//
//        dao.persist(t);
//        EasyMock.replay(dao);
//        trainingService.persist(t);
//        EasyMock.verify(dao);
//
//    }
//    @Test
//    public void testGetTraining()
//    {
//       EmployeeMentoring d = new EmployeeMentoring();
//        EasyMock.reset(dao);
//        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
//        EasyMock.replay(dao);
//        trainingService.find(new Long(1));
//        EasyMock.verify(dao);
//
//
//      }

}