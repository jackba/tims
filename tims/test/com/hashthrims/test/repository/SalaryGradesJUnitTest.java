/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.SalaryGradesDAO;
import java.math.BigDecimal;

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
public class SalaryGradesJUnitTest {

    private static Long salarygradesListid;
    private SalaryGradesDAO salarygradesDAO;
    private static ApplicationContext ctx;

    public SalaryGradesJUnitTest() {
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

        SalaryGrade u = new SalaryGrade();
        u.setGradeName("Work");
        u.setNotes("Do your work");
        u.setMidAmount(new BigDecimal("5000.00"));
        u.setEndAmount(new BigDecimal("10000.00"));
      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
        salarygradesDAO.persist(u);
        salarygradesListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
        SalaryGrade u = salarygradesDAO.find(salarygradesListid);
        Assert.assertEquals("Work", u.getGradeName());
    }

    @Test
    public void testUpdate() {
        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
      SalaryGrade u = salarygradesDAO.find(salarygradesListid);
        u.setGradeName("BA");
        salarygradesDAO.merge(u);
        SalaryGrade p2 = salarygradesDAO.find(salarygradesListid);
        Assert.assertEquals("BA", p2.getGradeName());
    }

    @Test
    public void testCount() {
        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
        Long count = salarygradesDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
        List<SalaryGrade> competencies = salarygradesDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
        SalaryGrade u = salarygradesDAO.getByPropertyName("salaryName", "BA");
        Assert.assertEquals("BA", u.getGradeName());

    }

    @Test
    public void testDelete() {
        salarygradesDAO = (SalaryGradesDAO) ctx.getBean("salaryGradesDAO");
        SalaryGrade u = salarygradesDAO.find(salarygradesListid);
        salarygradesDAO.remove(u);

    }
}