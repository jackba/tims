/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.setup;

import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Demography;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.regionlist.AddressType;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.repository.jpa.AddressTypeDAO;
import com.hashthrims.repository.jpa.CadresDAO;
import com.hashthrims.repository.jpa.CityDAO;
import com.hashthrims.repository.jpa.ClassificationDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.CountryDAO;
import com.hashthrims.repository.jpa.CountyDAO;
import com.hashthrims.repository.jpa.DemographyDAO;
import com.hashthrims.repository.jpa.DepartmentDAO;
import com.hashthrims.repository.jpa.DistrictDAO;
import com.hashthrims.repository.jpa.FacilityDAO;
import com.hashthrims.repository.jpa.FacilityTypeDAO;
import com.hashthrims.repository.jpa.JobsDAO;
import com.hashthrims.repository.jpa.PersonDAO;
import com.hashthrims.repository.jpa.PositionTypesDAO;
import com.hashthrims.repository.jpa.PositionsDAO;
import com.hashthrims.repository.jpa.RegionDAO;
import com.hashthrims.repository.jpa.StatusDAO;
import com.hashthrims.repository.jpa.impl.FacilityDAOJPAImpl;
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
public class Addfacilities {

    private static Long countryId;
    private FacilityTypeDAO faciliTypeDAO;
    private RegionDAO provinceDAO;
    private CountyDAO countyDAO;
    private DistrictDAO subDistrictDAO;
    private CityDAO cityDAO;
    private FacilityDAO facilityDAO;
    private JobsDAO jobsDAO;
    private CadresDAO cadresDAO;
    private ClassificationDAO classificationDAO;
    private static ApplicationContext ctx;

