/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.LanguageProficiency;

import java.util.List;
import org.junit.Assert;

import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.LanguageProficiencyDAO;
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
public class LanguageProficiencyJUnitTest {

    private static Long languageProficiencyid;
    private LanguageProficiencyDAO languageProficiencyDAO;
    private static ApplicationContext ctx;

    public LanguageProficiencyJUnitTest() {
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
    public void createNewLanguageProficiency() {
        //Create LanguageProficiency Object You need to replace this with a Creational Design Pattern

        LanguageProficiency u = new LanguageProficiency();
        u.setProficency("Working");

        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        languageProficiencyDAO.persist(u);
        languageProficiencyid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        LanguageProficiency u = languageProficiencyDAO.find(languageProficiencyid);
        Assert.assertEquals("Working", u.getProficency());
    }

    @Test
    public void testUpdate() {
        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        LanguageProficiency u = languageProficiencyDAO.find(languageProficiencyid);
        u.setProficency("professional");
        languageProficiencyDAO.merge(u);
        LanguageProficiency p2 = languageProficiencyDAO.find(languageProficiencyid);
        Assert.assertEquals("professional", p2.getProficency());
    }

    @Test
    public void testCount() {
        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        Long count = languageProficiencyDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        List<LanguageProficiency> universities = languageProficiencyDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        LanguageProficiency u = languageProficiencyDAO.getByPropertyName("proficency", "professional");
        Assert.assertEquals("professional", u.getProficency());

    }

    @Test
    public void testDelete() {
        languageProficiencyDAO = (LanguageProficiencyDAO) ctx.getBean("languageProficiencyDAO");
        LanguageProficiency u = languageProficiencyDAO.find(languageProficiencyid);
        languageProficiencyDAO.remove(u);

    }
}