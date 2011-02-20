/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.WorkPlaceAccidents;
import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.WorkPlaceAccidentsService;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class WorkPlaceAccidentsFactory {
private WorkPlaceAccidentsService workPlaceAccidentsService;


    private ApplicationContext ctx = GetContext.getApplicationContext();

    public WorkPlaceAccidents createWorkPlaceAccidents(AccidentTypeList accidentTypeList, Date endOfApplicability, String followUpRequired, Date dateOfOccurence, String peopleInvolved, Date begginningOfApplicability){
        workPlaceAccidentsService = (WorkPlaceAccidentsService) ctx.getBean("workplaceaccidentsService");
        WorkPlaceAccidents c = new WorkPlaceAccidents();
        c.setAccidentType(accidentTypeList);
        c.setEndAplicability(endOfApplicability);
        c.setFollowuopRequired(followUpRequired);
        c.setOccurence(dateOfOccurence);
        c.setPeopleInvioved(peopleInvolved);
        c.setStratAplicability(begginningOfApplicability);
        workPlaceAccidentsService.persist(c);
        return c;
    }

    public AccidentTypeList createAccidentTypeList(String accidentName){
        AccidentTypeList a = new AccidentTypeList();
        a.setAccidentName(accidentName);
        return a;
    }

    public WorkPlaceAccidents updateAccidentTypeList(String accidentName, Long id){
        workPlaceAccidentsService = (WorkPlaceAccidentsService) ctx.getBean("workplaceaccidentsService");
        WorkPlaceAccidents w = workPlaceAccidentsService.find(id);
        w.getAccidentType().setAccidentName(accidentName);
        workPlaceAccidentsService.merge(w);
        return w;
    }

    public WorkPlaceAccidents updateWorkPlaceAccidents(AccidentTypeList accidentTypeList, Date endOfApplicability, String followUpRequired, Date dateOfOccurence, String peopleInvolved, Date begginningOfApplicability, Long id){
        workPlaceAccidentsService = (WorkPlaceAccidentsService) ctx.getBean("workplaceaccidentsService");
        WorkPlaceAccidents w = workPlaceAccidentsService.find(id);
        w.setAccidentType(accidentTypeList);
        w.setEndAplicability(endOfApplicability);
        w.setFollowuopRequired(followUpRequired);
        w.setOccurence(dateOfOccurence);
        w.setPeopleInvioved(peopleInvolved);
        w.setStratAplicability(endOfApplicability);
        workPlaceAccidentsService.merge(w);
        return w;
    }

    public WorkPlaceAccidents loadWorkPlaceAccidents(Long id) {
        workPlaceAccidentsService = (WorkPlaceAccidentsService) ctx.getBean("workplaceaccidentsService");
        WorkPlaceAccidents c = workPlaceAccidentsService.find(id);
        return c;
    }
}
