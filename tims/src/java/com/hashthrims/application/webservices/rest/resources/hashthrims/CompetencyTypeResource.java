/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.CompetencyType;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CompetencyTypeResource {
    public abstract Collection<CompetencyType> getCompetencyTypes();

    public abstract CompetencyType getCompetencyType(Long id);

    public abstract CompetencyType getCompetencyType(@Context Request request,RequestForm form);

    public abstract Response createCompetencyType(@Context Request request,CompetencyTypeForm form);

     public abstract Response updateCompetencyType(@Context Request request,CompetencyType competencyType);
}
