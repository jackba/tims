/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.domain.employeelist. EducationType;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa. EducationTypeDAO;

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
public class EducationTypeJUnitTest {

    private static Long  educationTypeid;
    private  EducationTypeDAO  educationTypeDAO;
    private static ApplicationContext ctx;

    public EducationTypeJUnitTest() {
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

         EducationType u = new  EducationType();
        u.setEduc_type_name("Further education");

         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
         educationTypeDAO.persist(u);
         educationTypeid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
         EducationType u =  educationTypeDAO.find( educationTypeid);
        Assert.assertEquals("Further education", u.getEduc_type_name());
    }

    @Test
    public void testUpdate() {
         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
       EducationType u =  educationTypeDAO.find( educationTypeid);
        u.setEduc_type_name("Higher education");
         educationTypeDAO.merge(u);
         EducationType p2 =  educationTypeDAO.find( educationTypeid);
        Assert.assertEquals("Higher education", p2.getEduc_type_name());
    }

    @Test
    public void testCount() {
         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
        Long count =  educationTypeDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
        List< EducationType> competencies =  educationTypeDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
         EducationType u =  educationTypeDAO.getByPropertyName("educTypeName","Higher education");
        Assert.assertEquals("Higher education", u.getEduc_type_name());

    }

    @Test
    public void testDelete() {
         educationTypeDAO = ( EducationTypeDAO) ctx.getBean("educationTypeDAO");
         EducationType u =  educationTypeDAO.find( educationTypeid);
         educationTypeDAO.remove(u);

    }
}