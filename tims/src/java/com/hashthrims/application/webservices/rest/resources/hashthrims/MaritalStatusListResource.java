/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.MaritalStatusListForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.MaritalStatusList;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class MaritalStatusListResource {
    public abstract Collection<MaritalStatusList> getMaritalStatusLists();

    public abstract MaritalStatusList getMaritalStatusList(Long id);

    public abstract MaritalStatusList getMaritalStatusList(@Context Request request,RequestForm form);

    public abstract Response createMaritalStatusList(@Context Request request,MaritalStatusListForm form);

     public abstract Response updateMaritalStatusList(@Context Request request,MaritalStatusList maritalStatusList);
}
