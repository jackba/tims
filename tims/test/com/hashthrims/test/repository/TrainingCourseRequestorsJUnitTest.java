/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.TrainingCourseRequestorsDAO;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author staff
 */
public class TrainingCourseRequestorsJUnitTest {

    private static Long trainingCourseRequestorsId;
    private TrainingCourseRequestorsDAO TrainingCourseRequestorsDAO;
    private static ApplicationContext ctx;

    public TrainingCourseRequestorsJUnitTest() {
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
    public void createNewTrainingCourseRequestors() {
        //Create TrainingCourseRequestors Object You need to replace this with a Creational Design Pattern

        TrainingCourseRequestors t = new TrainingCourseRequestors();
        t.setRequestorName("Janie");

        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        TrainingCourseRequestorsDAO.persist(t);
        trainingCourseRequestorsId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        TrainingCourseRequestors t = TrainingCourseRequestorsDAO.find(trainingCourseRequestorsId);
        Assert.assertEquals("Janie", t.getRequestorName());
    }

    @Test
    public void testUpdate() {
        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        TrainingCourseRequestors t = TrainingCourseRequestorsDAO.find(trainingCourseRequestorsId);
        t.setRequestorName("Alex");
        TrainingCourseRequestorsDAO.merge(t);
        TrainingCourseRequestors p2 = TrainingCourseRequestorsDAO.find(trainingCourseRequestorsId);
        Assert.assertEquals("Alex", p2.getRequestorName());
    }

    @Test
    public void testCount() {
        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        Long count = TrainingCourseRequestorsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        List<TrainingCourseRequestors> universities = TrainingCourseRequestorsDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        TrainingCourseRequestors t = TrainingCourseRequestorsDAO.getByPropertyName("requestorName", "Alex");
        Assert.assertEquals("Alex", t.getRequestorName());

    }

    @Test
    public void testDelete() {
        TrainingCourseRequestorsDAO = (TrainingCourseRequestorsDAO) ctx.getBean("trainingCourseRequestorsDAO");
        TrainingCourseRequestors t = TrainingCourseRequestorsDAO.find(trainingCourseRequestorsId);
        TrainingCourseRequestorsDAO.remove(t);

    }

}