/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.DistrictDAO;
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
public class DistrictJUnitTest {

    private static Long districtId;
    private DistrictDAO DistrictDAO;
    private static ApplicationContext ctx;

    public DistrictJUnitTest() {
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
    public void createNewDistrict() {
        //Create District Object You need to replace this with a Creational Design Pattern

        District u = new District();
        u.setDistrictCode("7970");
        u.setDistrictName("CBD");

         Province r = new Province();
        r.setRegionCode("535");

        Country c = new Country();
        c.setAlphaCode("ZA");
        c.setCountryName("South Africa");
        c.setNumericCode(27);
        c.setLocation(true);
        c.setPrimaryCountry(true);

        r.setRegionName("Western Cape");

        //u.setRegions(r);

        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        DistrictDAO.persist(u);
        districtId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        District u = DistrictDAO.find(districtId);
        Assert.assertEquals("CBD", u.getDistrictName());
    }

    @Test
    public void testUpdate() {
        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        District u = DistrictDAO.find(districtId);
        u.setDistrictName("Harbour");
        DistrictDAO.merge(u);
        District p2 = DistrictDAO.find(districtId);
        Assert.assertEquals("Harbour", p2.getDistrictName());
    }

    @Test
    public void testCount() {
        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        Long count = DistrictDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        List<District> districts = DistrictDAO.findAll();
        Assert.assertTrue(districts.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        District u = DistrictDAO.getByPropertyName("districtName", "Harbour");
        Assert.assertEquals("Harbour", u.getDistrictName());

    }

    @Test
    public void testDelete() {
        DistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        District u = DistrictDAO.find(districtId);
        DistrictDAO.remove(u);

    }
}