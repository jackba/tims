/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.BenefitFrequencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.BenefitFrequency;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class BenefitFrequencyResource {
    public abstract Collection<BenefitFrequency> getBenefitFrequency();

    public abstract BenefitFrequency getBenefitFrequencies(Long id);

    public abstract BenefitFrequency getBenefitFrequencies(@Context Request request,RequestForm form);

    public abstract Response createBenefitFrequencies(@Context Request request,BenefitFrequencyForm form);

     public abstract Response updateBenefitFrequencies(@Context Request request,BenefitFrequency benefitFrequency);
}
