/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa.CompetencyEvaluationDAO;

import java.util.List;
import org.junit.Assert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author boniface
 */
public class CompetencyEvaluationJUnitTest {

    private static Long competencyEvaluationListid;
    private CompetencyEvaluationDAO competencyEvaluationDAO;
    private static ApplicationContext ctx;

    public CompetencyEvaluationJUnitTest() {
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
    public void createNewBenefitType() {
        //Create BenefitType Object You need to replace this with a Creational Design Pattern

        CompetencyEvaluation u = new CompetencyEvaluation();
        u.setCompt_type_name("Interviews");

        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
        competencyEvaluationDAO.persist(u);
        competencyEvaluationListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
        CompetencyEvaluation u = competencyEvaluationDAO.find(competencyEvaluationListid);
        Assert.assertEquals("Interviews", u.getCompt_type_name());
    }

    @Test
    public void testUpdate() {
        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
      CompetencyEvaluation u = competencyEvaluationDAO.find(competencyEvaluationListid);
        u.setCompt_type_name("Exam");
        competencyEvaluationDAO.merge(u);
        CompetencyEvaluation p2 = competencyEvaluationDAO.find(competencyEvaluationListid);
        Assert.assertEquals("Exam", p2.getCompt_type_name());
    }

    @Test
    public void testCount() {
        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
        Long count = competencyEvaluationDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
        List<CompetencyEvaluation> competencies = competencyEvaluationDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
        CompetencyEvaluation u = competencyEvaluationDAO.getByPropertyName("compTypeName", "Exam");
        Assert.assertEquals("Exam", u.getCompt_type_name());

    }

    @Test
    public void testDelete() {
        competencyEvaluationDAO = (CompetencyEvaluationDAO) ctx.getBean("competencyEvaluationDAO");
        CompetencyEvaluation u = competencyEvaluationDAO.find(competencyEvaluationListid);
        competencyEvaluationDAO.remove(u);

    }
}