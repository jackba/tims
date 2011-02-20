/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.TrainingCourseCategoryDAO;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
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
public class TrainingCourseCategoryJUnitTest {

    private static Long trainingCourseCategoryId;
    private TrainingCourseCategoryDAO TrainingCourseCategoryDAO;
    private static ApplicationContext ctx;

    public TrainingCourseCategoryJUnitTest() {
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
    public void createNewTrainingCourseCategory() {
        //Create TrainingCourseCategory Object You need to replace this with a Creational Design Pattern

        TrainingCourseCategory t = new TrainingCourseCategory();
        t.setCategoryName("Technical");

        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        TrainingCourseCategoryDAO.persist(t);
        trainingCourseCategoryId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        TrainingCourseCategory t = TrainingCourseCategoryDAO.find(trainingCourseCategoryId);
        Assert.assertEquals("Technical", t.getCategoryName());
    }

    @Test
    public void testUpdate() {
        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        TrainingCourseCategory t = TrainingCourseCategoryDAO.find(trainingCourseCategoryId);
        t.setCategoryName("Management");
        TrainingCourseCategoryDAO.merge(t);
        TrainingCourseCategory p2 = TrainingCourseCategoryDAO.find(trainingCourseCategoryId);
        Assert.assertEquals("Management", p2.getCategoryName());
    }

    @Test
    public void testCount() {
        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        Long count = TrainingCourseCategoryDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        List<TrainingCourseCategory> universities = TrainingCourseCategoryDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        TrainingCourseCategory t = TrainingCourseCategoryDAO.getByPropertyName("categoryName", "Management");
        Assert.assertEquals("Management", t.getCategoryName());

    }

    @Test
    public void testDelete() {
        TrainingCourseCategoryDAO = (TrainingCourseCategoryDAO) ctx.getBean("trainingCourseCategoryDAO");
        TrainingCourseCategory t = TrainingCourseCategoryDAO.find(trainingCourseCategoryId);
        TrainingCourseCategoryDAO.remove(t);

    }

}