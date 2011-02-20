package com.hashthrims.test.repository;

import com.hashthrims.repository.jpa.ProfessionalRegistrationDAO;
import com.hashthrims.domain.ProfessionalRegistration;
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
 public class ProfessionalRegistrationJUnitTest {

    private static Long usersId;
    private ProfessionalRegistrationDAO ProfessionalRegistrationDAO;
    private static ApplicationContext ctx;

    public ProfessionalRegistrationJUnitTest() {
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
    public void createNewProfessionalRegistration() {
        //Create ProfessionalRegistration Object You need to replace this with a Creational Design Pattern

        ProfessionalRegistration u = new ProfessionalRegistration();
        u.setLicenceNumber("123456");

        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        ProfessionalRegistrationDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        ProfessionalRegistration u = ProfessionalRegistrationDAO.find(usersId);
        Assert.assertEquals("123456", u.getLicenceNumber());
    }

    @Test
    public void testUpdate() {
        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        ProfessionalRegistration u = ProfessionalRegistrationDAO.find(usersId);
        u.setLicenceNumber("123458");
        ProfessionalRegistrationDAO.merge(u);
        ProfessionalRegistration p2 = ProfessionalRegistrationDAO.find(usersId);
        Assert.assertEquals("123458", p2.getLicenceNumber());
    }

    @Test
    public void testCount() {
        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        Long count = ProfessionalRegistrationDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        List<ProfessionalRegistration> universities = ProfessionalRegistrationDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        ProfessionalRegistration u = ProfessionalRegistrationDAO.getByPropertyName("licenceNumber", "123458");
        Assert.assertEquals("123458", u.getLicenceNumber());

    }

    @Test
    public void testDelete() {
        ProfessionalRegistrationDAO = (ProfessionalRegistrationDAO) ctx.getBean("professionalregistrationDAO");
        ProfessionalRegistration u = ProfessionalRegistrationDAO.find(usersId);
        ProfessionalRegistrationDAO.remove(u);

    }
}