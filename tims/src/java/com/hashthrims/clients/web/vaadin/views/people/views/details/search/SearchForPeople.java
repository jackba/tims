/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.search;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class SearchForPeople {

    private final ClientDataService data = new ClientDataService();
    private List<Person> results = new ArrayList<Person>();

    public List<Person> results(String firstName, String lastName, Long facilityId) {

        if (lastName != null & firstName == null & facilityId == null) {
            results = searchByLastName(lastName);
        }

        if (lastName == null & firstName != null & facilityId == null) {
            results = searchByFirstName(firstName);
        }

        if (lastName == null & firstName != null & facilityId != null) {
            results = searchByFirstName(firstName, facilityId);
        }

        if (lastName != null & firstName == null & facilityId != null) {
            results = searchByLastName(lastName, facilityId);
        }

        if (lastName == null & firstName == null & facilityId != null) {
            results = searchByfacility(facilityId);
        }

        if (lastName == null & firstName == null & facilityId == null) {
            results = searchByAll();
        }

        if (lastName != null & firstName != null & facilityId != null) {
            results = searchByAllParamaters(lastName, firstName, facilityId);
        }


        return results;
    }

    private List<Person> searchByFirstName(String firstName) {

        return data.getPersonService().getEntitiesByProperName("personName", firstName);
    }

    private List<Person> searchByLastName(String lastName) {

        return data.getPersonService().getEntitiesByProperName("personSurname", lastName);
    }

    private List<Person> searchByfacility(Long facilityId) {
        Facility facility = data.getFacilityService().find(facilityId);
        List<Person> res = new ArrayList<Person>();
        List<Person> persons = data.getPersonService().findAll();
        for (Person person : persons) {
            if (matchFacility(person.getPosition(),facility)) {
                res.add(person);
            }

        }
        return res;
    }

    private List<Person> searchByFirstName(String firstName, Long facilityId) {
        System.out.println("SEARCHING BY FIRST NAME AND FACILITY ID");
        return results;
    }

    private List<Person> searchByLastName(String lastName, Long facilityId) {
        System.out.println("SEARCHING BY LAST NAME AND FACILITY ID");
        return results;
    }

    private List<Person> searchByAll() {

        return data.getPersonService().findAll();
    }

    private List<Person> searchByAllParamaters(String lastName, String firstName, Long facilityId) {
        System.out.println("SEARCHING FOR ALL PARAMETERS ");
        return results;
    }

    private boolean matchFacility(List<EmployeePosition> position, Facility facility) {
        for (EmployeePosition employeePosition : position) {
             Facility fac = getFacilityObject(employeePosition.getPosition());
             if(facility.equals(fac))
                 return true;
        }
        return false;
    }

    private Facility getFacilityObject(Positions position) {
        if(position!=null)
            return position.getFacililty();
        return null;
    }
}
