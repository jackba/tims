/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.util;

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
public class GetPeopleInSpecificFacility {
    public List<Person> getPeopleInFacility(List<Person> persons, Facility facility){
       // List<Person> people = new ArrayList<Person>();
        if(facility!=null)
            return peopleInFacility(persons,facility);
        return persons;
    }

    private List<Person> peopleInFacility(List<Person> persons, Facility facility) {
        List<Person> people = new ArrayList<Person>();
        for (Person person : persons) {
           if(facility.equals(getCurrentPersonFacility(person)))
               people.add(person);
        }
        return people;
    }

    private Facility getCurrentPersonFacility(Person person) {

        List<EmployeePosition> positions = null;
        if (person.getPosition()!=null) {
            positions = person.getPosition();
        } else {
             positions = new ArrayList<EmployeePosition>();
        }

        for (EmployeePosition employeePosition : positions) {
            if("CURRENT".equals(employeePosition.getStatus()))
                return getFacility(employeePosition.getPosition());
        }
        return null;
    }

    private Facility getFacility(Positions position) {
        if(position!=null)
            return position.getFacililty();
        return null;
    }

}
