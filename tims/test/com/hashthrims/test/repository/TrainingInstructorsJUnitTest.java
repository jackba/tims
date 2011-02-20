/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.traininglist.TrainingInstructors;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
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
public class TrainingInstructorsJUnitTest {

    private static Long trainingInstructorsId;
    private TrainingInstructorsDAO TrainingInstructorsDAO;
    private static ApplicationContext ctx;

    public TrainingInstructorsJUnitTest() {
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
    public void createNewTrainingInstructors() {
        //Create TrainingInstructors Object You need to replace this with a Creational Design Pattern

        TrainingInstructors t = new TrainingInstructors();
        t.setInstructorName("Eustin");

        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        TrainingInstructorsDAO.persist(t);
        trainingInstructorsId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        TrainingInstructors t = TrainingInstructorsDAO.find(trainingInstructorsId);
        Assert.assertEquals("Eustin", t.getInstructorName());
    }

    @Test
    public void testUpdate() {
        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        TrainingInstructors t = TrainingInstructorsDAO.find(trainingInstructorsId);
        t.setInstructorName("John");
        TrainingInstructorsDAO.merge(t);
        TrainingInstructors p2 = TrainingInstructorsDAO.find(trainingInstructorsId);
        Assert.assertEquals("John", p2.getInstructorName());
    }

    @Test
    public void testCount() {
        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        Long count = TrainingInstructorsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        List<TrainingInstructors> universities = TrainingInstructorsDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        TrainingInstructors t = TrainingInstructorsDAO.getByPropertyName("instructorName", "John");
        Assert.assertEquals("John", t.getInstructorName());

    }

    @Test
    public void testDelete() {
        TrainingInstructorsDAO = (TrainingInstructorsDAO) ctx.getBean("trainingInstructorsDAO");
        TrainingInstructors t = TrainingInstructorsDAO.find(trainingInstructorsId);
        TrainingInstructorsDAO.remove(t);

    }

}