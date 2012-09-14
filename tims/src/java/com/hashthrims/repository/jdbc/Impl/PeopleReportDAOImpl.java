/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jdbc.Impl;

import com.hashthrims.clients.web.vaadin.views.reports.people.model.PeopleReport;
import com.hashthrims.repository.jdbc.PeopleReportDAO;
import com.hashthrims.repository.jdbc.queries.SelectAllRecords;
import com.hashthrims.repository.jdbc.queries.SelectAllRecordsByDates;
import com.hashthrims.repository.jdbc.queries.SelectByCityAll;
import com.hashthrims.repository.jdbc.queries.SelectByCityCourse;
import com.hashthrims.repository.jdbc.queries.SelectByCityProfession;
import com.hashthrims.repository.jdbc.queries.SelectByDistrictAll;
import com.hashthrims.repository.jdbc.queries.SelectByDistrictCourse;
import com.hashthrims.repository.jdbc.queries.SelectByDistrictProfession;
import com.hashthrims.repository.jdbc.queries.SelectByFacilityAll;
import com.hashthrims.repository.jdbc.queries.SelectByFacilityCourse;
import com.hashthrims.repository.jdbc.queries.SelectByFacilityProfession;
import com.hashthrims.repository.jdbc.queries.SelectByProvinceAll;
import com.hashthrims.repository.jdbc.queries.SelectByProvinceCourse;
import com.hashthrims.repository.jdbc.queries.SelectByProvinceProfession;
import com.hashthrims.repository.jdbc.queries.SelectBySubDistrictAll;
import com.hashthrims.repository.jdbc.queries.SelectBySubDistrictCourse;
import com.hashthrims.repository.jdbc.queries.SelectBySubDistrictProfession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author boniface
 */
@Repository("peopleReportDAO")
public class PeopleReportDAOImpl implements PeopleReportDAO {

    @Autowired
    private DataSource dataSource;
    private SelectAllRecords selectAllRecords;
    private SelectAllRecordsByDates selectAllRecordsByDates;
    private SelectByCityAll selectByCityAll;
    private SelectByCityCourse selectByCityCourse;
    private SelectByCityProfession selectByCityProfession;
    private SelectByDistrictAll selectByDistrictAll;
    private SelectByDistrictCourse selectByDistrictCourse;
    private SelectByDistrictProfession selectByDistrictProfession;
    private SelectByFacilityAll selectByFacilityAll;
    private SelectByFacilityCourse selectByFacilityCourse;
    private SelectByFacilityProfession selectByFacilityProfession;
    private SelectByProvinceAll selectByProvinceAll;
    private SelectByProvinceCourse selectByProvinceCourse;
    private SelectByProvinceProfession selectByProvinceProfession;
    private SelectBySubDistrictAll selectBySubDistrictAll;
    private SelectBySubDistrictCourse selectBySubDistrictCourse;
    private SelectBySubDistrictProfession selectBySubDistrictProfession;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        selectAllRecords = new SelectAllRecords(dataSource);
        selectAllRecordsByDates = new SelectAllRecordsByDates(dataSource);
        selectByProvinceAll = new SelectByProvinceAll(dataSource);
        selectByCityAll = new SelectByCityAll(dataSource);
        selectByCityCourse = new SelectByCityCourse(dataSource);
        selectByCityProfession = new SelectByCityProfession(dataSource);
        selectByDistrictAll = new SelectByDistrictAll(dataSource);
        selectByDistrictCourse = new SelectByDistrictCourse(dataSource);
        selectByDistrictProfession = new SelectByDistrictProfession(dataSource);
        selectByFacilityAll = new SelectByFacilityAll(dataSource);
        selectByFacilityCourse = new SelectByFacilityCourse(dataSource);
        selectByFacilityProfession = new SelectByFacilityProfession(dataSource);
        selectByProvinceCourse = new SelectByProvinceCourse(dataSource);
        selectByProvinceProfession = new SelectByProvinceProfession(dataSource);
        selectBySubDistrictAll = new SelectBySubDistrictAll(dataSource);
        selectBySubDistrictCourse = new SelectBySubDistrictCourse(dataSource);
        selectBySubDistrictProfession = new SelectBySubDistrictProfession(dataSource);

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<PeopleReport> getAll() {
        return selectAllRecords.execute();
    }

    @Override
    public List<PeopleReport> getRecordByFacility(String facility) {
        List<PeopleReport> records = new ArrayList<PeopleReport>();
        List<PeopleReport> list = selectAllRecords.execute();
        return records;
    }

    @Override
    public List<PeopleReport> getRecordByDate(Date startDate, Date endDate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        return selectAllRecordsByDates.executeByNamedParam(paramMap);

    }

    @Override
    public List<PeopleReport> getRecordByProvinceAll(Date startDate, Date endDate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        return selectByProvinceAll.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByProvinceCourse(Date startDate, Date endDate, String course) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("coursename", course);
        return selectByProvinceCourse.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByProvinceProfession(Date startDate, Date endDate, String profession) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("jobtittle", profession);
        return selectByProvinceProfession.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByDistrictAll(Date startDate, Date endDate, String district) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("districtname", district);
        return selectByDistrictAll.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByDistrictCourse(Date startDate, Date endDate, String course, String district) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("districtname", district);
        paramMap.put("coursename", course);
        return selectByDistrictCourse.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByDistrictProfession(Date startDate, Date endDate, String profession, String district) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("districtname", district);
        paramMap.put("jobtittle", profession);
        return selectByDistrictProfession.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordBySubDistrictAll(Date startDate, Date endDate, String subdistrict) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("countyname", subdistrict);
        return selectBySubDistrictAll.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordBySubDistrictCourse(Date startDate, Date endDate, String course, String subdistrict) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("countyname", subdistrict);
        paramMap.put("coursename", course);
        return selectBySubDistrictCourse.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordBySubDistrictProfession(Date startDate, Date endDate, String profession, String subdistrict) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("countyname", subdistrict);
        paramMap.put("jobtittle", profession);
        return selectBySubDistrictProfession.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByCityAll(Date startDate, Date endDate, String city) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("name", city);
        return selectByCityAll.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByCityCourse(Date startDate, Date endDate, String course, String city) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("name", city);
        paramMap.put("coursename", course);
        return selectByCityCourse.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByCityProfession(Date startDate, Date endDate, String profession, String city) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("name", city);
        paramMap.put("jobtittle", profession);
        return selectByCityProfession.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByFacilityAll(Date startDate, Date endDate, String facility) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("facilityname", facility);
        return selectByFacilityAll.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByFacilityCourse(Date startDate, Date endDate, String course, String facility) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("facilityname", facility);
        paramMap.put("coursename", course);
        return selectByFacilityCourse.executeByNamedParam(paramMap);
    }

    @Override
    public List<PeopleReport> getRecordByFacilityProfession(Date startDate, Date endDate, String profession, String facility) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("coursestartdate", startDate);
        paramMap.put("courseenddate", endDate);
        paramMap.put("facilityname", facility);
        paramMap.put("jobtittle", profession);
        return selectByFacilityProfession.executeByNamedParam(paramMap);
    }
}
