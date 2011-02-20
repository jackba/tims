/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.ProfessionalRegistration;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class ProfessionalRegistrationResource {
public abstract Collection<ProfessionalRegistration> getProfessionalRegistrations();

    public abstract ProfessionalRegistration getProfessionalRegistration(Long id);

    public abstract ProfessionalRegistration getProfessionalRegistration(@Context Request request,RequestForm form);

    public abstract Response createProfessionalRegistrations(@Context Request request,ProfessionalRegistrationForm form);

     public abstract Response updateProfessionalRegistration(@Context Request request,ProfessionalRegistration professionalRegistration);
}
