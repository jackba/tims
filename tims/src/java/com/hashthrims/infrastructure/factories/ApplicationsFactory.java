/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;


import com.hashthrims.domain.Applications;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.ApplicationsService;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class ApplicationsFactory {
//
    private ApplicationsService applicationsService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

public Applications createApplication(String additionalSkills, String advertSource, String availability, SalaryGrade desiredWage, boolean employmentContract, String felony, String felonyDetails, Positions position, Date startDate){
        Applications c = new Applications();
        c.setAdditionalSkills(additionalSkills);
        c.setAdverSource(advertSource);
        c.setAvailability(availability);
        c.setDesiredWage(desiredWage);
        c.setEmploymentyContract(employmentContract);
        c.setFelony(felony);
        c.setFelonyDetails(felonyDetails);
        c.setPosition(position);
        c.setStartDate(startDate);
        applicationsService = (ApplicationsService) ctx.getBean("applicationsService");
        applicationsService.persist(c);
        return c;
    }



    public Applications updateApplication(String additionalSkills, String advertSource, String availability, SalaryGrade desiredWage, boolean employmentContract, String felony, String felonyDetails, Positions position, Date startDate, Long id){
        applicationsService = (ApplicationsService) ctx.getBean("applicationsService");
        Applications c = applicationsService.find(id);
        c.setAdditionalSkills(additionalSkills);
        c.setAdverSource(advertSource);
        c.setAvailability(availability);
        c.setDesiredWage(desiredWage);
        c.setEmploymentyContract(employmentContract);
        c.setFelony(felony);
        c.setFelonyDetails(felonyDetails);
        c.setPosition(position);
        c.setStartDate(startDate);
        applicationsService.merge(c);
        return applicationsService.find(id);
    }

    public Applications updateSalary(BigDecimal amount, String currency, Long id){
        applicationsService = (ApplicationsService) ctx.getBean("applicationsService");
        Applications c = applicationsService.find(id);
        //c.getDesiredWage().setAmount(amount);
       // c.getDesiredWage().setCurrency(currency);
        applicationsService.merge(c);
        return applicationsService.find(id);
    }

   
    public Applications loadApplications(Long id) {
        applicationsService = (ApplicationsService) ctx.getBean("applicationsService");
        Applications c = applicationsService.find(id);
        return c;
    }
}