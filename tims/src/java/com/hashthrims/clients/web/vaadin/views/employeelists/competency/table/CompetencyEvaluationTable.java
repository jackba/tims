/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CompetencyEvaluationTable extends Table {

   private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public CompetencyEvaluationTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        addContainerProperty("Competency Evaluation", String.class, null);

        List<CompetencyEvaluation> competencyEvaluation = data.getCompetencyEvaluationService().findAll();
        for (CompetencyEvaluation compEvaluation : competencyEvaluation) {
            addItem(new Object[]{compEvaluation.getCompt_type_name()}, compEvaluation.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
