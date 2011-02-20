/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa.CompetencyTypeDAO;
import com.hashthrims.repository.jpa.CompetencyTypeDAO;

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
public class CompetencyTypeJUnitTest {

    private static Long competencyTypeid;
    private CompetencyTypeDAO competencyTypeDAO;
    private static ApplicationContext ctx;

    public CompetencyTypeJUnitTest() {
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

        CompetencyType u = new CompetencyType();
          u.setComp_name_typ("Ability");




        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
        competencyTypeDAO.persist(u);
        competencyTypeid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
        CompetencyType u = competencyTypeDAO.find(competencyTypeid);
        Assert.assertEquals("Ability", u.getComp_name_typ());
    }

    @Test
    public void testUpdate() {
        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
      CompetencyType u = competencyTypeDAO.find(competencyTypeid);
        u.setComp_name_typ("Skills");
        competencyTypeDAO.merge(u);
        CompetencyType p2 = competencyTypeDAO.find(competencyTypeid);
        Assert.assertEquals("Skills", p2.getComp_name_typ());
    }

    @Test
    public void testCount() {
        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
        Long count = competencyTypeDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
        List<CompetencyType> competencies = competencyTypeDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
        CompetencyType u = competencyTypeDAO.getByPropertyName("compNameType", "Skills");
        Assert.assertEquals("Skills", u.getComp_name_typ());

    }

    @Test
    public void testDelete() {
        competencyTypeDAO = (CompetencyTypeDAO) ctx.getBean("competencyTypeDAO");
        CompetencyType u = competencyTypeDAO.find(competencyTypeid);
        competencyTypeDAO.remove(u);

    }
}