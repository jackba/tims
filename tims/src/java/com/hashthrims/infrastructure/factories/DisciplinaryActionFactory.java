/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.DisciplinaryAction;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DisciplinaryActionService;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class DisciplinaryActionFactory {
private DisciplinaryActionService disciplinaryActionService;


    private ApplicationContext ctx = GetContext.getApplicationContext();


    public DisciplinaryAction createDisciplinaryAction(Date dateOfDiscussion, DisciplineActionTypeList disciplinaryActionTypeList, Date endOfApplicability, String notes, String peoplePresent, Date startOfApplicability){
        DisciplinaryAction c = new DisciplinaryAction();
        c.setDateOfDiscussion(dateOfDiscussion);
        c.setEmpDisciplinaryAction(disciplinaryActionTypeList);
        c.setEndofAplicability(endOfApplicability);
        c.setNotes(notes);
        c.setPeoplePresent(peoplePresent);
        c.setSatrtofAplicability(startOfApplicability);
        return c;
    }

    public DisciplinaryAction loadDisciplinaryAction(Long id) {
        disciplinaryActionService = (DisciplinaryActionService) ctx.getBean("disciplinaryactionService");
        DisciplinaryAction c = disciplinaryActionService.find(id);
        return c;
    }
}
