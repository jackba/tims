/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.regionlist.Country;
import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hashthrims.repository.jpa.TrainingDAO;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stud
 */
public class TrainingJUnitTest {

//    private static Long trainingId;
//    private TrainingDAO TrainingDAO;
//    private static ApplicationContext ctx;
//
//    public TrainingJUnitTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//        ctx = new ClassPathXmlApplicationContext("classpath:com/hashthrims/infrastructure/conf/applicationContext-*.xml");
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
//    @Test
//    public void createNewTraining() {
//        //Create EmployeeMentoring Object You need to replace this with a Creational Design Pattern
////
////        EmployeeMentoring t = new EmployeeMentoring();
//////        t.setNotes("staff training");
//////
//////        t.setRequestedDate(new Date());
//////
//////        t.setNotes("Completed");
//////        t.setCompetency(true);
//////        t.setRetraining(true);
////
////        ScheduledCourses sc = new ScheduledCourses();
////        sc.setClassNotes("Completed");
////        sc.setClassSite("Tygerberg");
////        sc.setClassloc(null);
////        sc.setDistrict("Western");
////        sc.setNumOfStuds(20);
////        sc.setStartDate(new Date());
////        sc.setEndDate(new Date());
////
////        Country c = new Country();
////        c.setAlphaCode("981");
////        c.setCountryName("England");
////        c.setLocation(true);
////        c.setNumericCode(1);
////        c.setPrimaryCountry(true);
////
////        TrainingCourseEvaluation tce = new TrainingCourseEvaluation();
////        tce.setEvaluationName("Microsoft");
////
////        CompetencyEvaluation ce = new CompetencyEvaluation();
////        ce.setCompt_type_name("Interviews");
////
////
////
////        TrainingCourseRequestors tcr = new TrainingCourseRequestors();
////        tcr.setRequestorName("Leon");
////
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        TrainingDAO.persist(t);
////        trainingId = t.getId();
////        Assert.assertNotNull(t.getId());
////    }
////
////    @Test
////    public void testRead() {
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        EmployeeMentoring t = TrainingDAO.find(trainingId);
////        Assert.assertEquals("Completed", t.getNotes());
////    }
////
////    @Test
////    public void testUpdate() {
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        EmployeeMentoring t = TrainingDAO.find(trainingId);
////        t.setNotes("training completed");
////        TrainingDAO.merge(t);
////        EmployeeMentoring p2 = TrainingDAO.find(trainingId);
////        Assert.assertEquals("training completed", p2.getNotes());
////    }
////
////    @Test
////    public void testCount() {
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        Long count = TrainingDAO.count();
////        Assert.assertEquals(new Long(1), count);
////    }
////
////    @Test
////    public void testList() {
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        List<EmployeeMentoring> universities = TrainingDAO.findAll();
////        Assert.assertTrue(universities.size() > 0);
////    }
////
////    @Test
////    public void tstGetByParamater() {
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        EmployeeMentoring t = TrainingDAO.getByPropertyName("notes", "training completed");
////        Assert.assertEquals("training completed", t.getNotes());
////
////    }
////
////    @Test
////    public void testDelete() {
////        TrainingDAO = (TrainingDAO) ctx.getBean("trainingDAO");
////        EmployeeMentoring t = TrainingDAO.find(trainingId);
////        TrainingDAO.remove(t);
////
////    }
}
