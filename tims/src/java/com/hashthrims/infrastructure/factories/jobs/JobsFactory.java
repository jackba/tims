/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.jobs;

import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.regionlist.Currency;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CadresService;
import com.hashthrims.services.ClassificationsService;
import com.hashthrims.services.CurrencyService;
import com.hashthrims.services.JobsService;
import com.hashthrims.services.SalaryGradesService;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author stud
 */
public class JobsFactory {

    private JobsService jobservice;
    private SalaryGradesService salaryGradesService;
    private ClassificationsService classificationsService;
    private CadresService cadresService;
    private CurrencyService currencyService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public Jobs createJobs(String jobTittle, String jobCode, String jobDesc, SalaryGrade salarygrades, Classifications classifications, Cadres cadres) {
        Jobs j = new Jobs();
        j.setJob_code(jobCode);
        j.setJob_desc(jobDesc);
        j.setJob_tittle(jobTittle);
        j.setCadres(cadres);
        j.setClassications(classifications);
        j.setSalaryGrades(salarygrades);
        return j;
    }

    public Jobs loadJobs(Long id) {
        jobservice = (JobsService) ctx.getBean("jobsService");
        Jobs j = jobservice.find(id);
        return j;
    }

    public SalaryGrade loadSalaryGrades(Long id) {
        salaryGradesService = (SalaryGradesService) ctx.getBean("salaryGradesService");
        SalaryGrade s = salaryGradesService.find(id);
        return s;
    }

    public Classifications createClassifications(String jobName, String jobDesc, String jobCode) {
        Classifications c = new Classifications();
        c.setJob_code(jobCode);
        c.setJob_name(jobName);
        c.setJob_desc(jobDesc);
        return c;
    }

    public Classifications loadClassification(Long id) {
        classificationsService = (ClassificationsService) ctx.getBean("classificationsService");
        Classifications c = classificationsService.find(id);
        return c;
    }

    public Cadres createCadres(String cadresName) {
        Cadres c = new Cadres();
        c.setCadres_name(cadresName);

        return c;
    }

    public Cadres loadCadres(Long id) {
        cadresService = (CadresService) ctx.getBean("cadresService");
        Cadres c = cadresService.find(id);
        return c;
    }
    //Added update for fields

    public Cadres updatedCadres(String name, Long id) {
        Cadres c = loadCadres(id);
        c.setCadres_name(name);
        return c;
    }

    public Classifications updatedClassification(String jobName, String jobCode, String jobDesc, Long id) {
        Classifications c = loadClassification(id);
        c.setJob_name(jobName);
        c.setJob_code(jobCode);
        c.setJob_desc(jobDesc);
        c.setId(id);
        return c;
    }

    public SalaryGrade createSalaryGrade(String gradeName, String startAmount, String endAmount, String midAmount, String currentAmount, String currency, String notes) {
        SalaryGrade sg = new SalaryGrade();
        currencyService = (CurrencyService) ctx.getBean("currencyService");
        Currency cur = currencyService.getByPropertyName("currencySymbol", currency);
        sg.setGradeName(gradeName);
        sg.setStartAmount(new BigDecimal(startAmount));
        sg.setEndAmount(new BigDecimal(endAmount));
        sg.setMidAmount(new BigDecimal(midAmount));
        sg.setCurrentAmount(new BigDecimal(currentAmount));
        sg.setCurrency(cur);
        sg.setNotes(notes);

        return sg;
    }

    public SalaryGrade updateSalaryGrade(String gradeName, String startAmount, String endAmount, String midAmount, String currentAmount, String currency, String notes, Long salaryGradeId) {
        SalaryGrade sg = loadSalaryGrades(salaryGradeId);
        currencyService = (CurrencyService) ctx.getBean("currencyService");
        Currency cur = currencyService.getByPropertyName("currencySymbol", currency);
        sg.setGradeName(gradeName);
        sg.setStartAmount(new BigDecimal(startAmount));
        sg.setEndAmount(new BigDecimal(endAmount));
        sg.setMidAmount(new BigDecimal(midAmount));
        sg.setCurrentAmount(new BigDecimal(currentAmount));
        sg.setCurrency(cur);
        sg.setNotes(notes);

        return sg;
    }

    public Jobs updateJob(String title, String description, String code,  String jobClassification, String cadre, Long jobsId) {
        Jobs job = loadJobs(jobsId);
//        salaryGradesService = (SalaryGradesService) ctx.getBean("salaryGradesService");
//        SalaryGrade salary = salaryGradesService.getByPropertyName("gradeName", salaryGrade);
        cadresService = (CadresService) ctx.getBean("cadresService");
        Cadres cdr = cadresService.getByPropertyName("cadresName", cadre);
        classificationsService = (ClassificationsService) ctx.getBean("classificationsService");
        Classifications classfication = classificationsService.getByPropertyName("jobName", jobClassification);

        job.setCadres(cdr);
        job.setClassications(classfication);
//        job.setSalaryGrades(salary);

        job.setJob_code(code);
        job.setJob_desc(description);
        job.setJob_tittle(title);

        return job;
    }

    public Jobs createJob(String title, String description, String code, String jobClassification, String cadre) {
        Jobs job = new Jobs();
//        salaryGradesService = (SalaryGradesService) ctx.getBean("salaryGradesService");
//        SalaryGrade salary = salaryGradesService.getByPropertyName("gradeName", salaryGrade);
        cadresService = (CadresService) ctx.getBean("cadresService");
        Cadres cdr = cadresService.getByPropertyName("cadresName", cadre);
        classificationsService = (ClassificationsService) ctx.getBean("classificationsService");
        Classifications classfication = classificationsService.getByPropertyName("jobName", jobClassification);

        job.setCadres(cdr);
        job.setClassications(classfication);
//        job.setSalaryGrades(salary);

        job.setJob_code(code);
        job.setJob_desc(description);
        job.setJob_tittle(title);


        return job;

    }
}
