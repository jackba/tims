/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.setup;

import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.EmployeePositionDAO;
import com.hashthrims.repository.jpa.PersonDAO;
import com.hashthrims.repository.jpa.PositionsDAO;
import com.hashthrims.repository.jpa.StatusDAO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author boniface
 */
public class AssignPositions {

    private static Long countryId;
    private PersonDAO personDAO;
    private StatusDAO statusDAO;
    private PositionsDAO positionDAO;
    private EmployeePositionDAO employeePositionDAO;
    private static ApplicationContext ctx;

    public AssignPositions() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new ClassPathXmlApplicationContext("classpath:com/hashthrims/infrastructure/conf/applicationContext-*.xml");

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
    @Ignore
    public void assignPositions() {
        personDAO = (PersonDAO) ctx.getBean(PersonDAO.class);
        statusDAO = (StatusDAO) ctx.getBean(StatusDAO.class);
        positionDAO = (PositionsDAO) ctx.getBean(PositionsDAO.class);
        employeePositionDAO = (EmployeePositionDAO) ctx.getBean(EmployeePositionDAO.class);


        try {
            URL url = this.getClass().getResource("positions.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("assign");

            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                List<EmployeePosition> positions = new ArrayList<EmployeePosition>();
                double pid = Double.parseDouble(worksheet.getRow(i).getCell(0).toString());
                Long personId = (long) pid;
                double poid = Double.parseDouble(worksheet.getRow(i).getCell(1).toString());
                Long positionId = (long) poid;
                Person person = personDAO.find(personId);
                Positions position = positionDAO.find(positionId);

                EmployeePosition p = new EmployeePosition();
                Status postionStatus = statusDAO.getByPropertyName("status", "FILLED");

                position.setPositionStatus(postionStatus);
                positionDAO.merge(position);
                p.setStartDate(new Date());
                p.setPosition(position);
                p.setStatus("CURRENT");
                person.getPosition().add(p);
                personDAO.merge(person);

            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    @Test
    public void deletePositions() {
         personDAO = (PersonDAO) ctx.getBean(PersonDAO.class);
         List<Person> people = personDAO.findAll();
         for (Person person : people) {
             if(person.getPosition().size()<1){
             personDAO.remove(person);
             }
                 
            
        }
        
    }
}
