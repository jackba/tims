/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jdbc;

import com.hashthrims.clients.web.vaadin.views.reports.people.model.PeopleReport;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public interface PeopleReportDAO {

    public List<PeopleReport> getAll();

    public List<PeopleReport> getRecordByFacility(String facility);

    public List<PeopleReport> getRecordByDate(Date startDate, Date endDate);

    //Province
    public List<PeopleReport> getRecordByProvinceAll(Date startDate, Date endDate);

    public List<PeopleReport> getRecordByProvinceCourse(Date startDate, Date endDate, String course);

    public List<PeopleReport> getRecordByProvinceProfession(Date startDate, Date endDate, String profession);

    //District 
    public List<PeopleReport> getRecordByDistrictAll(Date startDate, Date endDate, String district);

    public List<PeopleReport> getRecordByDistrictCourse(Date startDate, Date endDate, String course, String district);

    public List<PeopleReport> getRecordByDistrictProfession(Date startDate, Date endDate, String profession, String district);

    //Subdistrict
    public List<PeopleReport> getRecordBySubDistrictAll(Date startDate, Date endDate, String subdistrict);

    public List<PeopleReport> getRecordBySubDistrictCourse(Date startDate, Date endDate, String course, String subdistrict);

    public List<PeopleReport> getRecordBySubDistrictProfession(Date startDate, Date endDate, String profession, String subdistrict);

    //City
    public List<PeopleReport> getRecordByCityAll(Date startDate, Date endDate, String city);

    public List<PeopleReport> getRecordByCityCourse(Date startDate, Date endDate, String course, String city);

    public List<PeopleReport> getRecordByCityProfession(Date startDate, Date endDate, String profession, String city);

    //Facility
    public List<PeopleReport> getRecordByFacilityAll(Date startDate, Date endDate, String facility);

    public List<PeopleReport> getRecordByFacilityCourse(Date startDate, Date endDate, String course, String facility);

    public List<PeopleReport> getRecordByFacilityProfession(Date startDate, Date endDate, String profession, String facility);
}
