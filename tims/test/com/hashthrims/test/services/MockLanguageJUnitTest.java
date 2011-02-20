/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.repository.jpa.LanguageDAO;
import com.hashthrims.services.Impl.LanguageImplService;
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
public class MockLanguageJUnitTest {
      private static LanguageDAO dao;
   private static LanguageImplService languageService;


    public MockLanguageJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(LanguageDAO.class);
        languageService = new LanguageImplService();
        languageService.setLanguageDAO(dao);

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
     public void createBenefits()
     {
      
      Language e = new Language();
       e.setLanguage_name("Zulu");

       


        dao.persist(e);
        EasyMock.replay(dao);
        languageService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetLanguage()
    {
       Language d = new Language();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        languageService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

