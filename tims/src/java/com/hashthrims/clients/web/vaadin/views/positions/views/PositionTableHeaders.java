/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Status;

/**
 *
 * @author boniface
 */
public class PositionTableHeaders {
    private static final ClientDataService data = new ClientDataService();

    Long getFacilityId(String toString) {
        Facility facility = data.getFacilityService().getByPropertyName("facilityName", toString);
        if(facility!=null)
            return facility.getId();
        return null;
    }

    Long getSupervisorId(String toString) {
         Jobs position = data.getJobService().getByPropertyName("jobTittle", toString);
       if(position!=null)
           return position.getId();
         //Fix this baug later we need a
       return null;
    }

    Long getDepartmentId(String toString) {
        Department dept = data.getDepartmentService().getByPropertyName("deptName", toString);
        if(dept!=null)
            return dept.getId();
        return null;
    }

    Long getPotisionTitleId(String toString) {
       Jobs position = data.getJobService().getByPropertyName("jobTittle", toString);
       if(position!=null)
           return position.getId();
       return null;
    }

    Long getPositionTypeId(String toString) {
        PositionTypes posTypes = data.getPositionTypes().getByPropertyName("posTypeName", toString);
        if(posTypes!=null)
            return posTypes.getId();
        return null;
    }

    Long getPositionStatusId(String toString) {
        Status status = data.getStatusService().getByPropertyName("status", toString);
        if(status!=null)
            return status.getId();
        return null;
    }

}
