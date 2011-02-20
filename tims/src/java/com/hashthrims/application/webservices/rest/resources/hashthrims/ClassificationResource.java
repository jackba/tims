/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ClassificationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.jobs.Classifications;

import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class ClassificationResource {
    public abstract Collection<Classifications> getClassification();

    public abstract Classifications getClassification(Long id);

    public abstract Classifications getClassification(@Context Request request,RequestForm form);

    public abstract Response createClassification(@Context Request request,ClassificationForm form);

     public abstract Response updateClassification(@Context Request request,Classifications classification);
}
