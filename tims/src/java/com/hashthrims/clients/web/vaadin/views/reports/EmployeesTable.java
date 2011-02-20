/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.reports;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Employee;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EmployeesTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public EmployeesTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Surname", String.class, null);
        addContainerProperty("Position", String.class, null);
        addContainerProperty("Contacts", String.class, null);
        addContainerProperty("Benefits", String.class, null);
        addContainerProperty("Comptency", String.class, null);
       
        // Add Data Columns
        List<Employee> employees = data.getEmployeeService().findAll();
        for (Employee employee : employees) {
            //addItem(new Object[]{employee.getPerson().getPersonName(), employee.getPerson().getPersonSurname(), employee.getPosition(),employee.getContacts().getTelephoneNumber(),employee.getBenefits(),employee.getCompetency()}, employee.getId());
        }



        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setStyleName(ALIGN_LEFT);


    }
}
