/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PositionTypesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.positions.PositionTypes;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class PositionTypesResource {
    public abstract Collection<PositionTypes> getPositionTypess();

    public abstract PositionTypes getPositionTypes(Long id);

    public abstract PositionTypes getPositionTypes(@Context Request request,RequestForm form);

    public abstract Response createPositionTypes(@Context Request request,PositionTypesForm form);

     public abstract Response updatePositionTypes(@Context Request request,PositionTypes positionTypes);
}
