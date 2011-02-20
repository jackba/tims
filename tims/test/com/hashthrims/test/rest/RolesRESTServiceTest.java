/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RolesForm;
import com.hashthrims.domain.Roles;
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
public class RolesRESTServiceTest {
public RolesRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/rolesservice/");
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
    public void readRoles() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/rolesservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the project name or the class name
        Roles roles = client.path("hashthrims/1770").accept("application/xml").get(Roles.class);
        Assert.assertNotNull(roles);
    }

    @Test
    public void updateRoles() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/rolesservice/");
        // You have to Supply the ID that is in the DB /roles/{ID} for the Test to pass
        Roles voucher = client.path("roles/1770").accept("application/xml").get(Roles.class);

        //Now Update the Roles
        client.back(true);
        client.path("/roles/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        Roles newroles = client.path("roles/1770").accept("application/xml").get(Roles.class);

    }


    public void printRoleses(){

        //Code Invetory

    }

    // @Test
    public void testCreateRoleses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/rolesservice/");
        client.path("roles");
        RolesForm f = new  RolesForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
