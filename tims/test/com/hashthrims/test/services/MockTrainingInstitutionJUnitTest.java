/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.TrainingInstitution;
import org.easymock.IMocksControl;
import org.easymock.EasyMock;
import com.hashthrims.services.Impl.TrainingInstitutionImplService;
import com.hashthrims.repository.jpa.TrainingInstitutionDAO;
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
public class MockTrainingInstitutionJUnitTest {

    private static TrainingInstitutionDAO dao;
   private static TrainingInstitutionImplService trainingInstitutionService;

    public MockTrainingInstitutionJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(TrainingInstitutionDAO.class);
        trainingInstitutionService = new TrainingInstitutionImplService();
        trainingInstitutionService.setTrainingInstitutionDAO(dao);

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
     public void createTrainingInstitution()
     {
        TrainingInstitution t = new TrainingInstitution();
        t.setTrainingInstitution("Brad");





        dao.persist(t);
        EasyMock.replay(dao);
        trainingInstitutionService.persist(t);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetTrainingInstitution()
    {
       TrainingInstitution d = new TrainingInstitution();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        trainingInstitutionService.find(new Long(1));
        EasyMock.verify(dao);


      }

}