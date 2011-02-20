/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import java.util.List;

/**
 *
 * @author stud
 */
public class TrainingCourseEvaluationTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public TrainingCourseEvaluationTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Evaluation Name", String.class, null);
      
        // Add Data Columns
        List<TrainingCourseEvaluation> trainingCourseEvaluations = data.getTrainingCourseEvaluationService().findAll();
        for (TrainingCourseEvaluation trainingCourseEvaluation : trainingCourseEvaluations) {
            addItem(new Object[]{trainingCourseEvaluation.getEvaluationName()}, trainingCourseEvaluation.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
