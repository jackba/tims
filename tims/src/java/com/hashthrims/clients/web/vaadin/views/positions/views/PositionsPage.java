/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * THIS IS S A USELESS DAMY CLASS
 * @author boniface
 */
public class PositionsPage extends VerticalLayout{

    public PositionsPage() {
        
        // First a vertical SplitPanel
        final VerticalSplitPanel vert = new VerticalSplitPanel();
        vert.setHeight("600px");
        vert.setWidth("100%");
        vert.setSplitPosition(150, Sizeable.UNITS_PIXELS);
        addComponent(vert);

        // add a label to the upper area
        vert.setFirstComponent(new Label(" HERE IS TTHE CONTENT FOR TOP PANEL "));
        vert.setSecondComponent(new Button("Now we are talking "));
//        setSplitPosition(200, Sizeable.UNITS_PIXELS);
//        setFirstComponent(new Button("top Component"));
//        setSecondComponent(new Button("This is Lower Button"));
        

    }
    
}
