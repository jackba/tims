/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.training.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TargetGroup;
import java.util.List;

/**
 *
 * @author stud
 */
public class TargetGroupTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public TargetGroupTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Target Group", String.class, null);
        
        // Add Data Columns
        List<TargetGroup> targetGroups = data.getTargetGroupService().findAll();
        for (TargetGroup targetGroup : targetGroups) {
            addItem(new Object[]{targetGroup.getTargetGroupName()}, targetGroup.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
