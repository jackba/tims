package com.hashthrims.test.repository;

import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.repository.jpa.CompetencyDAO;
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
 public class CompetencyJUnitTest {

    private static Long usersId;
    private CompetencyDAO CompetencyDAO;
    private static ApplicationContext ctx;

    public CompetencyJUnitTest() {
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
    public void createNewCompetency() {
        //Create EmployeeCourses Object You need to replace this with a Creational Design Pattern

        EmployeeCourses u = new EmployeeCourses();
        u.setCompetencyName("com");

        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        CompetencyDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        EmployeeCourses u = CompetencyDAO.find(usersId);
        Assert.assertEquals("com", u.getCompetencyName());
    }

    @Test
    public void testUpdate() {
        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        EmployeeCourses u = CompetencyDAO.find(usersId);
        u.setCompetencyName("upd8");
        CompetencyDAO.merge(u);
        EmployeeCourses p2 = CompetencyDAO.find(usersId);
        Assert.assertEquals("upd8", p2.getCompetencyName());
    }

    @Test
    public void testCount() {
        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        Long count = CompetencyDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        List<EmployeeCourses> universities = CompetencyDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        EmployeeCourses u = CompetencyDAO.getByPropertyName("competencyName", "upd8");
        Assert.assertEquals("upd8", u.getCompetencyName());

    }

    @Test
    public void testDelete() {
        CompetencyDAO = (CompetencyDAO) ctx.getBean("competencyDAO");
        EmployeeCourses u = CompetencyDAO.find(usersId);
        CompetencyDAO.remove(u);

    }
}