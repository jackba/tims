/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.Province;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.CountryDAO;
import java.util.ArrayList;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author boniface
 */
public class CountryJUnitTest {

    private static Long countryId;
    private CountryDAO CountryDAO;
    private static ApplicationContext ctx;

    public CountryJUnitTest() {
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
    public void createNewCountry() {
        //Create Country Object You need to replace this with a Creational Design Pattern

        List<Province>  provinces = new ArrayList<Province>();

        Province w = new Province();
        w.setRegionCode("4");
        w.setRegionName("COPPERBELT");
       // w.set

        Province p = new Province();
        p.setRegionCode("8");
        p.setRegionName("LUSAKA");

        provinces.add(p);
        provinces.add(w);


        Country u = new Country();
        u.setAlphaCode("ZA");
        u.setCountryName("South Africa");
        u.setLocation(true);
        u.setNumericCode(28);
        u.setPrimaryCountry(true);
        u.setProvince(provinces);

        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        CountryDAO.persist(u);
        countryId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        Country u = CountryDAO.find(countryId);
        Assert.assertEquals("ZA", u.getAlphaCode());
    }

    @Test
    public void testUpdate() {
        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        Country u = CountryDAO.find(countryId);
        u.setCountryName("Zimbbabwe");
        CountryDAO.merge(u);
        Country p2 = CountryDAO.find(countryId);
        Assert.assertEquals("Zimbbabwe", p2.getCountryName());
    }

    @Ignore
    public void testCount() {
        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        Long count = CountryDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        List<Country> universities = CountryDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        Country u = CountryDAO.getByPropertyName("countryName", "Zimbbabwe");
        Assert.assertEquals("Zimbbabwe", u.getCountryName());

    }

    @Ignore
    public void testDelete() {
        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        Country u = CountryDAO.find(countryId);
        CountryDAO.remove(u);

    }
}