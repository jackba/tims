/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.BenefitFrequencyService;
import org.springframework.context.ApplicationContext;


/**
 *
 * @author boniface
 */
public class BenefitFrequencyFactory {

    private BenefitFrequencyService benefitFrequencyService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public BenefitFrequency createBenefitFrequency(String benefitFrequencyName) {
        BenefitFrequency b = new BenefitFrequency();
             b.setFrequency(benefitFrequencyName);
          return b;
    }

    public BenefitFrequency loadBenefitFrequency(Long id) {
        benefitFrequencyService = (BenefitFrequencyService) ctx.getBean("benefitFrequencyService");
        BenefitFrequency e = benefitFrequencyService.find(id);
        return e;
    }

   
}
