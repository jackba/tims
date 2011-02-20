/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.domain.employeelist.LanguageProficiency;
import com.hashthrims.repository.jpa.LanguageProficiencyDAO;

import com.hashthrims.services.Impl.LanguageProficiencyImplService;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author stud
 */
public class MockLanguageProficiencyJUnitTest {
    private static LanguageProficiencyDAO dao;
   private static LanguageProficiencyImplService languageProficiencyService;


    public MockLanguageProficiencyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(LanguageProficiencyDAO.class);
        languageProficiencyService = new LanguageProficiencyImplService();
        languageProficiencyService.setLanguageProficiencyDAO(dao);

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
     public void createLanguageProficiency()
     {
      
      LanguageProficiency e = new LanguageProficiency();
       e.setProficency("profesional");

       


        dao.persist(e);
        EasyMock.replay(dao);
        languageProficiencyService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetLanguageProficiency()
    {
       LanguageProficiency d = new LanguageProficiency();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        languageProficiencyService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

