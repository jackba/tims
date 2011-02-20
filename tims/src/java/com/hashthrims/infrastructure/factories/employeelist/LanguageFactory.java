/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.LanguageService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class LanguageFactory {

    private LanguageService languageService;
      
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public Language createLanguage(String LanguageName) {
        Language c = new Language();
         c.setLanguage_name(LanguageName);
        return c;
    }

    public Language loadLanguage(Long id) {
        languageService = (LanguageService) ctx.getBean("languageService");
        Language c = languageService.find(id);
        return c;
    }
     public Language updatedLanguage(String name, Long id) {
        Language l = loadLanguage(id);
        l.setLanguage_name(name);
        return l;
    }

    
}
