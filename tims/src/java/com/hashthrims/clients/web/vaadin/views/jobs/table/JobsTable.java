/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.Jobs;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class JobsTable extends Table {

    private static final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public JobsTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.

        addContainerProperty("Job Title", String.class, null);
        addContainerProperty("Job Code", String.class, null);
        addContainerProperty("Job Description", String.class, null);
        //addContainerProperty("Salary Grade", String.class, null);
        addContainerProperty("Classification", String.class, null);
        addContainerProperty("Cadre", String.class, null);

        // Add Data Columns
        List<Jobs> jobs = data.getJobService().findAll();
        for (Jobs job : jobs) {
            addItem(new Object[]{job.getJob_tittle(),
                                 job.getJob_code(),
                                 job.getJob_desc(), 
                                 job.getClassications().getJob_name(),
                                 job.getCadres().getCadres_name()}, job.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
