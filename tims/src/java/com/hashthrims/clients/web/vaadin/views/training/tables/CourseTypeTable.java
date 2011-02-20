/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.training.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.vaadin.ui.*;
import java.util.List;
/**
 *
 * @author stud
 */
public class CourseTypeTable extends Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CourseTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Course Type Name", String.class, null);

        // Add Data Columns
        List<CourseTypeName> courseTypeNames = data.getCourseTypeNameService().findAll();
        for (CourseTypeName courseType : courseTypeNames) {
            addItem(new Object[]{courseType.getCourseType()}, courseType.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
