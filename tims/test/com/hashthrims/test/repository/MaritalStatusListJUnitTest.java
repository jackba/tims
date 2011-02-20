/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.repository.jpa.MaritalStatusListDAO;

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
public class MaritalStatusListJUnitTest {

    private static Long maritalStatusListid;
    private MaritalStatusListDAO maritalStatusListtDAO;
    private static ApplicationContext ctx;

    public MaritalStatusListJUnitTest() {
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

        MaritalStatusList u = new MaritalStatusList();
        u.setStatus_name("Married");

        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
        maritalStatusListtDAO.persist(u);
        maritalStatusListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
        MaritalStatusList u = maritalStatusListtDAO.find(maritalStatusListid);
        Assert.assertEquals("Married", u.getStatus_name());
    }

    @Test
    public void testUpdate() {
        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
      MaritalStatusList u = maritalStatusListtDAO.find(maritalStatusListid);
        u.setStatus_name("single");
        maritalStatusListtDAO.merge(u);
        MaritalStatusList p2 = maritalStatusListtDAO.find(maritalStatusListid);
        Assert.assertEquals("single", p2.getStatus_name());
    }

    @Test
    public void testCount() {
        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
        Long count = maritalStatusListtDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
        List<MaritalStatusList> universities = maritalStatusListtDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
        MaritalStatusList u = maritalStatusListtDAO.getByPropertyName("statusName", "single");
        Assert.assertEquals("single", u.getStatus_name());

    }

    @Test
    public void testDelete() {
        maritalStatusListtDAO = (MaritalStatusListDAO) ctx.getBean("maritalStatusListDAO");
        MaritalStatusList u = maritalStatusListtDAO.find(maritalStatusListid);
        maritalStatusListtDAO.remove(u);

    }
}