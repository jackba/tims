package com.hashthrims.test.repository;

import com.hashthrims.domain.Education;
import com.hashthrims.repository.jpa.EducationDAO;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Te$tGen
 */
 public class EducationJUnitTest {

    private static Long usersId;
    private EducationDAO EducationDAO;
    private static ApplicationContext ctx;

    public EducationJUnitTest() {
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
    public void createNewEducation() {
        //Create Education Object You need to replace this with a Creational Design Pattern

        Education u = new Education();
        u.setInstitution("cput");


        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        EducationDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        Education u = EducationDAO.find(usersId);
        Assert.assertEquals("cput", u.getInstitution());
    }

    @Test
    public void testUpdate() {
        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        Education u = EducationDAO.find(usersId);
        u.setInstitution("jk");
        EducationDAO.merge(u);
        Education p2 = EducationDAO.find(usersId);
        Assert.assertEquals("jk", p2.getInstitution());
    }

    @Test
    public void testCount() {
        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        Long count = EducationDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        List<Education> universities = EducationDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        Education u = EducationDAO.getByPropertyName("institution", "jk");
        Assert.assertEquals("jk", u.getInstitution());

    }

    @Test
    public void testDelete() {
        EducationDAO = (EducationDAO) ctx.getBean("educationDAO");
        Education u = EducationDAO.find(usersId);
        EducationDAO.remove(u);

    }
}