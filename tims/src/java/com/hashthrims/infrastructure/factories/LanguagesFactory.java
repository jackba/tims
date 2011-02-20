/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.LanguageService;
import com.hashthrims.services.EmployeeLanguagesService;
import java.util.Date;
import java.util.Map;
import org.springframework.context.ApplicationContext;
/**
 *
 * @author abismail
 */
public class LanguagesFactory {
    private EmployeeLanguagesService employeeLanguagesService;
    private LanguageService languageService;


    private final ApplicationContext ctx = GetContext.getApplicationContext();
    private final ClientDataService data = new ClientDataService();



    public EmployeeLanguages createLanguages(Language l, Map<String,String> vals){
        EmployeeLanguages langs = new EmployeeLanguages();
        langs.setLanguage(l);
        langs.setReading(vals.get("reading"));//i think an enum should be created for reading/speaking/writing because they should only be able to hold a preset amount of values
        langs.setSpeaking(vals.get("speaking"));
        langs.setWriting(vals.get("writing"));
        return langs;
    }

public Language createLanguage(String language_name){
        Language lang = new Language();
        lang.setLanguage_name("English");
        return lang;
    }

    public EmployeeLanguages loadLanguages(Long id) {
        employeeLanguagesService = (EmployeeLanguagesService) ctx.getBean("employeeLanguagesService");
        EmployeeLanguages c = employeeLanguagesService.find(id);
        return c;
    }

    public Language loadLanguage(Long id) {
        languageService = (LanguageService) ctx.getBean("languageService");
        Language c = languageService.find(id);
        return c;
    }

    public EmployeeLanguages updateLanguages(Language lang, Map<String, String> vals, Long id) {
        EmployeeLanguages langs = loadLanguages(id);
        langs.setLanguage(lang);
        langs.setReading(vals.get("reading"));//i think an enum should be created for reading/speaking/writing because they should only be able to hold a preset amount of values
        langs.setSpeaking(vals.get("speaking"));
        langs.setWriting(vals.get("writing"));
        return langs;
    }

    public ProfessionalRegistration createProfessionalRegistration(RegistrationBody registrationBody, Map<String, String> vals, Date registrationDate, Date expirationDate) {
        final ProfessionalRegistration pr = new ProfessionalRegistration();
        pr.setExpirationDate(expirationDate);
        pr.setLicenceNumber(vals.get("licenceNumber"));
        pr.setRegistrationBody(registrationBody);
        pr.setRegistrationDate(registrationDate);
        pr.setRegistrationNumber(vals.get("registrationNumber"));
        
        return pr;
    }

    public ProfessionalRegistration updateProfessionalRegistration(RegistrationBody registrationBody, Map<String, String> vals, Date registrationDate, Date expirationDate, Long id) {
        final ProfessionalRegistration pr = loadProfessionalRegistration(id);
        pr.setExpirationDate(expirationDate);
        pr.setLicenceNumber(vals.get("licenceNumber"));
        pr.setRegistrationBody(registrationBody);
        pr.setRegistrationDate(registrationDate);
        pr.setRegistrationNumber(vals.get("registrationNumber"));

        return pr;
    }

    private ProfessionalRegistration loadProfessionalRegistration(Long id) {
       ProfessionalRegistration pr = data.getProfessionalRegistrationService().find(id);
       return pr;
    }

}
