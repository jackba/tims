/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa.DepartureReasonsDAO;

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
public class DepartureReasonsJUnitTest {

    private static Long departureReasonsid;
    private DepartureReasonsDAO departureReasonsDAO;
    private static ApplicationContext ctx;

    public DepartureReasonsJUnitTest() {
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

        DepartureReasons u = new DepartureReasons();
        u.setReason_name("Sick leave");

  


        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
        departureReasonsDAO.persist(u);
        departureReasonsid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
        DepartureReasons u = departureReasonsDAO.find(departureReasonsid);
        Assert.assertEquals("Sick leave", u.getReason_name());
    }

    @Test
    public void testUpdate() {
        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
      DepartureReasons u = departureReasonsDAO.find(departureReasonsid);
        u.setReason_name("Resignation");
        departureReasonsDAO.merge(u);
        DepartureReasons p2 = departureReasonsDAO.find(departureReasonsid);
        Assert.assertEquals("Resignation", p2.getReason_name());
    }

    @Test
    public void testCount() {
        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
        Long count = departureReasonsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
        List<DepartureReasons> competencies = departureReasonsDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
        DepartureReasons u = departureReasonsDAO.getByPropertyName("reasonName", "Resignation");
        Assert.assertEquals("Resignation", u.getReason_name());

    }

    @Test
    public void testDelete() {
        departureReasonsDAO = (DepartureReasonsDAO) ctx.getBean("departureReasonsDAO");
        DepartureReasons u = departureReasonsDAO.find(departureReasonsid);
        departureReasonsDAO.remove(u);

    }
}