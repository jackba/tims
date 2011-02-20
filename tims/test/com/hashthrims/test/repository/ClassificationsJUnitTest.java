/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.ClassificationDAO;

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
public class ClassificationsJUnitTest {

    private static Long classificationsListid;
    private ClassificationDAO classificationsDAO;
    private static ApplicationContext ctx;

    public ClassificationsJUnitTest() {
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

        Classifications u = new Classifications();
        u.setJob_code("BSc");
        u.setJob_desc("programming");
        u.setJob_name("Developer");
        EducationType e = new EducationType();
        e.setEduc_type_name("further education");



        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        classificationsDAO.persist(u);
        classificationsListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        Classifications u = classificationsDAO.find(classificationsListid);
        Assert.assertEquals("Developer", u.getJob_name());
    }

    @Test
    public void testUpdate() {
        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
      Classifications u = classificationsDAO.find(classificationsListid);
        u.setJob_name("Engineer");
        classificationsDAO.merge(u);
        Classifications p2 = classificationsDAO.find(classificationsListid);
        Assert.assertEquals("Engineer", p2.getJob_name());
    }

    @Test
    public void testCount() {
        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        Long count = classificationsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        List<Classifications> competencies = classificationsDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        Classifications u = classificationsDAO.getByPropertyName("jobName", "Engineer");
        Assert.assertEquals("Engineer", u.getJob_name());

    }

    @Test
    public void testDelete() {
        classificationsDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        Classifications u = classificationsDAO.find(classificationsListid);
        classificationsDAO.remove(u);

    }
}