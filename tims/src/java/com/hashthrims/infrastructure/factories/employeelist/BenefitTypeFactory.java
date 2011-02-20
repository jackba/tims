/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.BenefitTypeService;
import org.springframework.context.ApplicationContext;


/**
 *
 * @author boniface
 */
public class BenefitTypeFactory {

    private BenefitTypeService benefitTypeService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public BenefitType createBenefitTypes(String benefitTypeName) {
        BenefitType e = new BenefitType();
        e.setBenefit_type_name(benefitTypeName);
        return e;
    }

    public BenefitType loadBenefitTypes(Long id) {
        benefitTypeService = (BenefitTypeService) ctx.getBean("benefitTypeService");
        BenefitType e = benefitTypeService.find(id);
        return e;
    }

   
}
