/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.Users;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.UsersDAO;
import com.hashthrims.services.Impl.UsersImplService;
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
public class MockUsersJUnitTest {
      private static UsersDAO dao;
   private static UsersImplService usersService;


    public MockUsersJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(UsersDAO.class);
        usersService = new UsersImplService();
        usersService.setUsersDAO(dao);

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
     public void createUsers()
     {
        Users d = new Users();
        d.setFirstname("John");
      
       


        dao.persist(d);
        EasyMock.replay(dao);
        usersService.persist(d);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetUsers()
    {
       Users d = new Users();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        usersService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

