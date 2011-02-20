/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.repository.jpa.DepartmentDAO;

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
public class DepartmentJUnitTest {

    private static Long departmentListid;
    private DepartmentDAO departmentDAO;
    private static ApplicationContext ctx;

    public DepartmentJUnitTest() {
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

        Department u = new Department();
        u.setDeptName("I.T");
      EducationType e = new EducationType();
      e.setEduc_type_name("further education");



        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        departmentDAO.persist(u);
        departmentListid = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        Department u = departmentDAO.find(departmentListid);
        Assert.assertEquals("I.T", u.getDeptName());
    }

    @Test
    public void testUpdate() {
        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
      Department u = departmentDAO.find(departmentListid);
        u.setDeptName("Business");
        departmentDAO.merge(u);
        Department p2 = departmentDAO.find(departmentListid);
        Assert.assertEquals("Business", p2.getDeptName());
    }

    @Test
    public void testCount() {
        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        Long count = departmentDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        List<Department> competencies = departmentDAO.findAll();
        Assert.assertTrue(competencies.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        Department u = departmentDAO.getByPropertyName("deptName", "Business");
        Assert.assertEquals("Business", u.getDeptName());

    }

    @Test
    public void testDelete() {
        departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        Department u = departmentDAO.find(departmentListid);
        departmentDAO.remove(u);

    }
}