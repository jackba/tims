/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RegistrationBodyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.offices.RegistrationBody;

import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class RegistrationBodyResource {
    public abstract Collection<RegistrationBody> getRegistrationBody();

    public abstract RegistrationBody getRegistrationBody(Long id);

    public abstract RegistrationBody getRegistrationBody(@Context Request request,RequestForm form);

    public abstract Response createRegistrationBody(@Context Request request,RegistrationBodyForm form);

     public abstract Response updateRegistrationBody(@Context Request request,RegistrationBody registrationBody);
}
