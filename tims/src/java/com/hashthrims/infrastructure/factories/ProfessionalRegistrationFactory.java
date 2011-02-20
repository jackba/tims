/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.ProfessionalRegistrationService;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class ProfessionalRegistrationFactory {
private ProfessionalRegistrationService professionalRegistrationService;


private ApplicationContext ctx = GetContext.getApplicationContext();

    public ProfessionalRegistration createProfessionalRegistration(Date expirationDate, String licenseNumber, RegistrationBody registrationBody, Date registrationDate, String registrationNumber){
        ProfessionalRegistration c = new ProfessionalRegistration();
        c.setExpirationDate(expirationDate);
        c.setLicenceNumber(licenseNumber);
        c.setRegistrationBody(registrationBody);
        c.setRegistrationDate(registrationDate);
        c.setRegistrationNumber(registrationNumber);
        return c;
    }

    public ProfessionalRegistration loadProfessionalRegistration(Long id) {
        professionalRegistrationService = (ProfessionalRegistrationService) ctx.getBean("professionalregistrationService");
        ProfessionalRegistration c = professionalRegistrationService.find(id);
        return c;
    }
}
