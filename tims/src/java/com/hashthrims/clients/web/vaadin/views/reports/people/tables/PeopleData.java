/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.tables;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.DateSearchCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.LocationCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.model.PeopleReport;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
class PeopleData {

    ClientDataService data = new ClientDataService();
    DataFieldsUtil date = new DataFieldsUtil();

    public PeopleData() {
    }

    List<PeopleReport> getPeople(LocationCombo locationCombo, DateSearchCombo dateSearchCombo) {
        List<PeopleReport> people = new ArrayList<PeopleReport>();
        Date startDate = date.getDateFields(dateSearchCombo.getStartDate().getValue());
        Date endDate = date.getDateFields(dateSearchCombo.getEndDate().getValue());
        if (dateSearchCombo.getPositionsCombo().getValue() == null & dateSearchCombo.getTopicCombo().getValue() == null) {

            if (locationCombo.getFacility().getValue() != null) {
                return data.getPeopleReportService().getRecordByFacilityAll(startDate, endDate, locationCombo.getFacility().getValue().toString());
            } else if (locationCombo.getCity().getValue() != null) {
                return data.getPeopleReportService().getRecordByCityAll(startDate, endDate, locationCombo.getCity().getValue().toString());
            } else if (locationCombo.getSubDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordBySubDistrictAll(startDate, endDate, locationCombo.getSubDistrict().getValue().toString());
            } else if (locationCombo.getDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordByDistrictAll(startDate, endDate, locationCombo.getDistrict().getValue().toString());
            } else {
                return data.getPeopleReportService().getRecordByProvinceAll(startDate, endDate);
            }
        } else if (dateSearchCombo.getPositionsCombo().getValue() == null & dateSearchCombo.getTopicCombo().getValue() != null) {
            //Topic Search

            if (locationCombo.getFacility().getValue() != null) {
                return data.getPeopleReportService().getRecordByFacilityCourse(startDate, endDate, getTopic(dateSearchCombo.getTopicCombo().getValue()), getPosition(locationCombo.getFacility().getValue()));
            } else if (locationCombo.getCity().getValue() != null) {
                return data.getPeopleReportService().getRecordByCityCourse(startDate, endDate, getTopic(dateSearchCombo.getTopicCombo().getValue()), locationCombo.getCity().getValue().toString());
            } else if (locationCombo.getSubDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordBySubDistrictCourse(startDate, endDate, getTopic(dateSearchCombo.getTopicCombo().getValue()), locationCombo.getSubDistrict().getValue().toString());
            } else if (locationCombo.getDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordByDistrictCourse(startDate, endDate, getTopic(dateSearchCombo.getTopicCombo().getValue()), locationCombo.getDistrict().getValue().toString());
            } else {
                return data.getPeopleReportService().getRecordByProvinceCourse(startDate, endDate, getTopic(dateSearchCombo.getTopicCombo().getValue()));
            }

        } else if (dateSearchCombo.getPositionsCombo().getValue() != null & dateSearchCombo.getTopicCombo().getValue() == null) {
            //Course Professional
            if (locationCombo.getFacility().getValue() != null) {
                return data.getPeopleReportService().getRecordByFacilityProfession(startDate, endDate, dateSearchCombo.getPositionsCombo().getValue().toString(), locationCombo.getFacility().getValue().toString());
            } else if (locationCombo.getCity().getValue() != null) {
                return data.getPeopleReportService().getRecordByCityProfession(startDate, endDate, dateSearchCombo.getPositionsCombo().getValue().toString(), locationCombo.getCity().getValue().toString());
            } else if (locationCombo.getSubDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordBySubDistrictProfession(startDate, endDate, dateSearchCombo.getPositionsCombo().getValue().toString(), locationCombo.getSubDistrict().getValue().toString());
            } else if (locationCombo.getDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordByDistrictProfession(startDate, endDate, dateSearchCombo.getPositionsCombo().getValue().toString(), locationCombo.getDistrict().getValue().toString());
            } else {
                return data.getPeopleReportService().getRecordByProvinceProfession(startDate, endDate, dateSearchCombo.getPositionsCombo().getValue().toString());
            }

        } else {
            if (locationCombo.getFacility().getValue() != null) {
                return data.getPeopleReportService().getRecordByFacilityProfessionAndCourse(startDate, endDate, locationCombo.getFacility().getValue().toString(), dateSearchCombo.getPositionsCombo().getValue().toString(), dateSearchCombo.getTopicCombo().getValue().toString());
            } else if (locationCombo.getCity().getValue() != null) {
                return data.getPeopleReportService().getRecordByCityProfessionAndCourse(startDate, endDate, locationCombo.getCity().getValue().toString(), dateSearchCombo.getPositionsCombo().getValue().toString(), dateSearchCombo.getTopicCombo().getValue().toString());
            } else if (locationCombo.getSubDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordBySubDistrictProfessionAndCourse(startDate, endDate, locationCombo.getSubDistrict().getValue().toString(), dateSearchCombo.getPositionsCombo().getValue().toString(), dateSearchCombo.getTopicCombo().getValue().toString());
            } else if (locationCombo.getDistrict().getValue() != null) {
                return data.getPeopleReportService().getRecordByDistrictProfessionAndCourse(startDate, endDate, locationCombo.getDistrict().getValue().toString(), dateSearchCombo.getPositionsCombo().getValue().toString(), dateSearchCombo.getTopicCombo().getValue().toString());
            } else {
                return data.getPeopleReportService().getRecordByProvinceProfessionAndCourse(startDate, endDate, dateSearchCombo.getPositionsCombo().getValue().toString(), dateSearchCombo.getTopicCombo().getValue().toString());
            }
        }
    }

    private String getTopic(Object value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    private String getPosition(Object value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }
}
