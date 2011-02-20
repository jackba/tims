/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.repository;

import com.hashthrims.repository.jpa.CountryDAO;
import org.junit.Ignore;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.Province;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.RegionDAO;
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
public class ProvinceJUnitTest {

    private static Long regionId;
    private RegionDAO regionDAO;
    private CountryDAO countryDAO;
    private static ApplicationContext ctx;

    public ProvinceJUnitTest() {
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
    public void createNewRegion() {
        //Create Province Object You need to replace this with a Creational Design Pattern

        Province u = new Province();
        u.setRegionCode("535");

        countryDAO = (CountryDAO) ctx.getBean("countryDAO");
        Country c = countryDAO.find(new Long(21));
        u.setRegionCode("200");
        u.setRegionName("Western Cape");
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        regionDAO.persist(u);
        regionId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        Province u = regionDAO.find(regionId);
        Assert.assertEquals("Western Cape", u.getRegionName());
    }

    @Test
    public void testUpdate() {
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        Province u = regionDAO.find(regionId);
        u.setRegionName("Eastern Cape");
        regionDAO.merge(u);
        Province p2 = regionDAO.find(regionId);
        Assert.assertEquals("Eastern Cape", p2.getRegionName());
    }

    @Test
    public void testCount() {
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        Long count = regionDAO.count();
       // Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        List<Province> regions = regionDAO.findAll();
        Assert.assertTrue(regions.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        Province u = regionDAO.getByPropertyName("regionName", "Eastern Cape");
        Assert.assertEquals("Eastern Cape", u.getRegionName());

    }

    @Ignore
    public void testDelete() {
        regionDAO = (RegionDAO) ctx.getBean("regionDAO");
        Province u = regionDAO.find(regionId);
        regionDAO.remove(u);

    }
}
