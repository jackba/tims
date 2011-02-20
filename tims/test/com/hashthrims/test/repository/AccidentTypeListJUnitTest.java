/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;

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
public class AccidentTypeListJUnitTest {

    private static Long accidentTypeListid;
    private AccidentTypeListDAO accidentTypeListDAO;
    private static ApplicationContext ctx;

    public AccidentTypeListJUnitTest() {
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

        AccidentTypeList u = new AccidentTypeList();
        u.setAccidentName("Car accident");

        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
        accidentTypeListDAO.persist(u);
        accidentTypeListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
        AccidentTypeList u = accidentTypeListDAO.find(accidentTypeListid);
        Assert.assertEquals("Car accident", u.getAccidentName());
    }

    @Test
    public void testUpdate() {
        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
      AccidentTypeList u = accidentTypeListDAO.find(accidentTypeListid);
        u.setAccidentName("Flooding");
        accidentTypeListDAO.merge(u);
        AccidentTypeList p2 = accidentTypeListDAO.find(accidentTypeListid);
        Assert.assertEquals("Flooding", p2.getAccidentName());
    }

    @Test
    public void testCount() {
        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
        Long count = accidentTypeListDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
        List<AccidentTypeList> universities = accidentTypeListDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
        AccidentTypeList u = accidentTypeListDAO.getByPropertyName("accidentName", "Flooding");
        Assert.assertEquals("Flooding", u.getAccidentName());

    }

    @Test
    public void testDelete() {
        accidentTypeListDAO = (AccidentTypeListDAO) ctx.getBean("accidentTypeListDAO");
        AccidentTypeList u = accidentTypeListDAO.find(accidentTypeListid);
        accidentTypeListDAO.remove(u);

    }
}