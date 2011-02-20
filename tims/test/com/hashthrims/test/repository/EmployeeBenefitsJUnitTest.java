package com.hashthrims.test.repository;

import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.EmployeeBenefits;
import com.hashthrims.repository.jpa.EmployeeBenefitsDAO;
import java.math.BigDecimal;
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
 * @author Te$tGen
 */
 public class EmployeeBenefitsJUnitTest {

    private static Long usersId;
    private EmployeeBenefitsDAO EmployeeBenefitsDAO;
    private static ApplicationContext ctx;
    private SalaryGrade s;

    public EmployeeBenefitsJUnitTest() {
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
    public void createNewEmployeeBenefits() {
        //Create EmployeeBenefits Object You need to replace this with a Creational Design Pattern

        EmployeeBenefits u = new EmployeeBenefits();
        s = new SalaryGrade();
        s.setEndAmount(BigDecimal.ONE);
        u.setAmount(s);

        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        EmployeeBenefitsDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        EmployeeBenefits u = EmployeeBenefitsDAO.find(usersId);
        Assert.assertEquals(BigDecimal.ONE, u.getAmount());
    }

    @Test
    public void testUpdate() {
        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        EmployeeBenefits u = EmployeeBenefitsDAO.find(usersId);
        s.setCurrentAmount(BigDecimal.ZERO);
        u.setAmount(s);
        EmployeeBenefitsDAO.merge(u);
        EmployeeBenefits p2 = EmployeeBenefitsDAO.find(usersId);
        Assert.assertEquals(u.getAmount(), p2.getAmount());
    }

    @Test
    public void testCount() {
        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        Long count = EmployeeBenefitsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        List<EmployeeBenefits> universities = EmployeeBenefitsDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        EmployeeBenefits u = EmployeeBenefitsDAO.getByPropertyName("amount", s.toString());
        Assert.assertEquals(s.toString(), u.getAmount());

    }

    @Test
    public void testDelete() {
        EmployeeBenefitsDAO = (EmployeeBenefitsDAO) ctx.getBean("employeebenefitsDAO");
        EmployeeBenefits u = EmployeeBenefitsDAO.find(usersId);
        EmployeeBenefitsDAO.remove(u);

    }
}