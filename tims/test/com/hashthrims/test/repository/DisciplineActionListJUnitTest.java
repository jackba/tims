/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa.DisciplineActionTypeListDAO;

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
public class DisciplineActionListJUnitTest {

    private static Long disciplineActionTypeListid;
    private DisciplineActionTypeListDAO disciplineActionTypeListDAO;
    private static ApplicationContext ctx;

    public DisciplineActionListJUnitTest() {
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

        DisciplineActionTypeList u = new DisciplineActionTypeList();
        u.setDisplineName("Warning");

        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
        disciplineActionTypeListDAO.persist(u);
        disciplineActionTypeListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
        DisciplineActionTypeList u = disciplineActionTypeListDAO.find(disciplineActionTypeListid);
        Assert.assertEquals("Warning", u.getDisplineName());
    }

    @Test
    public void testUpdate() {
        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
      DisciplineActionTypeList u = disciplineActionTypeListDAO.find(disciplineActionTypeListid);
        u.setDisplineName("suspension");
        disciplineActionTypeListDAO.merge(u);
        DisciplineActionTypeList p2 = disciplineActionTypeListDAO.find(disciplineActionTypeListid);
        Assert.assertEquals("suspension", p2.getDisplineName());
    }

    @Test
    public void testCount() {
        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
        Long count = disciplineActionTypeListDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
        List<DisciplineActionTypeList> competencies = disciplineActionTypeListDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
        DisciplineActionTypeList u = disciplineActionTypeListDAO.getByPropertyName("displineName", "suspension");
        Assert.assertEquals("suspension", u.getDisplineName());

    }

    @Test
    public void testDelete() {
        disciplineActionTypeListDAO = (DisciplineActionTypeListDAO) ctx.getBean("disciplineActionTypeListDAO");
        DisciplineActionTypeList u = disciplineActionTypeListDAO.find(disciplineActionTypeListid);
        disciplineActionTypeListDAO.remove(u);

    }
}