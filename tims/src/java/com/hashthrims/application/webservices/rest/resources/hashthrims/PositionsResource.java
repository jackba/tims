/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PositionsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.positions.Positions;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class PositionsResource {
    public abstract Collection<Positions> getPositionss();

    public abstract Positions getPositions(Long id);

    public abstract Positions getPositions(@Context Request request,RequestForm form);

    public abstract Response createPositions(@Context Request request,PositionsForm form);

     public abstract Response updatePositions(@Context Request request,Positions positions);
}
