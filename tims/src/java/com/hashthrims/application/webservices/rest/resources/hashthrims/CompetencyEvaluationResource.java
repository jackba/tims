/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyEvaluationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CompetencyEvaluationResource {
    public abstract Collection<CompetencyEvaluation> getCompetencyEvaluations();

    public abstract CompetencyEvaluation getCompetencyEvaluation(Long id);

    public abstract CompetencyEvaluation getCompetencyEvaluation(@Context Request request,RequestForm form);

    public abstract Response createCompetencyEvaluation(@Context Request request,CompetencyEvaluationForm form);

     public abstract Response updateCompetencyEvaluation(@Context Request request,CompetencyEvaluation competencyEvaluation);
}
