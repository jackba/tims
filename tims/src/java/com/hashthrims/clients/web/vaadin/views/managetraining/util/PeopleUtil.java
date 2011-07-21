/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.util;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.MentoringSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PeopleUtil {
    private final static ClientDataService data = new ClientDataService();


    public List<Person> getMentors(List<Person> list) {
        List<Person> mentors = new ArrayList<Person>();
        List<Person> people = data.getPersonService().findAll();
        for (Person person : people) {
            if (isPersonMentor(person)) {
                mentors.add(person);
            } 
        }
        return mentors;
    }

    public List<Person> getAllPeopleWithPendingActionPlan(List<Person> list) {
        return list;
    }

    public List<Person> getFacilityPeopleWithPendingActionPlan(List<Person> list, Long facilityId) {
        return list;
    }

    public List<Person> getClusterPeopleWithPendingActionPlan(Long sessionId) {
        List<Person> list = data.getPersonService().findAll();
        MentoringSession ms = data.getMentoringSessionService().find(sessionId);
        Facility facility = ms.getMentoringVenue();
        
        //TODO PAUSE HERE TO BE CONTINUED 
        
        
        
        return list;
    }

    public List<Person> getNodePeopleWithPendingActionPlan(List<Person> list, Long clusterId) {
        return list;
    }

    private boolean isPersonMentor(Person person) {
        boolean isPersonMentor = false;
        List<PersonRoles> roles = person.getPersonRoles();
        for (PersonRoles role : roles) {
            if(role.getRoleName().equalsIgnoreCase("Mentor"))
            isPersonMentor = true;
        }
       return isPersonMentor;
    }
}
