/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.BenefitType;

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
public class BenefitTypeJUnitTest {

    private static Long benefitTypeid;
    private BenefitTypeDAO benefitTypeDAO;
    private static ApplicationContext ctx;

    public BenefitTypeJUnitTest() {
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

        BenefitType u = new BenefitType();
        u.setBenefit_type_name("Medical aid");

        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        benefitTypeDAO.persist(u);
        benefitTypeid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        BenefitType u = benefitTypeDAO.find(benefitTypeid);
        Assert.assertEquals("Medical aid", u.getBenefit_type_name());
    }

    @Test
    public void testUpdate() {
        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        BenefitType u = benefitTypeDAO.find(benefitTypeid);
        u.setBenefit_type_name("car allowance");
        benefitTypeDAO.merge(u);
        BenefitType p2 = benefitTypeDAO.find(benefitTypeid);
        Assert.assertEquals("car allowance", p2.getBenefit_type_name());
    }

    @Test
    public void testCount() {
        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        Long count = benefitTypeDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        List<BenefitType> universities = benefitTypeDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        BenefitType u = benefitTypeDAO.getByPropertyName("benefitTypeName", "car allowance");
        Assert.assertEquals("car allowance", u.getBenefit_type_name());

    }

    @Test
    public void testDelete() {
        benefitTypeDAO = (BenefitTypeDAO) ctx.getBean("benefitTypeDAO");
        BenefitType u = benefitTypeDAO.find(benefitTypeid);
        benefitTypeDAO.remove(u);

    }
}