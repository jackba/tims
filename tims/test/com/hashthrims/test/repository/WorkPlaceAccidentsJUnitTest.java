package com.hashthrims.test.repository;

import com.hashthrims.repository.jpa.WorkPlaceAccidentsDAO;
import com.hashthrims.domain.WorkPlaceAccidents;
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
 public class WorkPlaceAccidentsJUnitTest {

    private static Long usersId;
    private WorkPlaceAccidentsDAO WorkPlaceAccidentsDAO;
    private static ApplicationContext ctx;

    public WorkPlaceAccidentsJUnitTest() {
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
    public void createNewWorkPlaceAccidents() {
        //Create WorkPlaceAccidents Object You need to replace this with a Creational Design Pattern

        WorkPlaceAccidents u = new WorkPlaceAccidents();
        u.setPeopleInvioved("shu");

        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        WorkPlaceAccidentsDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        WorkPlaceAccidents u = WorkPlaceAccidentsDAO.find(usersId);
        Assert.assertEquals("shu", u.getPeopleInvioved());
    }

    @Test
    public void testUpdate() {
        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        WorkPlaceAccidents u = WorkPlaceAccidentsDAO.find(usersId);
        u.setPeopleInvioved("update");
        WorkPlaceAccidentsDAO.merge(u);
        WorkPlaceAccidents p2 = WorkPlaceAccidentsDAO.find(usersId);
        Assert.assertEquals("update", p2.getPeopleInvioved());
    }

    @Test
    public void testCount() {
        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        Long count = WorkPlaceAccidentsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        List<WorkPlaceAccidents> universities = WorkPlaceAccidentsDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        WorkPlaceAccidents u = WorkPlaceAccidentsDAO.getByPropertyName("peopleInvioved", "update");
        Assert.assertEquals("update", u.getPeopleInvioved());

    }

    @Test
    public void testDelete() {
        WorkPlaceAccidentsDAO = (WorkPlaceAccidentsDAO) ctx.getBean("workplaceaccidentsDAO");
        WorkPlaceAccidents u = WorkPlaceAccidentsDAO.find(usersId);
        WorkPlaceAccidentsDAO.remove(u);

    }
}