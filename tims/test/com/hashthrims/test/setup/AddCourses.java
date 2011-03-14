/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.setup;

import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.CompetencyListDAO;
import com.hashthrims.repository.jpa.CompetencyTypeDAO;
import org.springframework.context.ApplicationContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
public class AddCourses {

    private static Long countryId;
    private CompetencyListDAO competencyListDAO;
    private CompetencyTypeDAO competencyTypeDAO;
    private static ApplicationContext ctx;

    public AddCourses() {
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
    @Test
    public void addCourses() {
        competencyListDAO = (CompetencyListDAO)ctx.getBean(CompetencyListDAO.class);
        competencyTypeDAO = (CompetencyTypeDAO)ctx.getBean(CompetencyTypeDAO.class);
        CompetencyType ct = competencyTypeDAO.find(new Long(58748));
        try {
            URL url = this.getClass().getResource("courses.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("comps");
            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                CompetencyList competencyList = new CompetencyList();
                competencyList.setCompType(ct);
                competencyList.setComp_name(worksheet.getRow(i).getCell(0).toString());
                competencyList.setNotes("NONE");
                competencyListDAO.persist(competencyList);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }
}
