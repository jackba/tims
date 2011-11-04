/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.util;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PersonLocation {

    ClientDataService data = new ClientDataService();

    public Object getFacility(Positions position) {
        return position.getFacililty().getId();
    }

    public Object getFacility(List<EmployeePosition> position) {
        Object facility = null;
        for (EmployeePosition ep : position) {
            facility = ep.getPosition().getFacililty().getId();
        }
        return facility;
    }

    public Object getCity(List<EmployeePosition> position) {
        Object facility = null;
        for (EmployeePosition ep : position) {
            facility = ep.getPosition().getFacililty().getCity().getId();
        }
        return facility;
    }

    public Object getSubDistrict(List<EmployeePosition> position) {
        Object facility = null;
        for (EmployeePosition ep : position) {
            facility = ep.getPosition().getFacililty().getCity().getDistrict().getId();
        }
        return facility;
    }

    public Object getDistrict(List<EmployeePosition> position) {
        Object facility = null;
        for (EmployeePosition ep : position) {
            facility = ep.getPosition().getFacililty().getCity().getDistrict().getCounty().getId();
        }
        return facility;
    }

    public boolean isTrainingCourse(Long scheduledCourseSessionId, Object value) {

        boolean isTrainingCouse = false;

        ScheduledCourses sc = data.getScheduledCoursesType().find(scheduledCourseSessionId);

        if (sc.getCourseId().equals(value)) {
            isTrainingCouse = true;
        }

        return isTrainingCouse;
    }

    public boolean isPosition(List<EmployeePosition> position, Object value) {
        boolean isPosition = false;

        for (EmployeePosition employeePosition : position) {

            if (value.equals(employeePosition.getPosition().getJob().getId())) {
                isPosition = true;
            }

        }
        return isPosition;
    }
}
