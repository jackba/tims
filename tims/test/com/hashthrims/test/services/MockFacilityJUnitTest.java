/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.repository.jpa.FacilityDAO;
import com.hashthrims.services.Impl.FacilityImplService;
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
public class MockFacilityJUnitTest {
      private static FacilityDAO dao;
   private static FacilityImplService facilityService;


    public MockFacilityJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(FacilityDAO.class);
        facilityService = new FacilityImplService();
        facilityService.setFacilityDAO(dao);

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
     public void createFacility()
     {
        Facility d = new Facility();
        d.setFacilityName("I.T-Department");
       // d.setContactInfo("07455889123 garronappl@yahoo.com");

        FacilityType ft = new FacilityType();
        ft.setFacilityName("Informatics and design");


        Country c = new Country();
        c.setAlphaCode("6798");
        c.setCountryName("Nigeria");
        c.setNumericCode(45);
        c.setPrimaryCountry(true);
        c.setLocation(true);


        Contacts cntcs = new Contacts();
        cntcs.setAddressType("Normal");
        cntcs.setCellnumber("0827131895");
        cntcs.setEmail("user@gmail.com");
        cntcs.setFaxnumber("02185902");
        cntcs.setMailingAddress("www.hr@hotmail.com");
        cntcs.setNotes("Active");
        cntcs.setTelephoneNumber("0218594301");




      EducationType e = new EducationType();
      e.setEduc_type_name("Higher education");

       


        dao.persist(d);
        EasyMock.replay(dao);
        facilityService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetFacility()
    {
       Facility d = new Facility();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        facilityService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

