/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.people.views.details.demographics;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.domain.Person;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.GridLayout.OutOfBoundsException;
import com.vaadin.ui.GridLayout.OverlapsException;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DemographicsGrid extends VerticalLayout{
    private HashThrimsMain main;

    public VerticalLayout getDemographics(Person person, HashThrimsMain app) throws OverlapsException, OutOfBoundsException {
        GridLayout layout = new GridLayout(1,6);
        layout.setSizeFull();

        main=app;
        //-----------Individual Section heading
        Label individualInforLabel = new Label("Individual Information");
        individualInforLabel.addStyleName(Reindeer.LABEL_H2);
      
        layout.addComponent(individualInforLabel, 0, 1);
        // Table Headers First
        HorizontalLayout toolbar = new HorizontalLayout();
       
        layout.addComponent(toolbar, 0, 2);
        // Line header
        Label linebreak1 = new Label("<hr />", Label.CONTENT_XHTML);
        layout.addComponent(linebreak1, 0, 3);
        //Add the Table Here
        DemographicsTable tbl = new DemographicsTable();
        List<GridLayout> tbls = tbl.gemographicsTable(person,main);
        for (GridLayout demographicsTable : tbls) {
            layout.addComponent(demographicsTable, 0, 4);
        }
        //Close off with  Line
        layout.addComponent(new Label("<hr />", Label.CONTENT_XHTML), 0, 5);
       addComponent(layout);
       addComponent(toolbar);
        return this;
    }

}
