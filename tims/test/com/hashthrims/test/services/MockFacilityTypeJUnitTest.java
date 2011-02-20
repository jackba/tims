/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.repository.jpa.FacilityTypeDAO;
import com.hashthrims.services.Impl.FacilityTypeImplService;
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
public class MockFacilityTypeJUnitTest {
      private static FacilityTypeDAO dao;
   private static FacilityTypeImplService facilityTypeService;


    public MockFacilityTypeJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(FacilityTypeDAO.class);
        facilityTypeService = new FacilityTypeImplService();
        facilityTypeService.setFacilityTypeDAO(dao);

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
     public void createFacilityType()
     {
        FacilityType d = new FacilityType();
        d.setFacilityName("I.T");


        Facility f = new Facility();
       // f.setContactInfo("0745589485");
        f.setFacilityName("I.T");
       // f.setContactInfo("07455889123 garronappl@yahoo.com");



      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(d);
        EasyMock.replay(dao);
        facilityTypeService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetFacilityType()
    {
       FacilityType d = new FacilityType();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        facilityTypeService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

