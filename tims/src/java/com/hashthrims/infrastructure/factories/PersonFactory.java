/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.*;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.infrastructure.conf.GetContext;
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

//        EmployeePosition pos = new EmployeePosition();
//        pos.setEnddate(dates.get("endDate"));
//        pos.setStartDate(dates.get("startDate"));
//        pos.setStatus(personValues.get("status"));
//        Positions position = data.getPositionsService().getByPropertyName("positionCode", personValues.get("position"));
//        pos.setPosition(position);
//
//
//
//        p.getPosition().add(pos);
        cityService = (CityService)ctx.getBean("cityService");
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

//        EmployeePosition pos = new EmployeePosition();
//
//        pos.setEnddate(dates.get("endDate"));
//        pos.setStartDate(dates.get("startDate"));
//        pos.setStatus(personValues.get("status"));
//        Positions position = data.getPositionsService().getByPropertyName("positionCode", personValues.get("position"));
//        pos.setPosition(position);
//
//
//
//        p.getPosition().add(pos);

        cityService = (CityService)ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", personValues.get("residence"));
        p.setResidence(c);

        return p;
    }
}
