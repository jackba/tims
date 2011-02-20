package com.hashthrims.test.repository;

import com.hashthrims.domain.Contacts;
import com.hashthrims.repository.jpa.ContactsDAO;
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
 public class ContactsJUnitTest {

    private static Long usersId;
    private ContactsDAO ContactsDAO;
    private static ApplicationContext ctx;

    public ContactsJUnitTest() {
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
    public void createNewContacts() {
        //Create Contacts Object You need to replace this with a Creational Design Pattern

        Contacts u = new Contacts();
        u.setEmail("ismail@kenai.com");

        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        ContactsDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        Contacts u = ContactsDAO.find(usersId);
        Assert.assertEquals("ismail@kenai.com", u.getEmail());
    }

    @Test
    public void testUpdate() {
        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        Contacts u = ContactsDAO.find(usersId);
        u.setEmail("update@kenai.c");
        ContactsDAO.merge(u);
        Contacts p2 = ContactsDAO.find(usersId);
        Assert.assertEquals("update@kenai.c", p2.getEmail());
    }

    @Test
    public void testCount() {
        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        Long count = ContactsDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        List<Contacts> universities = ContactsDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        Contacts u = ContactsDAO.getByPropertyName("email", "update@kenai.c");
        Assert.assertEquals("update@kenai.c", u.getEmail());

    }

    @Test
    public void testDelete() {
        ContactsDAO = (ContactsDAO) ctx.getBean("contactsDAO");
        Contacts u = ContactsDAO.find(usersId);
        ContactsDAO.remove(u);

    }
}