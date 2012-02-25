/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.Users;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.UsersDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author boniface
 */
public class UsersJUnitTest {

    private static Long usersId;
    private UsersDAO UsersDAO;
    private static ApplicationContext ctx;

    public UsersJUnitTest() {
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
    public void createNewUsers() {
        //Create Users Object You need to replace this with a Creational Design Pattern

        Users u = new Users();
       
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        UsersDAO.persist(u);
        usersId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        Users u = UsersDAO.find(usersId);
        Assert.assertNotNull(u);
       
    }

    @Test
    public void testUpdate() {
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        Users u = UsersDAO.find(usersId);
       
        UsersDAO.merge(u);
        Users p2 = UsersDAO.find(usersId);
       
    }

    @Test
    public void testCount() {
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        Long count = UsersDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        List<Users> universities = UsersDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        Users u = UsersDAO.getByPropertyName("username", "UCT");
       

    }

    @Test
    public void testDelete() {
        UsersDAO = (UsersDAO) ctx.getBean("usersDAO");
        Users u = UsersDAO.find(usersId);
        UsersDAO.remove(u);

    }
}