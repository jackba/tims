/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.training.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import com.vaadin.ui.*;
import java.util.List;
/**
 *
 * @author stud
 */
public class CourseStatusTable extends Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CourseStatusTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Course Status", String.class, null);
       
        // Add Data Columns
        List<TrainingCourseStatus> trainingCourseStatus = data.getTrainingCourseStatusType().findAll();
        for (TrainingCourseStatus status : trainingCourseStatus) {
            addItem(new Object[]{status.getStatusName()}, status.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
