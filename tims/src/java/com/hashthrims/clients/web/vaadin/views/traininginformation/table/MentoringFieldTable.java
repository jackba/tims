/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import java.util.List;

/**
 *
 * @author stud
 */
public class MentoringFieldTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public MentoringFieldTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Mentoring Field", String.class, null);
        
        // Add Data Columns
        List<MentoringField> fields = data.getMentoringFieldService().findAll();
        for (MentoringField field : fields) {
            addItem(new Object[]{field.getFieldName()}, field.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
