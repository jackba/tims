/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jdbc.queries;

import com.hashthrims.clients.web.vaadin.views.reports.people.model.PeopleReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;



/**
 *
 * @author boniface
 */
public class SelectAllRecordsByDates extends MappingSqlQuery<PeopleReport> {

    private static final String SQL_SELECT_ALL_RECORDS = " "
            + "SELECT   "
            +   "person.personsurname , "
            +   "person.personname ,"
            +   "jobs.jobtittle , "
            +   "facility.facilityname ,  city.name,  "
            +   "district.districtname , "
            +   "county.countyname , "
            +   "employeecourses.courseenddate ,  "
            +   "employeecourses.coursestartdate ,  "
            +   "scheduledcourses.coursename "
            + "FROM  "
            +   "public.person,  "
            +   "public.employeeposition, "
            +   "public.jobs,   public.facility, "
            +   "public.positions,   public.city, "
            +   "public.district,   public.county, "
            +   "public.employeecourses, "
            +   "public.scheduledcourses "
            + "WHERE  "
            +   "employeeposition.person_id = person.id "
            +   "AND  employeeposition.status ='CURRENT' "
            +   "AND  employeeposition.position_id = positions.id "
            +   "AND  positions.job_id = jobs.id "
            +   "AND  positions.facililty_id = facility.id "
            +   "AND  facility.city_id = city.id "
            +   "AND  city.district_id = district.id "
            +   "AND  district.county_id = county.id "
            +   "AND  employeecourses.person_id=person.id "
            +   "AND  scheduledcourses.id=employeecourses.scheduledcoursesessionid "
            +   "AND  employeecourses.coursestartdate BETWEEN :coursestartdate AND :courseenddate ";
    
    public SelectAllRecordsByDates(DataSource dataSource){
        super(dataSource,SQL_SELECT_ALL_RECORDS);
        super.declareParameter(new SqlParameter("coursestartdate", Types.DATE));
        super.declareParameter(new SqlParameter("courseenddate", Types.DATE));

    }

    @Override
    protected PeopleReport mapRow(ResultSet rs, int i) throws SQLException {
        PeopleReport report = new PeopleReport();
        report.setCityname(rs.getString("name"));
        report.setCourseEndDate(rs.getDate("courseenddate"));
        report.setCourseName(rs.getString("coursename"));
        report.setCourseStateDate(rs.getDate("coursestartdate"));
        report.setDistrict(rs.getString("districtname"));
        report.setFacilityname(rs.getString("facilityname"));
        report.setFirstname(rs.getString("personname"));
        report.setLastname(rs.getString("personsurname"));
        report.setProfession(rs.getString("jobtittle"));
        report.setSubdistrict(rs.getString("countyname"));
        return report;
    }
}
