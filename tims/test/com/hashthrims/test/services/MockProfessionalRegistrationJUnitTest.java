package com.hashthrims.test.services;


import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.ProfessionalRegistrationDAO;
import com.hashthrims.services.Impl.ProfessionalRegistrationImplService;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author |\/|0(|<_G3N
 */
public class MockProfessionalRegistrationJUnitTest {
      private static ProfessionalRegistrationDAO dao;
   private static ProfessionalRegistrationImplService facilityService;


    public MockProfessionalRegistrationJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(ProfessionalRegistrationDAO.class);
        facilityService = new ProfessionalRegistrationImplService();
        facilityService.setProfessionalRegistrationDAO(dao);

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
     public void createProfessionalRegistration()
     {
        ProfessionalRegistration d = new ProfessionalRegistration();
        d.setLicenceNumber("257575");
        dao.persist(d);
        EasyMock.replay(dao);
        facilityService.persist(d);
        EasyMock.verify(dao);

    }

    @Test
    public void testGetProfessionalRegistration()
    {
       ProfessionalRegistration d = new ProfessionalRegistration();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        facilityService.find(new Long(1));
        EasyMock.verify(dao);
    }
}