/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.repository.jpa.FacilityTypeDAO;

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
public class FacilityTypeJUnitTest {

    private static Long facilityTypeListid;
    private FacilityTypeDAO facilityTypeDAO;
    private static ApplicationContext ctx;

    public FacilityTypeJUnitTest() {
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

        FacilityType u = new FacilityType();
        u.setFacilityName("Engineering");

        Facility f = new Facility();
        //f.setContactInfo("0745589485");
        f.setFacilityName("I.T");
       // f.setContactInfo("07455889123 garronappl@yahoo.com");

        EducationType e = new EducationType();
        e.setEduc_type_name("further education");



        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        facilityTypeDAO.persist(u);
        facilityTypeListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        FacilityType u = facilityTypeDAO.find(facilityTypeListid);
        Assert.assertEquals("Engineering", u.getFacilityName());
    }

    @Test
    public void testUpdate() {
        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
      FacilityType u = facilityTypeDAO.find(facilityTypeListid);
        u.setFacilityName("Education");
        facilityTypeDAO.merge(u);
        FacilityType p2 = facilityTypeDAO.find(facilityTypeListid);
        Assert.assertEquals("Education", p2.getFacilityName());
    }

    @Test
    public void testCount() {
        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        Long count = facilityTypeDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        List<FacilityType> competencies = facilityTypeDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        FacilityType u = facilityTypeDAO.getByPropertyName("facilityName", "Education");
        Assert.assertEquals("Education", u.getFacilityName());

    }

    @Test
    public void testDelete() {
        facilityTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        FacilityType u = facilityTypeDAO.find(facilityTypeListid);
        facilityTypeDAO.remove(u);

    }
}