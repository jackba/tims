/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.Roles;
import com.hashthrims.repository.jpa.RolesDAO;
import com.hashthrims.services.Impl.RolesImplService;
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
public class MockRolesJUnitTest {
      private static RolesDAO dao;
   private static RolesImplService rolesService;


    public MockRolesJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(RolesDAO.class);
        rolesService = new RolesImplService();
        rolesService.setRolesDAO(dao);

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
     public void createRoles()
     {
        Roles u = new Roles();
        u.setRoleName("Manager");

        dao.persist(u);
        EasyMock.replay(dao);
        rolesService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetRoles()
    {
       Roles d = new Roles();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        rolesService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

