/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa.CadresDAO;

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
public class CadresJUnitTest {

    private static Long cadresListid;
    private CadresDAO cadresDAO;
    private static ApplicationContext ctx;

    public CadresJUnitTest() {
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

        Cadres u = new Cadres();
       u.setCadres_name("TP3");
      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        cadresDAO.persist(u);
        cadresListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        Cadres u = cadresDAO.find(cadresListid);
        Assert.assertEquals("TP3", u.getCadres_name());
    }

    @Test
    public void testUpdate() {
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
      Cadres u = cadresDAO.find(cadresListid);
        u.setCadres_name("BA");
        cadresDAO.merge(u);
        Cadres p2 = cadresDAO.find(cadresListid);
        Assert.assertEquals("BA", p2.getCadres_name());
    }

    @Test
    public void testCount() {
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        Long count = cadresDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        List<Cadres> competencies = cadresDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        Cadres u = cadresDAO.getByPropertyName("cadresName", "BA");
        Assert.assertEquals("BA", u.getCadres_name());

    }

    @Test
    public void testDelete() {
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        Cadres u = cadresDAO.find(cadresListid);
        cadresDAO.remove(u);

    }
}