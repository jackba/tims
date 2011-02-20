/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.util.MentoringUtil;
import com.hashthrims.domain.traininglist.MentoringSession;
import java.util.List;

/**
 *
 * @author stud
 */
public class MentoringSessionTable extends com.vaadin.ui.Table {

    private static final ClientDataService data = new ClientDataService();
    private HashThrimsMain main;
    private final MentoringUtil st = new MentoringUtil();

    public MentoringSessionTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Session Name", String.class, null);
        addContainerProperty("Session Type", String.class, null);
        addContainerProperty("Mentoring Field", String.class, null);
        addContainerProperty("Mentoring Theme", String.class, null);
        addContainerProperty("Status", String.class, null);
        addContainerProperty("Training Institution", String.class, null);
        addContainerProperty("Notes", String.class, null);

        // Add Data Columns
        List<MentoringSession> sessions = data.getMentoringSessionService().findAll();
        for (MentoringSession session : sessions) {
            addItem(new Object[]{session.getSessionName(),
                        st.getSessionTypeName(session.getMentoringSessionType()),
                        st.getMentoringField(session.getMentoringTheme()),
                        st.getMentoringTheme(session.getMentoringTheme()),
                        st.getStatus(session.getSessionStatus()),
                        st.getTrainingInstitution(session.getInstitutionName()),
                        session.getMentoringNotes()}, session.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
