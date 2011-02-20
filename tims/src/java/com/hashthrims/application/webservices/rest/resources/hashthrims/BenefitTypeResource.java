/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.BenefitTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.BenefitType;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class BenefitTypeResource {
    public abstract Collection<BenefitType> getBenefitType();

    public abstract BenefitType getBenefitType(Long id);

    public abstract BenefitType getBenefitType(@Context Request request,RequestForm form);

    public abstract Response createBenefitType(@Context Request request,BenefitTypeForm form);

     public abstract Response updateBenefitType(@Context Request request,BenefitType benefitType);
}
