/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import java.util.ArrayList;
import com.hashthrims.domain.Contacts;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import org.junit.Assert;
import com.hashthrims.repository.jpa.TrainingFunderDAO;
import com.hashthrims.domain.traininglist.TrainingFunder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author staff
 */
public class TrainingFunderJUnitTest {

    private static Long trainingFunderId;
    private TrainingFunderDAO TrainingFunderDAO;
    private static ApplicationContext ctx;

    public TrainingFunderJUnitTest() {
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
    public void createNewTrainingFunder() {
        //Create TrainingFunder Object You need to replace this with a Creational Design Pattern

        TrainingFunder t = new TrainingFunder();
       

        Contacts contact = new Contacts();
        contact.setAddressType("Residential");
        contact.setCellnumber("076 294 6746");
        contact.setEmail("pasqualliee@cput.ac.za");
        contact.setFaxnumber("021 959 6908");
        contact.setMailingAddress("CPUT PO BOX 959");
        contact.setNotes("Started");
        contact.setTelephoneNumber("021 959 6907");

        List<Contacts> contactInfo = new ArrayList<Contacts>();
        contactInfo.add(contact);
      



        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        TrainingFunderDAO.persist(t);
        trainingFunderId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        TrainingFunder t = TrainingFunderDAO.find(trainingFunderId);
        
    }

    @Test
    public void testUpdate() {
        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        TrainingFunder t = TrainingFunderDAO.find(trainingFunderId);
        
        TrainingFunderDAO.merge(t);
        TrainingFunder p2 = TrainingFunderDAO.find(trainingFunderId);
       
    }

    @Test
    public void testCount() {
        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        Long count = TrainingFunderDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        List<TrainingFunder> universities = TrainingFunderDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        TrainingFunder t = TrainingFunderDAO.getByPropertyName("nameTrainer", "Richard");
       
    }

    @Test
    public void testDelete() {
        TrainingFunderDAO = (TrainingFunderDAO) ctx.getBean("trainingFunderDAO");
        TrainingFunder t = TrainingFunderDAO.find(trainingFunderId);
        TrainingFunderDAO.remove(t);

    }

}