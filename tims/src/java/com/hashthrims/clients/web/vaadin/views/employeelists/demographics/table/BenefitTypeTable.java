/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.BenefitType;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class BenefitTypeTable extends Table {

    private ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public BenefitTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Benefit Type", String.class, null);

        // Add Data Columns
        List<BenefitType> grades = data.getBenefitTypeService().findAll();
        for (BenefitType grade : grades) {
            addItem(new Object[]{grade.getBenefit_type_name()}, grade.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
