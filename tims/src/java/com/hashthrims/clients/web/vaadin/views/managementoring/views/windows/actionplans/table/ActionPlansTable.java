/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.views.windows.actionplans.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeActionPlan;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import java.util.List;

/**
 *
 * @author stud
 */
public class ActionPlansTable extends com.vaadin.ui.Table {

    private static ClientDataService data = new ClientDataService();
    private final DataFieldsUtil date = new DataFieldsUtil();
    private HashThrimsMain main;

    public ActionPlansTable(HashThrimsMain app, Person p) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Date", String.class, null);
        addContainerProperty("Action Plan", String.class, null);
        addContainerProperty("Status", String.class, null);
        addContainerProperty("Review", String.class, null);
        addContainerProperty("Review Date", String.class, null);


        // Add Data Columns
        List<EmployeeActionPlan> fields = p.getActionPlans();
        for (EmployeeActionPlan field : fields) {
            addItem(new Object[]{
                        date.getDFormat(field.getActionPlanDate()),
                        field.getActionPlan(),
                        field.getStatus(),
                        field.getActionPlanreview(),
                        date.getDFormat(field.getReviewPlanDate())
                    }, field.getId());
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
