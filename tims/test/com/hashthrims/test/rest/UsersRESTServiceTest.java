/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.UsersForm;
import com.hashthrims.domain.Users;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author abismail
 */
public class UsersRESTServiceTest {
public UsersRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/usersservice/");
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

    //Test requires the Application to be running in the Container
    //
    @Test
    public void readUsers() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/usersservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        Users users = client.path("hashthrims/1770").accept("application/xml").get(Users.class);
        Assert.assertNotNull(users);
    }

    @Test
    public void updateUsers() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/usersservice/");
        // You have to Supply the ID that is in the DB /users/{ID} for the Test to pass
        Users voucher = client.path("users/1770").accept("application/xml").get(Users.class);

        //Now Update the Users
        client.back(true);
        client.path("/users/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Users newusers = client.path("users/1770").accept("application/xml").get(Users.class);

    }


    public void printUserses(){

        //Code Invetory

    }

    // @Test
    public void testCreateUserses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/usersservice/");
        client.path("users");
        UsersForm f = new  UsersForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
