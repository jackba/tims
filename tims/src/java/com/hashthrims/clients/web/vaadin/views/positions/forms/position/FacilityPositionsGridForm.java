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
public class FacilityPositionsGridForm extends Form {

    private final GridLayout layout;

    public FacilityPositionsGridForm() {
        setCaption("Position Form");
        layout = new GridLayout(2, 6);
        layout.setMargin(true);
        layout.setSpacing(true);

        //layout.setSizeFull();
        //layout.setStyleName(Runo.LAYOUT_DARKER);
        layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
        final HorizontalLayout firstHeader = new HorizontalLayout();
        firstHeader.setSizeFull();
        firstHeader.setStyleName(Runo.LAYOUT_DARKER);
        final Label firstHeaderlabel = new Label("Facilities  Position");
        firstHeaderlabel.setStyleName(Runo.LABEL_H2);
        firstHeader.addComponent(firstHeaderlabel);
        layout.addComponent(firstHeader, 0, 0, 1, 0);
        layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);

        final HorizontalLayout formFooter = new HorizontalLayout();
        formFooter.setSizeFull();
        formFooter.setStyleName(Runo.LAYOUT_DARKER);
        layout.addComponent(formFooter, 0, 5, 1, 5);

        setLayout(layout);

    }

    @Override
    protected void attachField(Object propertyId, Field field) {


        if (propertyId.equals("job")) {
            layout.addComponent(field, 0, 1);
        } else if (propertyId.equals("positionCode")) {
            layout.addComponent(field, 1, 1);
        } else if (propertyId.equals("positionType")) {
            layout.addComponent(field, 0,2);
        } else if (propertyId.equals("supervisor")) {
            layout.addComponent(field, 1, 2);
        } else if (propertyId.equals("department")) {
            layout.addComponent(field, 0, 3);
        } else if (propertyId.equals("positionStatus")) {
            layout.addComponent(field, 1,3);
        } else if (propertyId.equals("facilities")) {
            layout.addComponent(field, 0,4, 1,4);
        }
    }
}
