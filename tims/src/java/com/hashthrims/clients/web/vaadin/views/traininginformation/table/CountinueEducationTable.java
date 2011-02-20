/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import java.util.List;

/**
 *
 * @author stud
 */
public class CountinueEducationTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CountinueEducationTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Countinue Course Education", String.class, null);
        addContainerProperty("Credit Hours", String.class, null);
        // Add Data Columns
        List<ContiuingEducationCourse> continueCourses = data.getContinuingEducationCourseService().findAll();
        for (ContiuingEducationCourse course : continueCourses) {
            addItem(new Object[]{course.getNameOfContinueCourse(),course.getCreditHours()}, course.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
