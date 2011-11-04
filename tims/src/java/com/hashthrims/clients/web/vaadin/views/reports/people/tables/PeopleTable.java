/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.tables;

import com.hashthrims.clients.web.vaadin.views.reports.people.forms.DateSearchCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.LocationCombo;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PeopleTable {

    public Table getTable(LocationCombo locationCombo, DateSearchCombo dateSearchCombo) {
        Table table = new Table();
        table.setSizeFull();

        table.addContainerProperty("First Name", String.class, null);
        table.addContainerProperty("Last Name", String.class, null);
        table.addContainerProperty("Profession ", String.class, null);
        table.addContainerProperty("Facility ", String.class, null);
        
        List<Person> people = getListOfPeople(locationCombo, dateSearchCombo);
        
        for (Person p : people) {
              table.addItem(new Object[] {p.getPersonName(),p.getPersonSurname(),getProfesion(p.getPosition()), getFacility(p.getPosition())}, p.getId());
            
        }

        return table;
    }

    private List<Person> getListOfPeople(LocationCombo locationCombo, DateSearchCombo dateSearchCombo) {
        return new PeopleData().getPeople(locationCombo,dateSearchCombo);
    }

    private String  getProfesion(List<EmployeePosition> position) {
        String profesion = null;
          for (EmployeePosition p : position) {
            profesion = getJob(p.getPosition());
        }
          return profesion;
    }

    private String getFacility(List<EmployeePosition> position) {
        String facilityName = null;
        for (EmployeePosition p : position) {
            
            facilityName = getFacility(p.getPosition());
            
        }
       return facilityName;
    }

    private String getJob(Positions position) {
        if(position!=null)
            return getJobName(position.getJob());
        return null;
    }

    private String getJobName(Jobs job) {
        if(job!=null)
            return job.getJob_tittle();
        return null;
    }

    private String getFacility(Positions position) {
        if(position!=null)
            return getFacilityName(position.getFacililty());
        return null;
    }

    private String getFacilityName(Facility facililty) {
        if(facililty!=null)
            return facililty.getFacilityName();
        return null;
    }
}
