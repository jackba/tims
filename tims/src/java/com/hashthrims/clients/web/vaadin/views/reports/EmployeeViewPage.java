/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.reports;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.location.*;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class EmployeeViewPage extends Form {
    Panel p = new Panel(" Employee reports ");
  private HashThrimsMain main;
    private EmployeesTable table;
    private VerticalLayout layout = new VerticalLayout();
    public EmployeeViewPage(HashThrimsMain app) {
       table = new EmployeesTable(main);
        this.main =app;
        setLayout(layout);
        layout.addComponent(table);
    }

}
