/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.TrainingCourseStatusDAO;
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

public class TrainingCourseStatusJUnitTest {

    private static Long trainingCourseStatusId;
    private TrainingCourseStatusDAO TrainingCourseStatusDAO;
    private static ApplicationContext ctx;

    public TrainingCourseStatusJUnitTest() {
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
    public void createNewTrainingCourseStatus() {
        //Create TrainingCourseStatus Object You need to replace this with a Creational Design Pattern

        TrainingCourseStatus t = new TrainingCourseStatus();
        t.setStatusName("To be done");

        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        TrainingCourseStatusDAO.persist(t);
        trainingCourseStatusId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        TrainingCourseStatus t = TrainingCourseStatusDAO.find(trainingCourseStatusId);
        Assert.assertEquals("To be done", t.getStatusName());
    }

    @Test
    public void testUpdate() {
        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        TrainingCourseStatus t = TrainingCourseStatusDAO.find(trainingCourseStatusId);
        t.setStatusName("Completed");
        TrainingCourseStatusDAO.merge(t);
        TrainingCourseStatus p2 = TrainingCourseStatusDAO.find(trainingCourseStatusId);
        Assert.assertEquals("Completed", p2.getStatusName());
    }

    @Test
    public void testCount() {
        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        Long count = TrainingCourseStatusDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        List<TrainingCourseStatus> universities = TrainingCourseStatusDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        TrainingCourseStatus t = TrainingCourseStatusDAO.getByPropertyName("statusName", "Completed");
        Assert.assertEquals("Completed", t.getStatusName());

    }

    @Test
    public void testDelete() {
        TrainingCourseStatusDAO = (TrainingCourseStatusDAO) ctx.getBean("trainingCourseStatusDAO");
        TrainingCourseStatus t = TrainingCourseStatusDAO.find(trainingCourseStatusId);
        TrainingCourseStatusDAO.remove(t);

    }

}