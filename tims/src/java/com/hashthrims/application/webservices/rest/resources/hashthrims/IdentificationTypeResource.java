/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.IdentificationTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.IdentificationType;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class IdentificationTypeResource {
    public abstract Collection<IdentificationType> getIdentificationTypes();

    public abstract IdentificationType getIdentificationType(Long id);

    public abstract IdentificationType getIdentificationType(@Context Request request,RequestForm form);

    public abstract Response createIdentificationType(@Context Request request,IdentificationTypeForm form);

     public abstract Response updateIdentificationType(@Context Request request,IdentificationType identificationType);
}
