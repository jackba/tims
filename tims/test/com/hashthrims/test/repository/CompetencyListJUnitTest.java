/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.CompetencyListDAO;

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
public class CompetencyListJUnitTest {

    private static Long competencyListListid;
    private CompetencyListDAO competencyListDAO;
    private static ApplicationContext ctx;

    public CompetencyListJUnitTest() {
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

        CompetencyList u = new CompetencyList();
        u.setComp_name("Knowlegde");
        u.setNotes("Everybody must undergo a test before being interviewed");

        CompetencyType c = new CompetencyType();
        c.setComp_name_typ("Test");

        //u.setCompType(c);
        



        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
        competencyListDAO.persist(u);
        competencyListListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
        CompetencyList u = competencyListDAO.find(competencyListListid);
        Assert.assertEquals("Knowlegde", u.getComp_name());
    }

    @Test
    public void testUpdate() {
        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
      CompetencyList u = competencyListDAO.find(competencyListListid);
        u.setComp_name("Skills");
        competencyListDAO.merge(u);
        CompetencyList p2 = competencyListDAO.find(competencyListListid);
        Assert.assertEquals("Skills", p2.getComp_name());
    }

    @Test
    public void testCount() {
        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
        Long count = competencyListDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
        List<CompetencyList> competencies = competencyListDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
        CompetencyList u = competencyListDAO.getByPropertyName("compName", "Skills");
        Assert.assertEquals("Skills", u.getComp_name());

    }

    @Test
    public void testDelete() {
        competencyListDAO = (CompetencyListDAO) ctx.getBean("competencyListDAO");
        CompetencyList u = competencyListDAO.find(competencyListListid);
        competencyListDAO.remove(u);

    }
}