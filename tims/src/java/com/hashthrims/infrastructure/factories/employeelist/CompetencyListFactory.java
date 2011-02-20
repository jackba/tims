/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CompetencyListService;
import com.hashthrims.services.CompetencyService;
import com.hashthrims.services.CompetencyTypeService;
import org.springframework.context.ApplicationContext;


/**
 *
 * @author boniface
 */
public class CompetencyListFactory {

    private CompetencyListService competencyListService;
    private CompetencyTypeService competencyTypeService;
    private CompetencyService competencyService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public CompetencyList createCompetencyList(String competencyList,String notes) {
        CompetencyList e = new CompetencyList();
        e.setComp_name(competencyList);
        e.setNotes(notes);
        return e;
    }
    public CompetencyType createCompetencyType(String competencyTypeName) {
        CompetencyType e = new CompetencyType();
       e.setComp_name_typ(competencyTypeName);
        return e;
    }

    public CompetencyList loadCompetencyList(Long id) {
        competencyListService = (CompetencyListService) ctx.getBean("competencyListService");
        CompetencyList e = competencyListService.find(id);
        return e;
    }
    public CompetencyType loadCompetencyType(Long id) {
        competencyTypeService = (CompetencyTypeService) ctx.getBean("competencyTypeService");
        CompetencyType e = competencyTypeService.find(id);
        return e;
    }

    public CompetencyList updatedCompetencyList(String name, Long id) {
        CompetencyList c = loadCompetencyList(id);
        c.setComp_name(name);
        return c;
    }

    public EmployeeCourses createCompetency(String competencyName, String competencyNotes) {
        EmployeeCourses e = new EmployeeCourses();
       e.setCompetencyName(competencyName);
       e.setCompetencyNotes(competencyNotes);
       //e.setCompetencyEval(competencyEval);
        return e;
    }

    public EmployeeCourses loadCompetency(Long id) {
        competencyService = (CompetencyService) ctx.getBean("competencyService");
        EmployeeCourses e = competencyService.find(id);
        return e;
    }

    public EmployeeCourses updatedCompetency(String name, Long id) {
        EmployeeCourses c = loadCompetency(id);
        c.setCompetencyName(name);
        return c;
    }

    public CompetencyList createCompetencyList(String competencyName, String competencyNotes, String competencyType) {
        CompetencyList cl = new CompetencyList();
        competencyTypeService = (CompetencyTypeService) ctx.getBean("competencyTypeService");
        CompetencyType ctype = competencyTypeService.getByPropertyName("compNameType", competencyType);
        cl.setComp_name(competencyName);
        cl.setNotes(competencyNotes);
        cl.setCompType(ctype);
        return cl;

    }

    public CompetencyList updateCompetencyList(String competencyName, String competencyNotes, String competencyType, Long competencyId) {
        CompetencyList cl = loadCompetencyList(competencyId);
        competencyTypeService = (CompetencyTypeService) ctx.getBean("competencyTypeService");
        CompetencyType ctype = competencyTypeService.getByPropertyName("compNameType", competencyType);
        cl.setComp_name(competencyName);
        cl.setNotes(competencyNotes);
        cl.setCompType(ctype);
        return cl;
    }

   
}
