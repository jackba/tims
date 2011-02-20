/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingFunderForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingFunder;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingFunderResource {
    public abstract Collection<TrainingFunder> getTrainingFunderes();

    public abstract TrainingFunder getTrainingFunder(Long id);

    public abstract TrainingFunder getTrainingFunder(@Context Request request,RequestForm form);

    public abstract Response createTrainingFunders(@Context Request request,TrainingFunderForm form);

     public abstract Response updateTrainingFunder(@Context Request request,TrainingFunder trainingFunder);
}
