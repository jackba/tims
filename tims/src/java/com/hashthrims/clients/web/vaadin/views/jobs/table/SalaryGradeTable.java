/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class SalaryGradeTable extends Table {

    private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public SalaryGradeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Grade Name", String.class, null);
        addContainerProperty("Start Amount", String.class, null);
        addContainerProperty("End Amount", String.class, null);
        addContainerProperty("Mid Amount", String.class, null);
        addContainerProperty("Current Amount", String.class, null);
        addContainerProperty("Currency", String.class, null);
        addContainerProperty("Notes", String.class, null);

        // Add Data Columns
        List<SalaryGrade> grades = data.getsalaryGradesService().findAll();
        for (SalaryGrade grade : grades) {
            addItem(new Object[]{grade.getGradeName(), grade.getStartAmount(),grade.getEndAmount(),grade.getMidAmount(), grade.getCurrentAmount(), grade.getCurrency().getCurrencySymbol(), grade.getNotes() }, grade.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
