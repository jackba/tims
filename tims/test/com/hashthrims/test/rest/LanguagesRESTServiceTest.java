/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.test.rest;


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguagesForm;
import com.hashthrims.domain.EmployeeLanguages;
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
public class LanguagesRESTServiceTest {
public LanguagesRESTServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/languagesservice/");
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
    public void readLanguages() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/languagesservice/");
        // You have to Supply the ID that is in the DB /voucher/{ID} for the Test to pass
//                                      vcheck whether this should be the
        EmployeeLanguages languages = client.path("hashthrims/1770").accept("application/xml").get(EmployeeLanguages.class);
        Assert.assertNotNull(languages);
    }

    @Test
    public void updateLanguages() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/ws/languagesservice/");
        // You have to Supply the ID that is in the DB /languages/{ID} for the Test to pass
        EmployeeLanguages voucher = client.path("languages/1770").accept("application/xml").get(EmployeeLanguages.class);

        //Now Update the EmployeeLanguages
        client.back(true);
        client.path("/languages/update");
        client.put(voucher);
        client.back(true);
        //Retrieve the Updated Voucher
        EmployeeLanguages newlanguages = client.path("languages/1770").accept("application/xml").get(EmployeeLanguages.class);

    }


    public void printLanguageses(){

        //Code Invetory

    }

    // @Test
    public void testCreateLanguageses() {
        WebClient client = WebClient.create("http://localhost:8084/hashthrims/languagesservice/");
        client.path("languages");
        LanguagesForm f = new  LanguagesForm();
        //Initialise Here
        client.post(f);
        client.back(true);
    }
}
