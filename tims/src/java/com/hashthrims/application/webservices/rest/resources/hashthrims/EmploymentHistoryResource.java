/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmploymentHistoryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmploymentHistory;
import java.util.Collection;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class EmploymentHistoryResource {
public abstract Collection<EmploymentHistory> getEmploymentHistories();

    public abstract EmploymentHistory getEmploymentHistory(Long id);

    public abstract EmploymentHistory getEmploymentHistory(@Context Request request,RequestForm form);

    public abstract Response createEmploymentHistories(@Context Request request,EmploymentHistoryForm form);

     public abstract Response updateEmploymentHistory(@Context Request request,EmploymentHistory employmentHistory);
}
