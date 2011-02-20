/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DisciplineActionTypeListService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class DisciplineActionListFactory {

    private DisciplineActionTypeListService disciplineActionService;
    
    
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public DisciplineActionTypeList createDisciplineActionList(String disciplineName) {
        DisciplineActionTypeList c = new DisciplineActionTypeList();
        c.setDisplineName(disciplineName);
      
        return c;
    }

    public DisciplineActionTypeList loadDisciplinaryActionService(Long id) {
        disciplineActionService = (DisciplineActionTypeListService) ctx.getBean("disciplineActionService");
        DisciplineActionTypeList d = disciplineActionService.find(id);
        return d;
    }


}