    public Addfacilities() {
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
    public void addFacilityType() {
        faciliTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        try {
            URL url = this.getClass().getResource("facility.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("ftype");

            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                FacilityType p = new FacilityType();
                p.setFacilityName(worksheet.getRow(i).getCell(0).toString());
                faciliTypeDAO.persist(p);
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }

    @Ignore
    public void addAddFacilities() {



        City city = null;
        faciliTypeDAO = (FacilityTypeDAO) ctx.getBean("facilityTypeDAO");
        subDistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        FacilityType ftype = faciliTypeDAO.find(new Long(725));
        List<Facility> facilities = ftype.getFacility();
        if (facilities == null) {
            facilities = new ArrayList<Facility>();
        } else {
        }
        try {
            URL url = this.getClass().getResource("facility.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("umzh");
            District subDistrict = subDistrictDAO.find(new Long(677));
            List<City> cities = subDistrict.getCities();
            for (City city1 : cities) {
                city = city1;
            }
            Contacts contact = new Contacts();
            contact.setAddressType("Work Contact");
            contact.setEmail("info@rtc.wsu.ac.za");
            contact.setFaxnumber("NOT AVAILABLE");
            contact.setMailingAddress("NOT AVAILABLE");
            contact.setTelephoneNumber("NOT AVALIBALE");
            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Facility facility = new Facility();
                facility.setFacilityName(worksheet.getRow(i).getCell(0).toString());
                // facility.setFacilityType(ftype);
                facility.setCity(city);
                facility.setContact(contact);
                facilities.add(facility);
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        ftype.setFacility(facilities);
        faciliTypeDAO.merge(ftype);

    }

    @Ignore
    public void addJobs() {
        classificationDAO = (ClassificationDAO) ctx.getBean("classificationsDAO");
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");
        cadresDAO = (CadresDAO) ctx.getBean("cadresDAO");
        Cadres cadre = cadresDAO.find(new Long(1543));
        Classifications cl = classificationDAO.find(new Long(1544));

        try {
            URL url = this.getClass().getResource("positions.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("jobs");
            System.out.println("SIZE OF JOBS LIST " + worksheet.getPhysicalNumberOfRows());



            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Jobs c = new Jobs();
                c.setJob_tittle(worksheet.getRow(i).getCell(0).toString());
                c.setJob_desc(worksheet.getRow(i).getCell(0).toString());
                c.setJob_code("JOBP00" + i);
                c.setClassications(cl);
                c.setCadres(cadre);
                jobsDAO.persist(c);


            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }

    @Test
    public void addJobPositions() {
        countyDAO = (CountyDAO) ctx.getBean("countyDAO");
        facilityDAO = (FacilityDAO) ctx.getBean("facilityDAO");
        DepartmentDAO departmentDAO = (DepartmentDAO) ctx.getBean("departmentDAO");
        StatusDAO stausDAO = (StatusDAO) ctx.getBean("statusDAO");
        PositionTypesDAO positionTypesDAO = (PositionTypesDAO) ctx.getBean("positionTypesDAO");
        subDistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        PositionsDAO positionsDAO = (PositionsDAO) ctx.getBean("positionsDAO");
        jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");

        Department dept = departmentDAO.find(new Long(1536));
        Status status = stausDAO.find(new Long(1746));
        PositionTypes ptype = positionTypesDAO.find(new Long(1744));

        List<Facility> facilities = facilityDAO.findAll();

        for (Facility facility : facilities) {

            try {
                URL url = this.getClass().getResource("positions.xls");
                FileInputStream fileInputStream = new FileInputStream(url.getFile());

                HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                HSSFSheet worksheet = workbook.getSheet("positions");
                for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

                    double lv = Double.parseDouble(worksheet.getRow(i).getCell(0).toString());
                    Long lte = (long) lv;

                    Jobs job = jobsDAO.find(lte);
                    if (job == null) {
                        System.out.println(" THE ID WITH NULL ID " + lte);
                    }
                    Positions p = new Positions();
                    p.setDepartment(dept);
                    p.setFacililty(facility);
                    p.setPositionCode("00" + i + "-" + job.getJob_tittle().toString().toUpperCase().replaceAll(" ", ""));
                    p.setJob(job);
                    p.setPositionStatus(status);
                    p.setPositionType(ptype);
                    p.setPositionComments("These are Position of People Working at a Health Facility in Eastern Cape");
                    positionsDAO.persist(p);
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }

    }

    @Ignore
    public void addPeople() {
        PersonDAO personDAO = (PersonDAO) ctx.getBean("personDAO");
        DemographyDAO demographyDAO = (DemographyDAO) ctx.getBean("demographyDAO");
        subDistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        AddressTypeDAO addressTypeDAO = (AddressTypeDAO)ctx.getBean("addressTypeDAO");


        try {
            URL url = this.getClass().getResource("people.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("people");
            System.out.println("THE VALUES IN THE SHEET ARE "+worksheet.getPhysicalNumberOfRows());
            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

                double lv = Double.parseDouble(worksheet.getRow(i).getCell(3).toString());
                Long lte = (long) lv;

                Person person = new Person();
                Contacts contact = new Contacts();
                AddressType addrType = addressTypeDAO.find(new Long(1532));
                contact.setAddressType(addrType.getAddressTypeName());
                contact.setCellnumber("NOT AVALIBALE");
                contact.setEmail("NOT AVALIBALE");
                contact.setFaxnumber("NOT AVAILABLE");
                contact.setTelephoneNumber("NOT AVAILABLE");
                contact.setMailingAddress("NOT AVAILABLE");
                List<Contacts> contacts = new ArrayList<Contacts>();
                contacts.add(contact);

                person.setContacts(contacts);

                Demography demo = new Demography();
                demo.setDob(new Date());
                demo.setGender("Unkown");
                person.setDemography(demo);

                person.setPersonSurname(worksheet.getRow(i).getCell(0).toString());
                person.setPersonName(worksheet.getRow(i).getCell(1).toString());
                person.setPersonOtherName(worksheet.getRow(i).getCell(2).toString());
                
                District subDistrict = subDistrictDAO.find(lte);
                
                List<City> cities = subDistrict.getCities();
                for (City city : cities) {
                     person.setResidence(city);                    
                }
                personDAO.persist(person);

               
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }



    }
}
