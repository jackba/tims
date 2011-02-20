/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;

import com.vaadin.terminal.ThemeResource;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class StatusMenuView extends VerticalLayout implements ClickListener {

    private HashThrimsMain main;
    private Button status = new Button("Add Status");
    private GridLayout layout;

    public StatusMenuView(HashThrimsMain app) {
        main= app;
        layout = new GridLayout(1, 1);
        layout.setSizeFull();

        //Register Events
        status.addListener((ClickListener) this);
        // Add Images to the Buttons
        status.setIcon(new ThemeResource("icons/64/globe.png"));
        // Layout The Buttons
         layout.addComponent(status, 0, 0);
        // Add the layout to the page
        addComponent(layout);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == status) {
            showLanguage();
        }
    
   
}
 private void showLanguage() {
          main.mainView.setSecondComponent(new StatusViewPage(main));
    }

}
