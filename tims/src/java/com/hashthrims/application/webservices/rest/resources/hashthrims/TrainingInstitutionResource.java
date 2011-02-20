/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingInstitutionForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingInstitutionResource {
    public abstract Collection<TrainingInstitution> getTrainingInstitutiones();

    public abstract TrainingInstitution getTrainingInstitution(Long id);

    public abstract TrainingInstitution getTrainingInstitution(@Context Request request,RequestForm form);

    public abstract Response createTrainingInstitutions(@Context Request request,TrainingInstitutionForm form);

     public abstract Response updateTrainingInstitution(@Context Request request,TrainingInstitution trainingInstitution);
}
