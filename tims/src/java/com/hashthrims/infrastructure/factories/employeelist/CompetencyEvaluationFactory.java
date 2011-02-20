/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CompetencyEvaluationService;
import org.springframework.context.ApplicationContext;


/**
 *
 * @author boniface
 */
public class CompetencyEvaluationFactory {

    private CompetencyEvaluationService competencyEvaluationService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public CompetencyEvaluation createCompetencyEvaluation(String competencyEvaluation) {
        CompetencyEvaluation e = new CompetencyEvaluation();
        e.setCompt_type_name(competencyEvaluation);
        return e;
    }

    public CompetencyEvaluation loadCompetencyEvaluation(Long id) {
        competencyEvaluationService = (CompetencyEvaluationService) ctx.getBean("competencyEvaluationService");
        CompetencyEvaluation e = competencyEvaluationService.find(id);
        return e;
    }

    public CompetencyEvaluation updatedCompetencyEvaluation(String name, Long id) {
        CompetencyEvaluation c = loadCompetencyEvaluation(id);
        c.setCompt_type_name(name);
        return c;
    }

   
}
