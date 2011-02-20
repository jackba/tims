/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.JobsDAO;

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
public class JobsJUnitTest {

    private static Long jobsListid;
    private JobsDAO jobsDAO;
    private static ApplicationContext ctx;

    public JobsJUnitTest() {
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

        Jobs u = new Jobs();
        u.setJob_desc("coding");
        u.setJob_code("IT404");
        u.setJob_tittle(".NET Developer");

      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        jobsDAO.persist(u);
        jobsListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        Jobs u = jobsDAO.find(jobsListid);
        Assert.assertEquals("coding", u.getJob_desc());
    }

    @Test
    public void testUpdate() {
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
      Jobs u = jobsDAO.find(jobsListid);
        u.setJob_desc("modeling");
        jobsDAO.merge(u);
        Jobs p2 = jobsDAO.find(jobsListid);
        Assert.assertEquals("modeling", p2.getJob_desc());
    }

    @Test
    public void testCount() {
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        Long count = jobsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        List<Jobs> competencies = jobsDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        Jobs u = jobsDAO.getByPropertyName("jobDesc", "modeling");
        Assert.assertEquals("modeling", u.getJob_desc());

    }

    @Test
    public void testDelete() {
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        Jobs u = jobsDAO.find(jobsListid);
        jobsDAO.remove(u);

    }
}