/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.regionlist.Country;
import java.util.Date;
import java.util.*;
import junit.framework.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.ScheduledCoursesDAO;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingInstructors;
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
public class ScheduledCoursesJUnitTest {

    private static Long scheduledCoursesId;
    private ScheduledCoursesDAO ScheduledCoursesDAO;
    private static ApplicationContext ctx;

    public ScheduledCoursesJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new ClassPathXmlApplicationContext("classpath:com/hashthrims/infrastructure/conf/applicationContext-*.xml");

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
    public void createNewScheduledCourses() {
        //Create ScheduledCourses Object You need to replace this with a Creational Design Pattern

        ScheduledCourses s = new ScheduledCourses();
        s.setDistrict("Southern");
        s.setClassNotes("In progress");
        s.setClassSite("CPUT");
        s.setStartDate(new Date());
        s.setEndDate(new Date());
        s.setNumOfStuds(20);

        TrainingInstructors cInstructor = new TrainingInstructors();
        cInstructor.setInstructorName("Boniface Kabaso");

        List<TrainingInstructors> classInstructor = new ArrayList<TrainingInstructors>();
        classInstructor.add(cInstructor);
        s.setClassInstructor(classInstructor);

        Country c = new Country();
        c.setAlphaCode("981");
        c.setCountryName("South Africa");
        c.setLocation(true);
        c.setNumericCode(1);
        c.setPrimaryCountry(true);

        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        ScheduledCoursesDAO.persist(s);
        scheduledCoursesId = s.getId();
        Assert.assertNotNull(s.getId());
    }

    @Test
    public void testRead() {
        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        ScheduledCourses s = ScheduledCoursesDAO.find(scheduledCoursesId);
        Assert.assertEquals("Southern", s.getDistrict());
    }

    @Test
    public void testUpdate() {
        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        ScheduledCourses s = ScheduledCoursesDAO.find(scheduledCoursesId);
        s.setDistrict("Northern");
        ScheduledCoursesDAO.merge(s);
        ScheduledCourses p2 = ScheduledCoursesDAO.find(scheduledCoursesId);
        Assert.assertEquals("Northern", p2.getDistrict());
    }

    @Test
    public void testCount() {
        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        Long count = ScheduledCoursesDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        List<ScheduledCourses> universities = ScheduledCoursesDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        ScheduledCourses s = ScheduledCoursesDAO.getByPropertyName("district", "Northern");
        Assert.assertEquals("Northern", s.getDistrict());

    }

    @Test
    public void testDelete() {
        ScheduledCoursesDAO = (ScheduledCoursesDAO) ctx.getBean("scheduledCoursesDAO");
        ScheduledCourses s = ScheduledCoursesDAO.find(scheduledCoursesId);
        ScheduledCoursesDAO.remove(s);

    }

}