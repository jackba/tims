/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.facilites.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Department;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DepartmentTable extends Table {

    private final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public DepartmentTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Department Name", String.class, null);
      
        // Add Data Columns
        final List<Department> departments = data.getDepartmentService().findAll();
        for (Department department : departments) {
            if (department.getDeptName()!=null) {
                addItem(new Object[]{department.getDeptName()}, department.getId());
            } else {
                 addItem(new Object[]{department.getDeptName(), "NEW VALUE"}, department.getId());
            }
        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
