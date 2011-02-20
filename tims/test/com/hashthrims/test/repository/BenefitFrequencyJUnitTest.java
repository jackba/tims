/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.repository.jpa.BenefitFrequencyDAO;

import java.util.List;
import org.junit.Assert;

import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.BenefitTypeDAO;
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
public class BenefitFrequencyJUnitTest {

    private static Long benefitFrequenceid;
    private BenefitFrequencyDAO benefitFrequencyDAO;
    private static ApplicationContext ctx;

    public BenefitFrequencyJUnitTest() {
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

        BenefitFrequency u = new BenefitFrequency();
        u.setFrequency("10");

        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        benefitFrequencyDAO.persist(u);
        benefitFrequenceid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        BenefitFrequency u = benefitFrequencyDAO.find(benefitFrequenceid);
        Assert.assertEquals("10", u.getFrequency());
    }

    @Test
    public void testUpdate() {
        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        BenefitFrequency u = benefitFrequencyDAO.find(benefitFrequenceid);
        u.setFrequency("20");
        benefitFrequencyDAO.merge(u);
        BenefitFrequency p2 = benefitFrequencyDAO.find(benefitFrequenceid);
        Assert.assertEquals("20", p2.getFrequency());
    }

    @Test
    public void testCount() {
        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        Long count = benefitFrequencyDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        List<BenefitFrequency> universities = benefitFrequencyDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        BenefitFrequency u = benefitFrequencyDAO.getByPropertyName("frequency", "20");
        Assert.assertEquals("20", u.getFrequency());

    }

    @Test
    public void testDelete() {
        benefitFrequencyDAO = (BenefitFrequencyDAO) ctx.getBean("benefitFrequencyDAO");
        BenefitFrequency u = benefitFrequencyDAO.find(benefitFrequenceid);
        benefitFrequencyDAO.remove(u);

    }
}