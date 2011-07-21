/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Contacts;
import java.util.ArrayList;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import org.junit.Assert;
import com.hashthrims.repository.jpa.TrainingCoursesDAO;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author staff
 */
public class TrainingCoursesJUnitTest {

    private static Long trainingCoursesId;
    private TrainingCoursesDAO TrainingCoursesDAO;
    private static ApplicationContext ctx;

    public TrainingCoursesJUnitTest() {
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
    public void createNewTrainingCourses() {
        //Create TrainingCourses Object You need to replace this with a Creational Design Pattern

        TrainingCourses t = new TrainingCourses();
        t.setCourseName("Food Tech");
        t.setCourseTopic("Food Enzyme Fundamentals");
       
        
        EmployeeCourses competency = new EmployeeCourses();
//        competency.setComEvaluation(null);
//        competency.setCompList(null);
        competency.setCompetencyName("");
        competency.setCompetencyNotes("Competant");
        competency.setLastEvaluated(null);

        List <EmployeeCourses> courseCompetencies = new ArrayList<EmployeeCourses>();
        courseCompetencies.add(competency);
       


        TrainingCourseCategory tcc = new TrainingCourseCategory();
        tcc.setCategoryName("Microsoft");


        TrainingInstitution ti = new TrainingInstitution();
  

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
       

        
        Status s = new Status();
        s.setStatus("Completed");
        
        TrainingFunder tf = new TrainingFunder();
      

//        Contacts contact = new Contacts();
//        contact.setAddressType("Residential");
//        contact.setCellnumber("076 294 6746");
//        contact.setEmail("pasqualliee@cput.ac.za");
//        contact.setFaxnumber("021 959 6908");
//        contact.setMailingAddress("CPUT PO BOX 959");
//        contact.setNotes("Started");
//        contact.setTelephoneNumber("021 959 6907");
//
//        List<Contacts> contactInfo = new ArrayList<Contacts>();
//        contactInfo.add(contact);
//        tf.setContactInfo(contactInfo);



        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        TrainingCoursesDAO.persist(t);
        trainingCoursesId = t.getId();
        Assert.assertNotNull(t.getId());
    }

    @Test
    public void testRead() {
        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        TrainingCourses t = TrainingCoursesDAO.find(trainingCoursesId);
        Assert.assertEquals("Food Tech", t.getCourseName());
    }

    @Test
    public void testUpdate() {
        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        TrainingCourses t = TrainingCoursesDAO.find(trainingCoursesId);
        t.setCourseName("Management");
        TrainingCoursesDAO.merge(t);
        TrainingCourses p2 = TrainingCoursesDAO.find(trainingCoursesId);
        Assert.assertEquals("Management", p2.getCourseName());
    }

    @Test
    public void testCount() {
        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        Long count = TrainingCoursesDAO.count();
        Assert.assertEquals(new Long(1), count);
    }

    @Test
    public void testList() {
        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        List<TrainingCourses> universities = TrainingCoursesDAO.findAll();
        Assert.assertTrue(universities.size() > 0);
    }

    @Test
    public void tstGetByParamater() {
        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        TrainingCourses t = TrainingCoursesDAO.getByPropertyName("courseName", "Management");
        Assert.assertEquals("Management", t.getCourseName());

    }

    @Test
    public void testDelete() {
        TrainingCoursesDAO = (TrainingCoursesDAO) ctx.getBean("trainingCoursesDAO");
        TrainingCourses t = TrainingCoursesDAO.find(trainingCoursesId);
        TrainingCoursesDAO.remove(t);

    }

}