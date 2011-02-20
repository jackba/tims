/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.positions.PositionTypes;
import java.util.List;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.PositionTypesDAO;
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
public class PositionTypesJUnitTest {

    private static Long positionTypesId;
    private PositionTypesDAO PositionTypesDAO;
    private static ApplicationContext ctx;

    public PositionTypesJUnitTest() {
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
    public void createNewPositionTypes() {
        //Create PositionTypes Object You need to replace this with a Creational Design Pattern

        PositionTypes u = new PositionTypes();
        u.setPosTypeName("Analyst");

        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        PositionTypesDAO.persist(u);
        positionTypesId = u.getId();
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testRead() {
        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        PositionTypes u = PositionTypesDAO.find(positionTypesId);
        Assert.assertEquals("Analyst", u.getPosTypeName());
    }

    @Test
    public void testUpdate() {
        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        PositionTypes u = PositionTypesDAO.find(positionTypesId);
        u.setPosTypeName("Programmer");
        PositionTypesDAO.merge(u);
        PositionTypes p2 = PositionTypesDAO.find(positionTypesId);
        Assert.assertEquals("Programmer", p2.getPosTypeName());
    }

    @Test
    public void testCount() {
        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        Long count = PositionTypesDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        List<PositionTypes> universities = PositionTypesDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        PositionTypes u = PositionTypesDAO.getByPropertyName("posTypeName", "Programmer");
        Assert.assertEquals("Programmer", u.getPosTypeName());

    }

    @Test
    public void testDelete() {
        PositionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        PositionTypes u = PositionTypesDAO.find(positionTypesId);
        PositionTypesDAO.remove(u);

    }
}