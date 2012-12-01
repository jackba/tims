/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.SessionType;
import com.vaadin.ui.*;
import java.util.List;

/**
 *
 * @author stud
 */
public class MentoringSessionTypeTable extends Table {

    private static final ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public MentoringSessionTypeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Tools and Methods", String.class, null);


        // Add Data Columns
        List<SessionType> sessions = data.getMentoringSessionTypeService().findAll();
        for (SessionType session : sessions) {
            addItem(new Object[]{session.getSessionTypeName()
                    }, session.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
