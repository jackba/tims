/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyListForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.CompetencyList;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CompetencyListResource {
    public abstract Collection<CompetencyList> getCompetencyLists();

    public abstract CompetencyList getCompetencyList(Long id);

    public abstract CompetencyList getCompetencyList(@Context Request request,RequestForm form);

    public abstract Response createCompetencyList(@Context Request request,CompetencyListForm form);

     public abstract Response updateCompetencyList(@Context Request request,CompetencyList competencyList);
}
