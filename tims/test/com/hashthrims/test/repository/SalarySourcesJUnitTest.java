/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.positions.SalarySources;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.SalarySourcesDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author boniface
 */
public class SalarySourcesJUnitTest {

    private static Long salarySourcesId;
    private SalarySourcesDAO SalarySourcesDAO;
    private static ApplicationContext ctx;

    public SalarySourcesJUnitTest() {
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
    public void createNewSalarySources() {
        //Create SalarySources Object You need to replace this with a Creational Design Pattern

        SalarySources u = new SalarySources();
        u.setSalSourceName("boniface");

        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        SalarySourcesDAO.persist(u);
        salarySourcesId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        SalarySources u = SalarySourcesDAO.find(salarySourcesId);
        Assert.assertEquals("boniface", u.getSalSourceName());
    }

    @Test
    public void testUpdate() {
        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        SalarySources u = SalarySourcesDAO.find(salarySourcesId);
        u.setSalSourceName("Mongezi");
        SalarySourcesDAO.merge(u);
        SalarySources p2 = SalarySourcesDAO.find(salarySourcesId);
        Assert.assertEquals("Mongezi", p2.getSalSourceName());
    }

    @Test
    public void testCount() {
        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        Long count = SalarySourcesDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        List<SalarySources> universities = SalarySourcesDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        SalarySources u = SalarySourcesDAO.getByPropertyName("salSourceName", "Mongezi");
        Assert.assertEquals("Mongezi", u.getSalSourceName());

    }

    @Test
    public void testDelete() {
        SalarySourcesDAO = (SalarySourcesDAO) ctx.getBean("salarySourcesDAO");
        SalarySources u = SalarySourcesDAO.find(salarySourcesId);
        SalarySourcesDAO.remove(u);

    }
}