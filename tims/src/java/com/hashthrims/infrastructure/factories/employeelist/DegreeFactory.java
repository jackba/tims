/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DegreeService;
import com.hashthrims.services.EducationTypeService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class DegreeFactory {

    private DegreeService degreeService;
    private EducationTypeService educationTypeService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public Degree createDegree(String degreeName, String edType) {
        educationTypeService = (EducationTypeService) ctx.getBean("educationTypeService");
        EducationType educationType = educationTypeService.getByPropertyName("educTypeName", edType);
        Degree d = new Degree();
        d.setDegree_name(degreeName);
        d.setEducType(educationType);
        return d;
    }

    public EducationType createEducationType(String educationTypeName) {
        EducationType e = new EducationType();
        e.setEduc_type_name(educationTypeName);
        return e;
    }

    public Degree loadDegree(Long id) {
        degreeService = (DegreeService) ctx.getBean("degreeService");
        Degree d = degreeService.find(id);
        return d;
    }

    public EducationType loadEducationType(Long id) {
        educationTypeService = (EducationTypeService) ctx.getBean("educationTypeService");
        EducationType d = educationTypeService.find(id);
        return d;
    }

    public Degree updatedDegree(String name, String eduType, Long id) {
        Degree c = loadDegree(id);
         educationTypeService = (EducationTypeService) ctx.getBean("educationTypeService");
        EducationType cdr = educationTypeService.getByPropertyName("educTypeName", eduType);
        c.setDegree_name(name);
        c.setEducType(cdr);
        return c;
    }
}
