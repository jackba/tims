/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.repository.jdbc.SnapshotDataDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author boniface
 */
public class SnapshotUnitTest {

    private static Long countyId;
    private SnapshotDataDAO snapshotDataDAO;
    private static ApplicationContext ctx;

    public SnapshotUnitTest() {
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
    public void createNewCounty() {
        //Create County Object You need to replace this with a Creational Design Pattern



        snapshotDataDAO = (SnapshotDataDAO) ctx.getBean("snapshotDataDAO");
        snapshotDataDAO.getNumberOfPeople();
        System.out.println(" THE RESULT "+snapshotDataDAO.getNumberOfPeople());
        
    }


}