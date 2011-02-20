/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.EducationHistory;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class EducationHistoryResource {
public abstract Collection<EducationHistory> getEducationHistories();

    public abstract EducationHistory getEducationHistory(Long id);

    public abstract EducationHistory getEducationHistory(@Context Request request,RequestForm form);

    public abstract Response createEducationHistories(@Context Request request,EducationHistoryForm form);

     public abstract Response updateEducationHistory(@Context Request request,EducationHistory educationHistory);
}
