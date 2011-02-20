/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.Demography;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.DemographyDAO;
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
public class DemographyJUnitTest {

    private static Long demographyId;
    private DemographyDAO DemographyDAO;
    private static ApplicationContext ctx;

    public DemographyJUnitTest() {
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
    public void createNewDemography() {
        //Create Demography Object You need to replace this with a Creational Design Pattern

        Demography u = new Demography();
        u.setDependants(5);
        u.setGender("Female");
        u.setMaritalStatus("Single");


        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        DemographyDAO.persist(u);
        demographyId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        Demography u = DemographyDAO.find(demographyId);
        Assert.assertEquals("Single", u.getMaritalStatus());
    }

    @Test
    public void testUpdate() {
        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        Demography u = DemographyDAO.find(demographyId);
        u.setMaritalStatus("Married");
        DemographyDAO.merge(u);
        Demography p2 = DemographyDAO.find(demographyId);
        Assert.assertEquals("Married", p2.getMaritalStatus());
    }

    @Test
    public void testCount() {
        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        Long count = DemographyDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        List<Demography> universities = DemographyDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        Demography u = DemographyDAO.getByPropertyName("maritalStatus", "Married");
        Assert.assertEquals("Married", u.getMaritalStatus());

    }

    @Test
    public void testDelete() {
        DemographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        Demography u = DemographyDAO.find(demographyId);
        DemographyDAO.remove(u);

    }
}