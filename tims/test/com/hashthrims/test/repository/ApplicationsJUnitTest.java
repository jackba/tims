package com.hashthrims.test.repository;

import com.hashthrims.repository.jpa.ApplicationsDAO;
import com.hashthrims.domain.Applications;
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
 public class ApplicationsJUnitTest {

    private static Long usersId;
    private ApplicationsDAO ApplicationsDAO;
    private static ApplicationContext ctx;

    public ApplicationsJUnitTest() {
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
    public void createNewApplications() {
        //Create Applications Object You need to replace this with a Creational Design Pattern

        Applications u = new Applications();
        u.setFelony("dnd");

        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        ApplicationsDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        Applications u = ApplicationsDAO.find(usersId);
        Assert.assertEquals("dnd", u.getFelony());
    }

    @Test
    public void testUpdate() {
        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        Applications u = ApplicationsDAO.find(usersId);
        u.setFelony("sdtg");
        ApplicationsDAO.merge(u);
        Applications p2 = ApplicationsDAO.find(usersId);
        Assert.assertEquals("sdtg", p2.getFelony());
    }

    @Test
    public void testCount() {
        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        Long count = ApplicationsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        List<Applications> universities = ApplicationsDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        Applications u = ApplicationsDAO.getByPropertyName("felony", "sdtg");
        Assert.assertEquals("sdtg", u.getFelony());

    }

    @Test
    public void testDelete() {
        ApplicationsDAO = (ApplicationsDAO) ctx.getBean("applicationsDAO");
        Applications u = ApplicationsDAO.find(usersId);
        ApplicationsDAO.remove(u);

    }
}