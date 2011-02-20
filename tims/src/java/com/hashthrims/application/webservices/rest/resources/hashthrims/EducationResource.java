/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.Education;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class EducationResource {
public abstract Collection<Education> getEducations();

    public abstract Education getEducation(Long id);

    public abstract Education getEducation(@Context Request request,RequestForm form);

    public abstract Response createEducations(@Context Request request,EducationForm form);

     public abstract Response updateEducation(@Context Request request,Education education);
}
