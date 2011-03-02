/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.test.setup;

import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.hashthrims.repository.jpa.CountryDAO;
import com.hashthrims.repository.jpa.CountyDAO;
import com.hashthrims.repository.jpa.DistrictDAO;
import com.hashthrims.repository.jpa.RegionDAO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class AddLocations {

    private static Long countryId;
    private CountryDAO CountryDAO;
    private RegionDAO provinceDAO;
    private CountyDAO countyDAO;
    private DistrictDAO subDistrictDAO;
    private static ApplicationContext ctx;

    public AddLocations() {
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
    public void addCountryAndProvinces() {
        List<Province> provinces = new ArrayList<Province>();
        try {
            URL url = this.getClass().getResource("setup.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("provinces");

            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Province p = new Province();
                p.setRegionCode(worksheet.getRow(i).getCell(1).toString());
                p.setRegionName(worksheet.getRow(i).getCell(0).toString());
                provinces.add(p);
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        Country u = new Country();
        u.setAlphaCode("ZA");
        u.setCountryName("South Africa");
        u.setLocation(true);
        u.setNumericCode(27);
        u.setPrimaryCountry(true);
        u.setProvince(provinces);

        CountryDAO = (CountryDAO) ctx.getBean("countryDAO");
        CountryDAO.persist(u);
    }

    @Ignore
    public void addDistricticts() {
        List<County> districts = new ArrayList<County>();
        try {
            URL url = this.getClass().getResource("setup.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheetAt(1);


            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                County c = new County();
                c.setCountyName(worksheet.getRow(i).getCell(1).toString());
                c.setCountyCode(worksheet.getRow(i).getCell(0).toString());
                districts.add(c);
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        provinceDAO = (RegionDAO) ctx.getBean("regionDAO");
        // Load a Province To Populate 
        Province u = provinceDAO.find(new Long(619));
        u.setCounty(districts);
        provinceDAO.merge(u);
    }

    @Ignore
    public void addSubDistricts() {
        List<District> districts = new ArrayList<District>();
        try {
            URL url = this.getClass().getResource("setup.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("nzo");



            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                District c = new District();
                c.setDistrictCode(worksheet.getRow(i).getCell(1).toString());
                c.setDistrictName(worksheet.getRow(i).getCell(0).toString());
                districts.add(c);
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        countyDAO = (CountyDAO) ctx.getBean("countyDAO");
        County u = countyDAO.find(new Long(628));
        u.setDistrict(districts);
        countyDAO.merge(u);
    }

    @Ignore
    public void addOneCityToEachDistrict() {
        countyDAO = (CountyDAO) ctx.getBean("countyDAO");
        subDistrictDAO = (DistrictDAO) ctx.getBean("districtDAO");
        County district = countyDAO.find(new Long(634));

        List<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityCode("BE");
        city.setName("Barkly East");
        cities.add(city);
        List<District> subDistricts = district.getDistrict();
        for (District subdistrict : subDistricts) {
            subdistrict.setCities(cities);
            subDistrictDAO.merge(subdistrict);
        }



    }
}
