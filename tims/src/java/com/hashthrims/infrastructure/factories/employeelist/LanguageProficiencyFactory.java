/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.LanguageProficiency;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.LangauageProficiencyService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class LanguageProficiencyFactory {

    private LangauageProficiencyService languageProficiencyService;
   
    
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public LanguageProficiency createLangauageProficiency(String langauageProficiencyServiceName) {
        LanguageProficiency c = new LanguageProficiency();
        c.setProficency(langauageProficiencyServiceName);
        return c;
    }

    public LanguageProficiency loadLangauageProficiency(Long id) {
        languageProficiencyService = (LangauageProficiencyService) ctx.getBean("languageProficiencyService");
        LanguageProficiency c = languageProficiencyService.find(id);
        return c;
    }

  
}
