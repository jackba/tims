/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.EmployeeBenefits;
import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.EmployeeBenefitsService;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class EmployeeBenefitsFactory {
private EmployeeBenefitsService employeeBenefitsService;


    private ApplicationContext ctx = GetContext.getApplicationContext();

    public EmployeeBenefits createEmployeeBenefits(SalaryGrade salary, BenefitType benefits, Date endDate, BenefitFrequency benefitFrequency, SalarySources salarySource, Date startDate){
        EmployeeBenefits c = new EmployeeBenefits();
        c.setAmount(salary);
        c.setBenefits(benefits);
        c.setEndDate(endDate);
        c.setFrequency(benefitFrequency);
        c.setSource(salarySource);
        c.setStartDate(startDate);
        return c;
    }

    public EmployeeBenefits loadEmployeeBenefits(Long id) {
        employeeBenefitsService = (EmployeeBenefitsService) ctx.getBean("employeebenefitsService");
        EmployeeBenefits c = employeeBenefitsService.find(id);
        return c;
    }
}
