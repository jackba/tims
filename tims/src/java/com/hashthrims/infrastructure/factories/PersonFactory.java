/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.*;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.regionlist.AddressType;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.services.CityService;
import com.hashthrims.services.PersonService;
import java.util.*;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class PersonFactory {

    private ApplicationContext ctx = GetContext.getApplicationContext();
    private PersonService personService;
    private CityService cityService;
    private ClientDataService data = new ClientDataService();
    private final PositionsFactory factory = data.getPositionFactory();

    public Person loadPerson(Long id) {
        personService = (PersonService) ctx.getBean("personService");
        Person s = personService.find(id);
        return s;
    }

    public Person createPerson(Map<String, String> personValues, Map<String, Date> dates) {
        Person p = new Person();
        p.setPersonName(personValues.get("personName"));
        p.setPersonOtherName(personValues.get("personOtherName"));
        p.setPersonSurname(personValues.get("personSurname"));


        Demography d = new Demography();
        d.setDob(dates.get("dob"));
        d.setGender(personValues.get("gender"));
        p.setDemography(d);


        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", personValues.get("residence"));
        p.setResidence(c);

        return p;
    }

    public Person updatePerson(Map<String, String> personValues, Map<String, Date> dates, Long personId) {
        Person p = loadPerson(personId);
        p.setPersonName(personValues.get("personName"));
        p.setPersonOtherName(personValues.get("personOtherName"));
        p.setPersonSurname(personValues.get("personSurname"));

        Demography d = p.getDemography();
        d.setDob(dates.get("dob"));
        d.setGender(personValues.get("gender"));
        p.setDemography(d);


        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", personValues.get("residence"));
        p.setResidence(c);

        return p;
    }

    public Person createNewPerson(Map<String, String> names, Map<String, Collection<Long>> lists, Map<String, Long> demo, Map<String, Date> dates) {
        Person p = new Person();
        p.setPersonName(names.get("firstname"));
        p.setPersonOtherName(names.get("othername"));
        p.setPersonSurname(names.get("surname"));



        Contacts c = new Contacts();
        c.setAddressType("Personal Contact");
        c.setCellnumber(names.get("telephoneNumber"));
        c.setEmail(names.get("cellnumber"));
        c.setFaxnumber(names.get("faxnumber"));
        c.setTelephoneNumber(names.get("email"));
        p.getContacts().add(c);


        final Identities i = new Identities();
        i.setIdType(names.get("idType"));
        i.setIdValue(names.get("idValue"));
        p.getIdentities().add(i);


        final Long positionId = demo.get("positionId");
        final Date startDate = dates.get("startDate");
        final Positions position = data.getPositionsService().find(positionId);
        final Status st = data.getStatusService().getByPropertyName("status", "FILLED");
        position.setPositionStatus(st);
        data.getPositionsService().merge(position);
        EmployeePosition employeePositions = factory.createEmployeePosition(position, "CURRENT", startDate, null);
        p.getPosition().add(employeePositions);

        //Demo
//        dates.put("dob", dob);
//        

        Demography d = new Demography();
        d.setDob(dates.get("dob"));
        GenderList gender = data.getGenderListService().find(demo.get("genderId"));
        if (gender != null) {
            d.setGender(gender.getGender());
        }
        RaceList race = data.getRaceListService().find(demo.get("raceId"));
        if (race != null) {
            d.setRace(race.getRaceName());
        }
        p.setDemography(d);

        Collection<Long> rolesid = lists.get("rolesid");
        for (Long roleid : rolesid) {
            PersonRoles r = new PersonRoles();
            r.setRoleName(getMentorsRolesName(data.getMentorsRolesService().find(roleid)));
            p.getPersonRoles().add(r);
        }
        Collection<Long> competencyFieldId = lists.get("competencyFieldId");
        for (Long compid : competencyFieldId) {
            MentorExpertiseArea a = new MentorExpertiseArea();
            a.setExpertiseAreaName(getFieldName(data.getMentoringFieldService().find(compid)));
            p.getExpertiseArea().add(a);
        }
        Collection<Long> expertiseId = lists.get("expertiseId");
        for (Long expertiseid : expertiseId) {
        }

        return p;
    }

    public Person updateNewPerson(Map<String, String> names, Map<String, Collection<Long>> lists, Map<String, Long> demo, Map<String, Date> dates) {
        Person p = data.getPersonService().find(demo.get("id"));
        p.setPersonName(names.get("firstname"));
        p.setPersonOtherName(names.get("othername"));
        p.setPersonSurname(names.get("surname"));

        //COntacts 
//        names.put("telephoneNumber", telephoneNumber);
//        names.put("cellnumber", cellnumber);
//        names.put("faxnumber", faxnumber);
//        names.put("email", email);

        if (p.getContacts().size() < 1) {
            Contacts c = new Contacts();
            c.setAddressType("Personal Contact");
            c.setCellnumber(names.get("telephoneNumber"));
            c.setEmail(names.get("cellnumber"));
            c.setFaxnumber(names.get("faxnumber"));
            c.setTelephoneNumber(names.get("email"));
            p.getContacts().add(c);
        }

        // Identies
//        names.put("idType", idType);
//        names.put("idValue", idValue);

        if (p.getIdentities().size() < 1) {
            final Identities i = new Identities();
            i.setIdType(names.get("idType"));
            i.setIdValue(names.get("idValue"));
            p.getIdentities().add(i);
        }
//        
        //Position
//        demo.put("positionId", positionId);
//        demo.put("facilityId", facilityId);
//        dates.put("startDate", startDate);

        if (p.getPosition().size() < 1) {
            final Long positionId = demo.get("positionId");
            final Date startDate = dates.get("startDate");
            final Positions position = data.getPositionsService().find(positionId);
            final Status st = data.getStatusService().getByPropertyName("status", "FILLED");
            position.setPositionStatus(st);
            data.getPositionsService().merge(position);
            EmployeePosition employeePositions = factory.createEmployeePosition(position, "CURRENT", startDate, null);
            p.getPosition().add(employeePositions);
        }
        //Demo
//        dates.put("dob", dob);
//        

        Demography d = p.getDemography();
        d.setDob(dates.get("dob"));
        GenderList gender = data.getGenderListService().find(demo.get("genderId"));
        if (gender != null) {
            d.setGender(gender.getGender());
        }
        RaceList race = data.getRaceListService().find(demo.get("raceId"));
        if (race != null) {
            d.setRace(race.getRaceName());
        }
        p.setDemography(d);

        p.getPersonRoles().clear();
        Collection<Long> rolesid = lists.get("rolesid");
        for (Long roleid : rolesid) {
            PersonRoles r = new PersonRoles();
            r.setRoleName(getMentorsRolesName(data.getMentorsRolesService().find(roleid)));
            p.getPersonRoles().add(r);
        }
        p.getExpertiseArea().clear();
        Collection<Long> competencyFieldId = lists.get("competencyFieldId");
        for (Long compid : competencyFieldId) {
            MentorExpertiseArea a = new MentorExpertiseArea();
            a.setExpertiseAreaName(getFieldName(data.getMentoringFieldService().find(compid)));
            p.getExpertiseArea().add(a);
        }
        Collection<Long> expertiseId = lists.get("expertiseId");
        for (Long expertiseid : expertiseId) {
        }

        return p;
    }

    private String getMentorsRolesName(MentorsRoles role) {
        if (role != null) {
            return role.getMentorsRolesName();
        }
        return null;
    }

    private String getFieldName(MentoringField role) {
        if (role != null) {
            return role.getFieldName();
        }
        return null;
    }
}
