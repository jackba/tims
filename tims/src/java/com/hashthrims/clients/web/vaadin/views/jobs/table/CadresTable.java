/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.Cadres;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CadresTable extends Table {

    private static final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public CadresTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        addContainerProperty("Cadre Name", String.class, null);

        List<Cadres> cadre = data.getCadresService().findAll();
        for (Cadres cadres : cadre) {
            addItem(new Object[]{cadres.getCadres_name()}, cadres.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
