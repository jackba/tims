/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.ContiuingEducationCourseDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stud
 */
public class ContiuingEducationCourseJUnitTest {

    private static Long contiuingEducationCourseId;
    private ContiuingEducationCourseDAO ContiuingEducationCourseDAO;
    private static ApplicationContext ctx;

    public ContiuingEducationCourseJUnitTest() {
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
    public void createNewContiuingEducationCourse() {
        //Create ContiuingEducationCourse Object You need to replace this with a Creational Design Pattern

        ContiuingEducationCourse c = new ContiuingEducationCourse();
        c.setNameOfContinueCourse("MsOffice2007");
        c.setCreditHours("8");

        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        ContiuingEducationCourseDAO.persist(c);
        contiuingEducationCourseId = c.getId();
        Assert.assertNotNull(c.getId());
    }

    @Test
    public void testRead() {
        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        ContiuingEducationCourse c = ContiuingEducationCourseDAO.find(contiuingEducationCourseId);
        Assert.assertEquals("MsOffice2007", c.getNameOfContinueCourse());
    }

    @Test
    public void testUpdate() {
        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        ContiuingEducationCourse c = ContiuingEducationCourseDAO.find(contiuingEducationCourseId);
        c.setNameOfContinueCourse("MsOffice 2010");
        ContiuingEducationCourseDAO.merge(c);
        ContiuingEducationCourse p2 = ContiuingEducationCourseDAO.find(contiuingEducationCourseId);
        Assert.assertEquals("MsOffice 2010", p2.getNameOfContinueCourse());
    }

    @Test
    public void testCount() {
        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        Long count = ContiuingEducationCourseDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        List<ContiuingEducationCourse> universities = ContiuingEducationCourseDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        ContiuingEducationCourse c = ContiuingEducationCourseDAO.getByPropertyName("nameOfContinueCourse", "MsOffice 2010");
        Assert.assertEquals("MsOffice 2010", c.getNameOfContinueCourse());

    }

    @Test
    public void testDelete() {
        ContiuingEducationCourseDAO = (ContiuingEducationCourseDAO) ctx.getBean("contiuingEducationCourseDAO");
        ContiuingEducationCourse c = ContiuingEducationCourseDAO.find(contiuingEducationCourseId);
        ContiuingEducationCourseDAO.remove(c);

    }
}