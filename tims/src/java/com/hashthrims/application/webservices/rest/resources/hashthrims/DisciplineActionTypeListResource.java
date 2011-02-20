/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DisciplineActionTypeListForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class DisciplineActionTypeListResource {
    public abstract Collection<DisciplineActionTypeList> getDisciplineActionTypeLists();

    public abstract DisciplineActionTypeList getDisciplineActionTypeList(Long id);

    public abstract DisciplineActionTypeList getDisciplineActionTypeList(@Context Request request,RequestForm form);

    public abstract Response createDisciplineActionTypeList(@Context Request request,DisciplineActionTypeListForm form);

     public abstract Response updateDisciplineActionTypeList(@Context Request request,DisciplineActionTypeList disciplineActionTypeList);
}
