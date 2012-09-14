/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.repository;

import com.hashthrims.clients.web.vaadin.views.reports.people.model.PeopleReport;
import com.hashthrims.repository.jdbc.PeopleReportDAO;
import java.util.List;
import org.joda.time.DateTime;

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
 * @author boniface
 */
public class PeopleReportJUnitTest {


    private PeopleReportDAO peopleReportDAO;
    private static ApplicationContext ctx;

    public PeopleReportJUnitTest() {
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
    public void createNewBenefitType() {
 
        peopleReportDAO = (PeopleReportDAO) ctx.getBean("peopleReportDAO");
        
        DateTime start = new DateTime("2008-01-11");
        DateTime end = new DateTime("2011-01-12");
        
        List<PeopleReport> report = peopleReportDAO.getRecordByDate(start.toDate(), end.toDate());
        
        System.out.println(" THE SIZE of DATA ia "+report.size());
    }

    
}