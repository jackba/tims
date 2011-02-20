/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.DisciplinaryAction;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class DisciplinaryActionResource {
public abstract Collection<DisciplinaryAction> getDisciplinaryActions();

    public abstract DisciplinaryAction getDisciplinaryAction(Long id);

    public abstract DisciplinaryAction getDisciplinaryAction(@Context Request request,RequestForm form);

    public abstract Response createDisciplinaryActions(@Context Request request,DisciplinaryActionForm form);

     public abstract Response updateDisciplinaryAction(@Context Request request,DisciplinaryAction disciplinaryAction);
}
