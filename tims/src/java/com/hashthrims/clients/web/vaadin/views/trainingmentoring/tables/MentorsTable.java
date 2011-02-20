/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.Mentors;
import com.vaadin.ui.*;
import java.util.List;
/**
 *
 * @author stud
 */
public class MentorsTable extends Table{
  private static final ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public MentorsTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Title", String.class, null);
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("Qualification", String.class, null);

        // Add Data Columns
        List<Mentors> mentors = data.getMentorsService().findAll();
        for (Mentors mentor : mentors) {
            addItem(new Object[]{mentor.getTitle(),
                                 mentor.getFirstName(),
                                 mentor.getLastName(),
                                 mentor.getQualification()
                                                        }, mentor.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
