/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.repository.jpa.FacilityDAO;

import java.util.List;
import org.junit.Assert;

import org.springframework.context.ApplicationContext;
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
public class FacilityJUnitTest {

    private static Long facilityListid;
    private FacilityDAO facilityDAO;
    private static ApplicationContext ctx;

    public FacilityJUnitTest() {
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

        Facility u = new Facility();
        u.setFacilityName("I.T");
        //u.setContactInfo("07455889123 garronappl@yahoo.com");


        FacilityType ft = new FacilityType();
        ft.setFacilityName("Informatics and design");
        //ft.setFacility(u);
        
        Country c = new Country();
        c.setAlphaCode("6798");
        c.setCountryName("Nigeria");
        c.setNumericCode(45);
        c.setPrimaryCountry(true);
        c.setLocation(true);
//        u.setCountry(c);

        Contacts cntcs = new Contacts();
        cntcs.setAddressType("Normal");
        cntcs.setCellnumber("0827131895");
        cntcs.setEmail("user@gmail.com");
        //cntcs.setFacility(u);
        cntcs.setFaxnumber("02185902");
        cntcs.setMailingAddress("www.hr@hotmail.com");
        cntcs.setNotes("Active");
        cntcs.setTelephoneNumber("0218594301");


        EducationType e = new EducationType();
        e.setEduc_type_name("further education");



        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        facilityDAO.persist(u);
        facilityListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        Facility u = facilityDAO.find(facilityListid);
        Assert.assertEquals("I.T", u.getFacilityName());
    }

    @Test
    public void testUpdate() {
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
      Facility u = facilityDAO.find(facilityListid);
        u.setFacilityName("Science");
        facilityDAO.merge(u);
        Facility p2 = facilityDAO.find(facilityListid);
        Assert.assertEquals("Science", p2.getFacilityName());
    }

    @Test
    public void testCount() {
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        Long count = facilityDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        List<Facility> competencies = facilityDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        Facility u = facilityDAO.getByPropertyName("facilityName", "Science");
        Assert.assertEquals("Science", u.getFacilityName());

    }

    @Test
    public void testDelete() {
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        Facility u = facilityDAO.find(facilityListid);
        facilityDAO.remove(u);

    }
}