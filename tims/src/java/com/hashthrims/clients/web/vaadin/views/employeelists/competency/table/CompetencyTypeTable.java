/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CompetencyTypeTable extends Table {

  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CompetencyTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();


        // Define the names and data types of columns.
        addContainerProperty("Subject Area", String.class, null);
        

        // Add Data Columns
        List<CompetencyType> comptType = data.getCompetencyTypeService().findAll();
        for (CompetencyType competencyType : comptType) {
            addItem(new Object[]{competencyType.getComp_name_typ()}, competencyType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
