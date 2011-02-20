/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.Language;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class LanguageTable extends Table {

   private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public LanguageTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        addContainerProperty("Languages", String.class, null);

        List<Language> language = data.getLanguageService().findAll();
        for (Language languages : language) {
            addItem(new Object[]{languages.getLanguage_name()}, languages.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
