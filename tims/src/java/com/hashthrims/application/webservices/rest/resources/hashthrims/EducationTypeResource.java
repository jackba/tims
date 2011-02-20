/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.EducationType;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class EducationTypeResource {
    public abstract Collection<EducationType> getEducationTypes();

    public abstract EducationType getEducationType(Long id);

    public abstract EducationType getEducationType(@Context Request request,RequestForm form);

    public abstract Response createEducationType(@Context Request request,EducationTypeForm form);

     public abstract Response updateEducationType(@Context Request request,EducationType educationType);
}
