/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CompetencyListTable extends Table {

    private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;
    private final TimsUtil st = new TimsUtil();

    public CompetencyListTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Competency Name", String.class, null);
        addContainerProperty("Subject Area", String.class, null);
        addContainerProperty("Competency Notes", String.class, null);
        // Add Data Columns
        final List<CompetencyList> competencies = data.getCompetencyList().findAll();
        
        for (CompetencyList competence : competencies) {
            final String comType = st.getVal(competence.getCompType());
            addItem(new Object[]{competence.getComp_name(),
                        comType,
                        competence.getNotes()}, competence.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
