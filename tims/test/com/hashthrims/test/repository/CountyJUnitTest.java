/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.regionlist.County;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.CountyDAO;
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
public class CountyJUnitTest {

    private static Long countyId;
    private CountyDAO CountyDAO;
    private static ApplicationContext ctx;

    public CountyJUnitTest() {
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
    public void createNewCounty() {
        //Create County Object You need to replace this with a Creational Design Pattern

        County u = new County();
        u.setCountyName("Bellville");


        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        CountyDAO.persist(u);
        countyId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        County u = CountyDAO.find(countyId);
        Assert.assertEquals("Bellville", u.getCountyName());
    }

    @Test
    public void testUpdate() {
        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        County u = CountyDAO.find(countyId);
        u.setCountyName("Durbanville");
        CountyDAO.merge(u);
        County p2 = CountyDAO.find(countyId);
        Assert.assertEquals("Durbanville", p2.getCountyName());
    }

    @Test
    public void testCount() {
        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        Long count = CountyDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        List<County> universities = CountyDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        County u = CountyDAO.getByPropertyName("countyName", "Durbanville");
        Assert.assertEquals("Durbanville", u.getCountyName());

    }

    @Test
    public void testDelete() {
        CountyDAO = (CountyDAO) ctx.getBean("countyDAO");
        County u = CountyDAO.find(countyId);
        CountyDAO.remove(u);

    }
}