/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.services;


import com.hashthrims.domain.Contacts;
import com.hashthrims.repository.jpa.ContactsDAO;
import com.hashthrims.services.Impl.ContactsImplService;
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
public class MockContactsJUnitTest {
      private static ContactsDAO dao;
   private static ContactsImplService contactsService;


    public MockContactsJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IMocksControl mockCreator = EasyMock.createControl();
        dao = mockCreator.createMock(ContactsDAO.class);
        contactsService = new ContactsImplService();
        contactsService.setContactsDAO(dao);

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
     public void createContacts()
     {
        Contacts u = new Contacts();
        u.setAddressType("Physical Address");

        dao.persist(u);
        EasyMock.replay(dao);
        contactsService.persist(u);
        EasyMock.verify(dao);

    }
    @Test
    public void testGetContacts()
    {
       Contacts d = new Contacts();
        EasyMock.reset(dao);
        EasyMock.expect(dao.find(new Long(1))).andReturn(d);
        EasyMock.replay(dao);
        contactsService.find(new Long(1));
        EasyMock.verify(dao);


      }



     }

