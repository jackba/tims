/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PositionChangeForm extends Form {

    private Button save = new Button("Change Position");
    private Button cancel = new Button("Cancel");
    private final HorizontalLayout buttons = new HorizontalLayout();
    private final ClientDataService data = new ClientDataService();
    private GridLayout layout;
    private final TimsUtil st = new TimsUtil();

    public PositionChangeForm() {
        setCaption("Change Postion Form");
        layout = new GridLayout(7, 2);
        layout.setMargin(true);
        layout.setSpacing(true);
        buttons.addComponent(save);
        buttons.addComponent(cancel);
        layout.addComponent(buttons, 0, 1, 1, 1);

//        setFormFieldFactory(new EmployeePositionFieldFactory());
        setLayout(layout);
    }

    @Override
    protected void attachField(Object propertyId, Field field) {

        if (propertyId.equals("position")) {
            layout.addComponent(field, 1, 1);
        } else if (propertyId.equals("status")) {
            layout.addComponent(field, 0, 3);
        } else if (propertyId.equals("startDate")) {
            layout.addComponent(field, 0, 2);
        } else if (propertyId.equals("enddate")) {
            layout.addComponent(field, 1, 2);
        } else if (propertyId.equals("facility")) {
            layout.addComponent(field, 0, 1);
        } else if (propertyId.equals("id")) {
            layout.addComponent(field, 1, 3);
        }
    }

    
}
