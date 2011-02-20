/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.EmployeeMentoring;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class TrainingResource {
public abstract Collection<EmployeeMentoring> getTrainings();

    public abstract EmployeeMentoring getTraining(Long id);

    public abstract EmployeeMentoring getTraining(@Context Request request,RequestForm form);

    public abstract Response createTrainings(@Context Request request,TrainingForm form);

     public abstract Response updateTraining(@Context Request request,EmployeeMentoring training);
}
