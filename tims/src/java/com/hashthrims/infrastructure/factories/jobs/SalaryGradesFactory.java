/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories.jobs;

import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.SalaryGradesService;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author stud
 */
public class SalaryGradesFactory {


    private SalaryGradesService salaryGradesService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

     public SalaryGrade createSalaryGrades(String salaryName, String notes, BigDecimal midAmount, BigDecimal endAmount,BigDecimal startAmount)
    {
        SalaryGrade s = new SalaryGrade();
        s.setGradeName(salaryName);
        s.setNotes(notes);
        s.setStartAmount(startAmount);
        s.setMidAmount(midAmount);
        s.setEndAmount(endAmount);
        return s;
    }
    public SalaryGrade loadSalaryGrades(Long id) {
        salaryGradesService = (SalaryGradesService) ctx.getBean("salaryGradesService");
        SalaryGrade s = salaryGradesService.find(id);
        return s;
    }

}
