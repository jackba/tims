/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.StatusDAO;

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
public class StatusJUnitTest {

    private static Long statusListid;
    private StatusDAO statusDAO;
    private static ApplicationContext ctx;

    public StatusJUnitTest() {
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

        Status u = new Status();
        u.setStatus("Accepted");
      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
        statusDAO.persist(u);
        statusListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
        Status u = statusDAO.find(statusListid);
        Assert.assertEquals("Accepted", u.getStatus());
    }

    @Test
    public void testUpdate() {
        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
      Status u = statusDAO.find(statusListid);
        u.setStatus("Rejected");
        statusDAO.merge(u);
        Status p2 = statusDAO.find(statusListid);
        Assert.assertEquals("Rejected", p2.getStatus());
    }

    @Test
    public void testCount() {
        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
        Long count = statusDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
        List<Status> competencies = statusDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
        Status u = statusDAO.getByPropertyName("status", "Rejected");
        Assert.assertEquals("Rejected", u.getStatus());

    }

    @Test
    public void testDelete() {
        statusDAO = (StatusDAO) ctx.getBean("statusDAO");
        Status u = statusDAO.find(statusListid);
        statusDAO.remove(u);

    }
}