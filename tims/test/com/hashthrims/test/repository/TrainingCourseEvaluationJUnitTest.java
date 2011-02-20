/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.TrainingCourseEvaluationDAO;
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
public class TrainingCourseEvaluationJUnitTest {

    private static Long trainingCourseEvaluationId;
    private TrainingCourseEvaluationDAO TrainingCourseEvaluationDAO;
    private static ApplicationContext ctx;

    public TrainingCourseEvaluationJUnitTest() {
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
    public void createNewTrainingCourseEvaluation() {
        //Create TrainingCourseEvaluation Object You need to replace this with a Creational Design Pattern

        TrainingCourseEvaluation t = new TrainingCourseEvaluation();
        t.setEvaluationName("Medical");

        CompetencyEvaluation ce = new CompetencyEvaluation();
        ce.setCompt_type_name("Competant");
        

        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        TrainingCourseEvaluationDAO.persist(t);
        trainingCourseEvaluationId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        TrainingCourseEvaluation t = TrainingCourseEvaluationDAO.find(trainingCourseEvaluationId);
        Assert.assertEquals("Medical", t.getEvaluationName());
    }

    @Test
    public void testUpdate() {
        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        TrainingCourseEvaluation t = TrainingCourseEvaluationDAO.find(trainingCourseEvaluationId);
        t.setEvaluationName("Psychological");
        TrainingCourseEvaluationDAO.merge(t);
        TrainingCourseEvaluation p2 = TrainingCourseEvaluationDAO.find(trainingCourseEvaluationId);
        Assert.assertEquals("Psychological", p2.getEvaluationName());
    }

    @Test
    public void testCount() {
        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        Long count = TrainingCourseEvaluationDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        List<TrainingCourseEvaluation> universities = TrainingCourseEvaluationDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        TrainingCourseEvaluation t = TrainingCourseEvaluationDAO.getByPropertyName("evaluationName", "Psychological");
        Assert.assertEquals("Psychological", t.getEvaluationName());

    }

    @Test
    public void testDelete() {
        TrainingCourseEvaluationDAO = (TrainingCourseEvaluationDAO) ctx.getBean("trainingCourseEvaluationDAO");
        TrainingCourseEvaluation t = TrainingCourseEvaluationDAO.find(trainingCourseEvaluationId);
        TrainingCourseEvaluationDAO.remove(t);

    }

}