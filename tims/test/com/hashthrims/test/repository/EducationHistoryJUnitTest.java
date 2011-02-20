package com.hashthrims.test.repository;

import com.hashthrims.repository.jpa.EducationHistoryDAO;
import com.hashthrims.domain.EducationHistory;
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
 public class EducationHistoryJUnitTest {

    private static Long usersId;
    private EducationHistoryDAO EducationHistoryDAO;
    private static ApplicationContext ctx;

    public EducationHistoryJUnitTest() {
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
    public void createNewEducationHistory() {
        //Create EducationHistory Object You need to replace this with a Creational Design Pattern

        EducationHistory u = new EducationHistory();
        u.setInstituteNamwe("shu");

        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        EducationHistoryDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        EducationHistory u = EducationHistoryDAO.find(usersId);
        Assert.assertEquals("shu", u.getInstituteNamwe());
    }

    @Test
    public void testUpdate() {
        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        EducationHistory u = EducationHistoryDAO.find(usersId);
        u.setInstituteNamwe("update");
        EducationHistoryDAO.merge(u);
        EducationHistory p2 = EducationHistoryDAO.find(usersId);
        Assert.assertEquals("update", p2.getInstituteNamwe());
    }

    @Test
    public void testCount() {
        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        Long count = EducationHistoryDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        List<EducationHistory> universities = EducationHistoryDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        EducationHistory u = EducationHistoryDAO.getByPropertyName("instituteNamwe", "update");
        Assert.assertEquals("update", u.getInstituteNamwe());

    }

    @Test
    public void testDelete() {
        EducationHistoryDAO = (EducationHistoryDAO) ctx.getBean("educationhistoryDAO");
        EducationHistory u = EducationHistoryDAO.find(usersId);
        EducationHistoryDAO.remove(u);

    }
}