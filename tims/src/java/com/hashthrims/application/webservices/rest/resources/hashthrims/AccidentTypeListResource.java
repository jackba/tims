/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.AccidentTypeListForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.AccidentTypeList;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author bulelani
 */
public abstract class AccidentTypeListResource {
    public abstract Collection<AccidentTypeList> getAccidentTypeList();

    public abstract AccidentTypeList getAccidentTypeList(Long id);

    public abstract AccidentTypeList getAccidentTypeList(@Context Request request,RequestForm form);

    public abstract Response createAccidentTypeList(@Context Request request,AccidentTypeListForm form);

     public abstract Response updateAccidentTypeList(@Context Request request,AccidentTypeList accidentType);
}
