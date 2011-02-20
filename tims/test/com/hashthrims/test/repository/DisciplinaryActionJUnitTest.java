package com.hashthrims.test.repository;

import com.hashthrims.repository.jpa.DisciplinaryActionDAO;
import com.hashthrims.domain.DisciplinaryAction;
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
 public class DisciplinaryActionJUnitTest {

    private static Long usersId;
    private DisciplinaryActionDAO DisciplinaryActionDAO;
    private static ApplicationContext ctx;

    public DisciplinaryActionJUnitTest() {
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
    public void createNewDisciplinaryAction() {
        //Create DisciplinaryAction Object You need to replace this with a Creational Design Pattern

        DisciplinaryAction u = new DisciplinaryAction();
        u.setNotes("asdrf");

        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        DisciplinaryActionDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        DisciplinaryAction u = DisciplinaryActionDAO.find(usersId);
        Assert.assertEquals("asdrf", u.getNotes());
    }

    @Test
    public void testUpdate() {
        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        DisciplinaryAction u = DisciplinaryActionDAO.find(usersId);
        u.setNotes("upd8");
        DisciplinaryActionDAO.merge(u);
        DisciplinaryAction p2 = DisciplinaryActionDAO.find(usersId);
        Assert.assertEquals("upd8", p2.getNotes());
    }

    @Test
    public void testCount() {
        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        Long count = DisciplinaryActionDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        List<DisciplinaryAction> universities = DisciplinaryActionDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        DisciplinaryAction u = DisciplinaryActionDAO.getByPropertyName("notes", "upd8");
        Assert.assertEquals("upd8", u.getNotes());

    }

    @Test
    public void testDelete() {
        DisciplinaryActionDAO = (DisciplinaryActionDAO) ctx.getBean("disciplinaryactionDAO");
        DisciplinaryAction u = DisciplinaryActionDAO.find(usersId);
        DisciplinaryActionDAO.remove(u);

    }
}