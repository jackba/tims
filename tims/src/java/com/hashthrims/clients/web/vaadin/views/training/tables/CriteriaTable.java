/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.training.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.Criteria;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import java.util.List;

/**
 *
 * @author stud
 */
public class CriteriaTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CriteriaTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Selection Criteria", String.class, null);
        
        // Add Data Columns
        List<Criteria> criterias = data.getCriteriaService().findAll();
        for (Criteria criteria : criterias) {
            addItem(new Object[]{criteria.getSelectionCriteria()}, criteria.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
