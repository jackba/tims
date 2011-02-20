/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Demography;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DemographyService;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class DemographyFactory {
    private DemographyService demographyService;


    private ApplicationContext ctx = GetContext.getApplicationContext();


    public Demography createDemography(int dependants, Date dob, String gender, String maritalStatus, Person personDemographics){
        Demography c = new Demography();
        c.setDependants(dependants);
        c.setDob(dob);
        c.setGender(gender);
        c.setMaritalStatus(maritalStatus);
        c.setPersonDemographics(personDemographics);
        return c;
    }

    public Demography loadDemography(Long id) {
        demographyService = (DemographyService) ctx.getBean("demographyService");
        Demography c = demographyService.find(id);
        return c;
    }
}
