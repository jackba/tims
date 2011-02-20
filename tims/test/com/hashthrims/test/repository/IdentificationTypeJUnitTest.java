/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.repository.jpa.IdentificationTypeDAO;

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
public class IdentificationTypeJUnitTest {

    private static Long identificationTypeid;
    private IdentificationTypeDAO identificationTypetDAO;
    private static ApplicationContext ctx;

    public IdentificationTypeJUnitTest() {
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

        IdentificationType u = new IdentificationType();
        u.setIdentity_name_type("passport");

        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
        identificationTypetDAO.persist(u);
        identificationTypeid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
        IdentificationType u = identificationTypetDAO.find(identificationTypeid);
        Assert.assertEquals("passport", u.getIdentity_name_type());
    }

    @Test
    public void testUpdate() {
        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
      IdentificationType u = identificationTypetDAO.find(identificationTypeid);
        u.setIdentity_name_type("identity book");
        identificationTypetDAO.merge(u);
        IdentificationType p2 = identificationTypetDAO.find(identificationTypeid);
        Assert.assertEquals("identity book", p2.getIdentity_name_type());
    }

    @Test
    public void testCount() {
        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
        Long count = identificationTypetDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
        List<IdentificationType> universities = identificationTypetDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
        IdentificationType u = identificationTypetDAO.getByPropertyName("identityNameType", "identity book");
        Assert.assertEquals("identity book", u.getIdentity_name_type());

    }

    @Test
    public void testDelete() {
        identificationTypetDAO = (IdentificationTypeDAO) ctx.getBean("identificationTypeDAO");
        IdentificationType u = identificationTypetDAO.find(identificationTypeid);
        identificationTypetDAO.remove(u);

    }
}