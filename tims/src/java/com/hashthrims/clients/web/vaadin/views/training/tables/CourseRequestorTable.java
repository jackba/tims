/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.training.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.vaadin.ui.*;
import java.util.List;
/**
 *
 * @author stud
 */
public class CourseRequestorTable extends Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CourseRequestorTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Course Requestor", String.class, null);
       
        // Add Data Columns
        List<TrainingCourseRequestors> trainingCourseRequestors = data.getTrainingCourseRequestorsType().findAll();
        for (TrainingCourseRequestors requestor : trainingCourseRequestors) {
            addItem(new Object[]{requestor.getRequestorName()}, requestor.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
