/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.EducationTypeService;
import org.springframework.context.ApplicationContext;


/**
 *
 * @author boniface
 */
public class EducationTypeFactory {

    private EducationTypeService educationService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public EducationType createEducation(String educationTypeName) {
        EducationType e = new EducationType();
        e.setEduc_type_name(educationTypeName);


        return e;
    }

    public EducationType loadEducaction(Long id) {
        educationService = (EducationTypeService) ctx.getBean("educationTypeService");
        EducationType e = educationService.find(id);
        return e;
    }

    public EducationType updatedEducaction(String name, Long id) {
        EducationType et = loadEducaction(id);
        et.setEduc_type_name(name);
        return et;
    }

   
}
