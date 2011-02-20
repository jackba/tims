/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.CompetencyTypeDAO;
import com.hashthrims.services.Impl.CompetencyTypeImplService;
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
public class MockCompetencyTypeJUnitTest {
      private static CompetencyTypeDAO dao;
   private static CompetencyTypeImplService competencyTypeService;


    public MockCompetencyTypeJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(CompetencyTypeDAO.class);
        competencyTypeService = new CompetencyTypeImplService();
        competencyTypeService.setCompetencyTypeDAO(dao);

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
     public void createCompetencyTypes()
     {
      
      CompetencyType e = new CompetencyType();
       e.setComp_name_typ("ability");


        dao.persist(e);
        EasyMock.replay(dao);
        competencyTypeService.persist(e);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetCompetencyType()
    {
       CompetencyType d = new CompetencyType();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        competencyTypeService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

