/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.util;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeActionPlan;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityGrouping;
import com.hashthrims.domain.positions.Positions;
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

    public List<Person> getClusterPeopleWithPendingActionPlan(Long mentoringSessionId) {
        List<Person> menteesInCluster = new ArrayList<Person>();
        List<Person> list = data.getPersonService().findAll();
        MentoringSession ms = data.getMentoringSessionService().find(mentoringSessionId);
        Facility facility = ms.getMentoringVenue();
        for (Person person : list) {
            if(isPersonInCluster(facility,person)){
                if (hasPersonGotPendingActionPlan(person)) {
                    menteesInCluster.add(person);
                }
            }
        }
        return  menteesInCluster;
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

    private boolean isPersonInCluster(Facility facility,Person person) {
        String personCluster = getPersonClusterName(person);
        boolean isPersonIncluster = false;
        if(facility.getFacilityGrouping()!=null){
            if (personCluster!=null) {
                if (personCluster.equalsIgnoreCase(clusterGroupingName(facility.getFacilityGrouping()))) {
                    isPersonIncluster = true;
                }
            }
        }
        return isPersonIncluster;
    }

    private String getPersonClusterName(Person person) {
        if(person.getPosition().size()<0)
            return null;
        return personFacility(person.getPosition());
    }

    private String personFacility(List<EmployeePosition> position) {
        String clusterName = null;
        for (EmployeePosition p : position) {
            if (p.getStatus().equalsIgnoreCase("CURRENT")) {
                clusterName = getPosition(p);
            }
        }
        return clusterName;
    }

    private String getPosition(EmployeePosition p) {
        if(p.getPosition()!=null)
            return getFacility(p.getPosition());
        return null;
    }

    private String getFacility(Positions position) {
        if(position.getFacililty()!=null)
            return getFacilityGrouping(position.getFacililty());
        return null;
    }

    private String getFacilityGrouping(Facility facililty) {
        if(facililty.getFacilityGrouping()!=null)
            return facililty.getFacilityGrouping().getCluster();
        return null;
    }

    private boolean hasPersonGotPendingActionPlan(Person person) {
        boolean hasPendingActionPlan = false;
        if(person.getActionPlans().size()<0)
            return  hasPendingActionPlan;
        return anypendingPlan(person.getActionPlans());
            
    }

    private boolean anypendingPlan(List<EmployeeActionPlan> actionPlans) {
        boolean pendingPlan = false;
        for (EmployeeActionPlan employeeActionPlan : actionPlans) {
            if(employeeActionPlan.getActionPlan()!=null)
                if(employeeActionPlan.getStatus().equalsIgnoreCase("PENDING")){
                    pendingPlan=true;
                }
        }
        return pendingPlan;
    }

    private String clusterGroupingName(FacilityGrouping facilityGrouping) {
        if(facilityGrouping!=null)
            return facilityGrouping.getCluster();
        return null;
    }
}
