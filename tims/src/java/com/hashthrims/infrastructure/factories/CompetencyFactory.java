/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;


import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CompetencyService;
import org.springframework.context.ApplicationContext;
/**
 *
 * @author abismail
 */
public class CompetencyFactory {
//    extends FactoryChainHandler
    private CompetencyService competencyService;


    private ApplicationContext ctx = GetContext.getApplicationContext();


//    public EmployeeCourses createCompetency(CompetencyEvaluation com, String cname, String cnotes, List<CompetencyList> competencyList){
//        EmployeeCourses c = new EmployeeCourses();
//        c.setComEvaluation(com);
//        c.setCompetencyName(cname);
//        c.setCompetencyNotes(cnotes);
//        c.setCompList(competencyList);
//        competencyService = (CompetencyService) ctx.getBean("competencyService");
//        competencyService.persist(c);
//        return c;
//    }
//
//    public CompetencyEvaluation createCompetencyEvaluation(String competencyTypeName){
//        CompetencyEvaluation c = new CompetencyEvaluation();
//        c.setCompt_type_name(competencyTypeName);
//        return c;
//    }
//
//    public EmployeeCourses updateCompetencyEvaluation(String competencyTypeName, Long id){
//        competencyService = (CompetencyService) ctx.getBean("competencyService");
//        EmployeeCourses c = competencyService.find(id);
//        c.getComEvaluation().setCompt_type_name(competencyTypeName);
//        competencyService.merge(c);
//        return c;
//    }
//
//    public CompetencyList createCompetencyList(CompetencyType competencyType, String competencyName, String notes){
//        CompetencyList c = new CompetencyList();
//        c.setCompType(competencyType);
//        c.setComp_name(competencyName);
//        c.setNotes(notes);
//        return c;
//    }
//
//    public EmployeeCourses updateCompetencyList(CompetencyType competencyType, String competencyName, String notes, Long id){
//        competencyService = (CompetencyService) ctx.getBean("competencyService");
//        EmployeeCourses c = competencyService.find(id);
//        CompetencyList r = (CompetencyList)c.getCompList();
//        c.setCompList((List<CompetencyList>)((new CompetencyListFactory()).createCompetencyList(competencyName, notes)));
//        return c;
//    }
//
//    public EmployeeCourses updateCompetency(CompetencyEvaluation com, String cname, String cnotes, List<CompetencyList> competencyList, Long id){
//        competencyService = (CompetencyService) ctx.getBean("competencyService");
//        EmployeeCourses c = competencyService.find(id);
//        c.setComEvaluation(com);
//        c.setCompetencyName(cname);
//        c.setCompetencyNotes(cnotes);
//        c.setCompList(competencyList);
//        competencyService.merge(c);
//        return c;
//    }
//
//    public EmployeeCourses loadCompetency(Long id) {
//        competencyService = (CompetencyService) ctx.getBean("competencyService");
//        EmployeeCourses c = competencyService.find(id);
//        return c;
//    }
}
