/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.EmployeeLanguages;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class LanguagesResource {
public abstract Collection<EmployeeLanguages> getLanguageses();

    public abstract EmployeeLanguages getLanguages(Long id);

    public abstract EmployeeLanguages getLanguages(@Context Request request,RequestForm form);

    public abstract Response createLanguageses(@Context Request request,LanguagesForm form);

     public abstract Response updateLanguages(@Context Request request,EmployeeLanguages languages);
}
