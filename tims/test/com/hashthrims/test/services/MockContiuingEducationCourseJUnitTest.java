/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;

import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import com.hashthrims.services.Impl.ContiuingEducationCourseImplService;
import com.hashthrims.repository.jpa.ContiuingEducationCourseDAO;
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
public class MockContiuingEducationCourseJUnitTest {

   private static ContiuingEducationCourseDAO dao;
   private static ContiuingEducationCourseImplService benefitTypeService;

    public MockContiuingEducationCourseJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(ContiuingEducationCourseDAO.class);
        benefitTypeService = new ContiuingEducationCourseImplService();
        benefitTypeService.setContiuingEducationCourseDAO(dao);

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

      ContiuingEducationCourse c = new ContiuingEducationCourse();
       c.setNameOfContinueCourse("MsOffice2007");
       c.setCreditHours("8");



        dao.persist(c);
        EasyMock.replay(dao);
        benefitTypeService.persist(c);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetContiuingEducationCourse()
    {
       ContiuingEducationCourse d = new ContiuingEducationCourse();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        benefitTypeService.find(new Long(1));
        EasyMock.verify(dao);


      }

}