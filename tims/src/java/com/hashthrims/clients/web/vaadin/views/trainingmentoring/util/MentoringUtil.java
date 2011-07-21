/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.util;

import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.SessionType;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.TrainingInstitution;

/**
 *
 * @author boniface
 */
public class MentoringUtil {

    public String getMentoringField(MentoringTheme mentoringTheme) {
        if (mentoringTheme != null) {
            return getFieldName(mentoringTheme.getMentoringField());
        }

        return null;
    }

    public String getFieldName(MentoringField mentoringField) {
        if (mentoringField != null) {
            return mentoringField.getFieldName();
        }
        return null;
    }

    public String  getSessionTypeName(SessionType mentoringSessionType) {
        if(mentoringSessionType!=null)
            return mentoringSessionType.getSessionTypeName();
        return null;
    }

    public String  getMentoringTheme(MentoringTheme mentoringTheme) {
        if(mentoringTheme!=null)
            return mentoringTheme.getMentoringTheme();
        return null;
    }

    public String  getStatus(Status sessionStatus) {
        if(sessionStatus!=null)
            return sessionStatus.getStatus();
        return null;
    }

    public String  getTrainingInstitution(TrainingInstitution institutionName) {
        if(institutionName!=null)
            return institutionName.getTrainingInstitution();
        return null;
    }
}
