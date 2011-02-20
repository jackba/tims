/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.SalaryGradesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.jobs.SalaryGrade;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class SalaryGradesResource {
    public abstract Collection<SalaryGrade> getSalaryGradess();

    public abstract SalaryGrade getSalaryGrades(Long id);

    public abstract SalaryGrade getSalaryGrades(@Context Request request,RequestForm form);

    public abstract Response createSalaryGrades(@Context Request request,SalaryGradesForm form);

     public abstract Response updateSalaryGrades(@Context Request request,SalaryGrade salaryGrades);
}
