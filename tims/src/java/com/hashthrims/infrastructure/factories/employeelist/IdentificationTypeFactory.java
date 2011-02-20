/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.IdentificationTypeService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class IdentificationTypeFactory {

    private IdentificationTypeService identificationTypeService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public IdentificationType createIdentificationType(String identificationTypeName) {
        IdentificationType c = new IdentificationType();
       c.setIdentity_name_type(identificationTypeName);
        return c;
    }

    public IdentificationType loadIdentificationType(Long id) {
        identificationTypeService = (IdentificationTypeService) ctx.getBean("identificationTypeService");
        IdentificationType c = identificationTypeService.find(id);
        return c;
    }

    
}
