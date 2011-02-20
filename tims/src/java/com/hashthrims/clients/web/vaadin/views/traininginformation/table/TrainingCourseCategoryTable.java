/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import java.util.List;

/**
 *
 * @author stud
 */
public class TrainingCourseCategoryTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public TrainingCourseCategoryTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Training Field", String.class, null);
        
        // Add Data Columns
        List<TrainingCourseCategory> categories = data.getTrainingCourseCategoryService().findAll();
        for (TrainingCourseCategory category : categories) {
            addItem(new Object[]{category.getCategoryName()}, category.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
