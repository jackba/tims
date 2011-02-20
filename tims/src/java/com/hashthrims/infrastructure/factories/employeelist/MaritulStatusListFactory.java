/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.MaritalStatusListService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class MaritulStatusListFactory {

    private MaritalStatusListService maritalStatusListServiceService;
   
    
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public MaritalStatusList createMaritalStatusList(String maritalStatusListService) {
        MaritalStatusList c = new MaritalStatusList();
        c.setStatus_name(maritalStatusListService);       
        return c;
    }

    public MaritalStatusList updateMaritalStatusList(String maritalStatusListService, Long id) {
        maritalStatusListServiceService = (MaritalStatusListService) ctx.getBean("maritalStatusListServiceService");
        MaritalStatusList c = maritalStatusListServiceService.find(id);
        c.setStatus_name(maritalStatusListService);
        maritalStatusListServiceService.merge(c);
        return c;
    }

    public MaritalStatusList loadMaritalStatusList(Long id) {
        maritalStatusListServiceService = (MaritalStatusListService) ctx.getBean("maritalStatusListServiceService");
        MaritalStatusList c = maritalStatusListServiceService.find(id);
        return c;
    }

  
}
