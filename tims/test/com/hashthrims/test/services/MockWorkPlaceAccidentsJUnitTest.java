package com.hashthrims.test.services;


import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.WorkPlaceAccidents;
import com.hashthrims.repository.jpa.WorkPlaceAccidentsDAO;
import com.hashthrims.services.Impl.WorkPlaceAccidentsImplService;
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
public class MockWorkPlaceAccidentsJUnitTest {
      private static WorkPlaceAccidentsDAO dao;
   private static WorkPlaceAccidentsImplService facilityService;


    public MockWorkPlaceAccidentsJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(WorkPlaceAccidentsDAO.class);
        facilityService = new WorkPlaceAccidentsImplService();
        facilityService.setWorkPlaceAccidentsDAO(dao);

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
     public void createWorkPlaceAccidents()
     {
        WorkPlaceAccidents d = new WorkPlaceAccidents();
        d.setPeopleInvioved("alot");
        dao.persist(d);
        EasyMock.replay(dao);
        facilityService.persist(d);
        EasyMock.verify(dao);

    }

    @Test
    public void testGetWorkPlaceAccidents()
    {
       WorkPlaceAccidents d = new WorkPlaceAccidents();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        facilityService.find(new Long(1));
        EasyMock.verify(dao);
    }
}