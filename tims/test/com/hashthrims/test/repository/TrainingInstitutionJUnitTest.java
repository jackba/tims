/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.Contacts;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.TrainingInstitutionDAO;
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
public class TrainingInstitutionJUnitTest {

    private static Long trainingInstitutionId;
    private TrainingInstitutionDAO TrainingInstitutionDAO;
    private static ApplicationContext ctx;

    public TrainingInstitutionJUnitTest() {
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
    public void createNewTrainingInstitution() {
        //Create TrainingInstitution Object You need to replace this with a Creational Design Pattern

        TrainingInstitution t = new TrainingInstitution();
 

        Contacts contact = new Contacts();
        contact.setAddressType("Postal");
        contact.setCellnumber("076 294 6746");
        contact.setEmail("pasqualliee@cput.ac.za");
        contact.setFaxnumber("021 959 6908");
        contact.setMailingAddress("CPUT PO BOX 959");
        contact.setNotes("Started");
        contact.setTelephoneNumber("021 959 6907");

        List<Contacts> contactInfo = new ArrayList<Contacts>();
        contactInfo.add(contact);
       



        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        TrainingInstitutionDAO.persist(t);
        trainingInstitutionId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        TrainingInstitution t = TrainingInstitutionDAO.find(trainingInstitutionId);
      
    }

    @Test
    public void testUpdate() {
        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        TrainingInstitution t = TrainingInstitutionDAO.find(trainingInstitutionId);
      
        TrainingInstitutionDAO.merge(t);
        TrainingInstitution p2 = TrainingInstitutionDAO.find(trainingInstitutionId);
      
    }

    @Test
    public void testCount() {
        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        Long count = TrainingInstitutionDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        List<TrainingInstitution> universities = TrainingInstitutionDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        TrainingInstitution t = TrainingInstitutionDAO.getByPropertyName("nameTrainer", "Rooney");
       

    }

    @Test
    public void testDelete() {
        TrainingInstitutionDAO = (TrainingInstitutionDAO) ctx.getBean("trainingInstitutionDAO");
        TrainingInstitution t = TrainingInstitutionDAO.find(trainingInstitutionId);
        TrainingInstitutionDAO.remove(t);

    }

}