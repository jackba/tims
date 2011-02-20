/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.DegreeDAO;

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
public class DegreeJUnitTest {

    private static Long degreeListid;
    private DegreeDAO degreeDAO;
    private static ApplicationContext ctx;

    public DegreeJUnitTest() {
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

        Degree u = new Degree();
        u.setDegree_name("BSc");
      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
        degreeDAO.persist(u);
        degreeListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
        Degree u = degreeDAO.find(degreeListid);
        Assert.assertEquals("BSc", u.getDegree_name());
    }

    @Test
    public void testUpdate() {
        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
      Degree u = degreeDAO.find(degreeListid);
        u.setDegree_name("BA");
        degreeDAO.merge(u);
        Degree p2 = degreeDAO.find(degreeListid);
        Assert.assertEquals("BA", p2.getDegree_name());
    }

    @Test
    public void testCount() {
        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
        Long count = degreeDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
        List<Degree> competencies = degreeDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
        Degree u = degreeDAO.getByPropertyName("degreeName", "BA");
        Assert.assertEquals("BA", u.getDegree_name());

    }

    @Test
    public void testDelete() {
        degreeDAO = (DegreeDAO) ctx.getBean("degreeDAO");
        Degree u = degreeDAO.find(degreeListid);
        degreeDAO.remove(u);

    }
}