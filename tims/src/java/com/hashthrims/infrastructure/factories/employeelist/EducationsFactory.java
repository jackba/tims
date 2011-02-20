/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Education;
import com.hashthrims.domain.EducationHistory;
import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DegreeService;
import com.hashthrims.services.EducationService;
import com.hashthrims.services.EducationTypeService;
import com.hashthrims.services.RegistrationBodyService;
import java.util.Date;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class EducationsFactory {

    private EducationTypeService educationTypeService;
    private DegreeService degreeService;
    private RegistrationBodyService registrationBodyService;
    private EducationService educationService;
    private ApplicationContext ctx = GetContext.getApplicationContext();
    private final  ClientDataService data = new ClientDataService();

    public EducationType createTypeEducation(String educationTypeName) {
        EducationType e = new EducationType();
        e.setEduc_type_name(educationTypeName);


        return e;
    }
    //TODO: the education class must be completed for this factory and ultimately the GUI to be complete

    public Education createEducation(String institution) {
        Education c = new Education();
        c.setInstitution(institution);
        return c;
    }

    public Education loadEducation(Long id) {
        educationService = (EducationService) ctx.getBean("educationService");
        Education c = educationService.find(id);
        return c;
    }

    public EducationType loadEducaction(Long id) {
        educationTypeService = (EducationTypeService) ctx.getBean("educationTypeService");
        EducationType e = educationTypeService.find(id);
        return e;
    }

    public Degree createDegree(String degree, EducationType educType) {
        Degree e = new Degree();
        e.setDegree_name(degree);
        e.setEducType(educType);


        return e;
    }

    public Degree loadDegree(Long id) {
        degreeService = (DegreeService) ctx.getBean("degreeService");
        Degree e = degreeService.find(id);
        return e;
    }

    public RegistrationBody createRegistrationBody(String council) {
        RegistrationBody e = new RegistrationBody();
        e.setName(council);


        return e;
    }

    public RegistrationBody loadRegistrationBody(Long id) {
        registrationBodyService = (RegistrationBodyService) ctx.getBean("registrationBodyService");
        RegistrationBody e = registrationBodyService.find(id);
        return e;
    }

    public EducationHistory createEducationHistory(Long country, Map<String, String> vals, Date graduationDate, Long degreeType) {
        EducationHistory educationHistory = new EducationHistory();
        Country coun = data.getCountryService().find(country);

        Degree degree = data.getDegreeName().find(degreeType);

        educationHistory.setDegreeType(degree);
        educationHistory.setGraduation(graduationDate);
        educationHistory.setInstituteNamwe(vals.get("instituteName"));
        educationHistory.setMajor(vals.get("major"));
        educationHistory.setLocation(coun);

        return educationHistory;

    }

    public EducationHistory updateEducationHistory(Long country, Map<String, String> vals, Date graduationDate, Long degreeType, Long id) {
        EducationHistory educationHistory = loadEducationHistory(id);
        Country coun = data.getCountryService().find(country);

        Degree degree = data.getDegreeName().find(degreeType);

        educationHistory.setDegreeType(degree);
        educationHistory.setGraduation(graduationDate);
        educationHistory.setInstituteNamwe(vals.get("instituteName"));
        educationHistory.setMajor(vals.get("major"));
        educationHistory.setLocation(coun);

        return educationHistory;

    }

    private EducationHistory loadEducationHistory(Long id) {
        EducationHistory educationHistory = data.getEducationHistoryService().find(id);
        return educationHistory;
    }
}
