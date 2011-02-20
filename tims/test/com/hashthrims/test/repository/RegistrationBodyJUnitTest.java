/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.repository.jpa.RegistrationBodyDAO;

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
public class RegistrationBodyJUnitTest {

    private static Long registrationBodyListid;
    private RegistrationBodyDAO registrationBodyDAO;
    private static ApplicationContext ctx;

    public RegistrationBodyJUnitTest() {
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

        RegistrationBody u = new RegistrationBody();
        u.setName("CPUT");
      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
        registrationBodyDAO.persist(u);
        registrationBodyListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
        RegistrationBody u = registrationBodyDAO.find(registrationBodyListid);
        Assert.assertEquals("CPUT", u.getName());
    }

    @Test
    public void testUpdate() {
        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
      RegistrationBody u = registrationBodyDAO.find(registrationBodyListid);
        u.setName("UCT");
        registrationBodyDAO.merge(u);
        RegistrationBody p2 = registrationBodyDAO.find(registrationBodyListid);
        Assert.assertEquals("UCT", p2.getName());
    }

    @Test
    public void testCount() {
        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
        Long count = registrationBodyDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
        List<RegistrationBody> competencies = registrationBodyDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
        RegistrationBody u = registrationBodyDAO.getByPropertyName("name", "UCT");
        Assert.assertEquals("UCT", u.getName());

    }

    @Test
    public void testDelete() {
        registrationBodyDAO = (RegistrationBodyDAO) ctx.getBean("registrationBodyDAO");
        RegistrationBody u = registrationBodyDAO.find(registrationBodyListid);
        registrationBodyDAO.remove(u);

    }
}