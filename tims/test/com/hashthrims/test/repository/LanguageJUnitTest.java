/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.Language;

import java.util.List;
import org.junit.Assert;

import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.LanguageDAO;
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
public class LanguageJUnitTest {

    private static Long languageid;
    private LanguageDAO languageDAO;
    private static ApplicationContext ctx;

    public LanguageJUnitTest() {
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
    public void createNewLanguage() {
        //Create Language Object You need to replace this with a Creational Design Pattern

        Language u = new Language();
        u.setLanguage_name("Xhosa");

        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        languageDAO.persist(u);
        languageid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        Language u = languageDAO.find(languageid);
        Assert.assertEquals("Xhosa", u.getLanguage_name());
    }

    @Test
    public void testUpdate() {
        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        Language u = languageDAO.find(languageid);
        u.setLanguage_name("English");
        languageDAO.merge(u);
        Language p2 = languageDAO.find(languageid);
        Assert.assertEquals("English", p2.getLanguage_name());
    }

    @Test
    public void testCount() {
        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        Long count = languageDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        List<Language> universities = languageDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        Language u = languageDAO.getByPropertyName("languageName", "English");
        Assert.assertEquals("English", u.getLanguage_name());

    }

    @Test
    public void testDelete() {
        languageDAO = (LanguageDAO) ctx.getBean("languageDAO");
        Language u = languageDAO.find(languageid);
        languageDAO.remove(u);

    }
}