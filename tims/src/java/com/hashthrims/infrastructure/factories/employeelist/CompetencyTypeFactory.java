/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CompetencyTypeService;
import com.hashthrims.services.IdentificationTypeService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class CompetencyTypeFactory {

    private CompetencyTypeService competencyTypeService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public CompetencyType createCompetencyType(String competencyTypeName) {
        CompetencyType e = new CompetencyType();
        e.setComp_name_typ(competencyTypeName);
        return e;
    }

    public CompetencyType loadCompetencyType(Long id) {
        competencyTypeService = (CompetencyTypeService) ctx.getBean("competencyTypeService");
        CompetencyType c = competencyTypeService.find(id);
        return c;
    }

     public CompetencyType updatedCompetencyType(String name, Long id) {
        CompetencyType c = loadCompetencyType(id);
        c.setComp_name_typ(name);

        return c;
    }
}
