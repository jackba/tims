/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.forms.position;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Runo;

/**
 *
 * @author boniface
 */
public class GlobalFacilityPositionsGridForm extends Form {

    private final GridLayout layout;

    public GlobalFacilityPositionsGridForm() {
        setCaption("Positions For Faclitities  Form");
        layout = new GridLayout(2, 3);
        layout.setMargin(true);
        layout.setSpacing(true);

        //layout.setSizeFull();
        //layout.setStyleName(Runo.LAYOUT_DARKER);
        layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
        final HorizontalLayout firstHeader = new HorizontalLayout();
        firstHeader.setSizeFull();
        firstHeader.setStyleName(Runo.LAYOUT_DARKER);
        final Label firstHeaderlabel = new Label("Facility Positions");
        firstHeaderlabel.setStyleName(Runo.LABEL_H2);
        firstHeader.addComponent(firstHeaderlabel);
        layout.addComponent(firstHeader, 0, 0, 1, 0);
        layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);

        final HorizontalLayout formFooter = new HorizontalLayout();
        formFooter.setSizeFull();
        formFooter.setStyleName(Runo.LAYOUT_DARKER);
        layout.addComponent(formFooter, 0, 2, 1, 2);
        setLayout(layout);

    }

    @Override
    protected void attachField(Object propertyId, Field field) {
        if (propertyId.equals("facilities")) {
            layout.addComponent(field, 0,1, 1,1);
        } 
    }
}
