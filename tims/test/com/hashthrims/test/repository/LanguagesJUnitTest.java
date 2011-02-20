package com.hashthrims.test.repository;

import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.repository.jpa.EmployeeLanguagesDAO;
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
 public class LanguagesJUnitTest {

    private static Long usersId;
    private EmployeeLanguagesDAO LanguagesDAO;
    private static ApplicationContext ctx;

    public LanguagesJUnitTest() {
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
    public void createNewLanguages() {
        //Create EmployeeLanguages Object You need to replace this with a Creational Design Pattern

        EmployeeLanguages u = new EmployeeLanguages();
        u.setWriting("tets");

        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        LanguagesDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        EmployeeLanguages u = LanguagesDAO.find(usersId);
        Assert.assertEquals("tets", u.getWriting());
    }

    @Test
    public void testUpdate() {
        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        EmployeeLanguages u = LanguagesDAO.find(usersId);
        u.setWriting("up");
        LanguagesDAO.merge(u);
        EmployeeLanguages p2 = LanguagesDAO.find(usersId);
        Assert.assertEquals("up", p2.getWriting());
    }

    @Test
    public void testCount() {
        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        Long count = LanguagesDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        List<EmployeeLanguages> universities = LanguagesDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        EmployeeLanguages u = LanguagesDAO.getByPropertyName("writing", "up");
        Assert.assertEquals("up", u.getWriting());

    }

    @Test
    public void testDelete() {
        LanguagesDAO = (EmployeeLanguagesDAO) ctx.getBean("languagesDAO");
        EmployeeLanguages u = LanguagesDAO.find(usersId);
        LanguagesDAO.remove(u);

    }
}