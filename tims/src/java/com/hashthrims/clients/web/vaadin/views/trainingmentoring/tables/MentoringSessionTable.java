/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.util.MentoringUtil;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.MentoringSession;
import java.util.Date;
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
        addContainerProperty("Session Date", Date.class, null);
        addContainerProperty("Training Institution", String.class, null);
        addContainerProperty("Mentoring venue", String.class, null);
        
       
     

        // Add Data Columns
        List<MentoringSession> sessions = data.getMentoringSessionService().findAll();
        for (MentoringSession session : sessions) {
            addItem(new Object[]{session.getSessionName(),
                        session.getSessionDate(),
                        st.getTrainingInstitution(session.getInstitutionName()),
                        getMentoringVenue(session.getMentoringVenue())}, session.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    private Object getMentoringVenue(Facility mentoringVenue) {
        if(mentoringVenue!=null)
            return mentoringVenue.getFacilityName();
        return null;
    }
}
