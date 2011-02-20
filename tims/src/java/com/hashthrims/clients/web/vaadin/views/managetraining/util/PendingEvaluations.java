/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.util;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PendingEvaluations {

    private final ClientDataService data = new ClientDataService();

    public String getPendingEvaluations() {
        int sum = 0;
        List<EmployeeCourses> courses = data.getEmployeeCourseService().findAll();
        for (EmployeeCourses employeeCourses : courses) {
            if (hasPendingEvaluation(employeeCourses)) {
                sum++;
            }

        }

        return Integer.toString(sum);
    }

    private boolean hasPendingEvaluation(EmployeeCourses employeeCourses) {
        if (employeeCourses != null) {
            return noPendingEvalaution(employeeCourses.getEvaluation());
        }
        return false;
    }

    private boolean noPendingEvalaution(CompetencyEvaluation evaluation) {
        if (evaluation != null) {
            return false;
        }
        return true;

    }
}
