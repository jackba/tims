/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class LandingPage extends VerticalLayout {

    private HashThrimsMain main;
    private Form form;
    private HorizontalLayout footer;

    public LandingPage(HashThrimsMain app) {
        


        final Embedded rtcpic = new Embedded("", new ThemeResource("rtcimages/trainingImage.jpg"));


        Panel p = new Panel("Welcome To The Training and Management Information System( TIMS)");


        p.setHeight("400px");
        p.addComponent(rtcpic);



        main = app;
        setSizeFull();
        setMargin(true);
        form = new Form();
        form.setImmediate(true);
        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.setVisible(true);
        footer.setMargin(true);

        addComponent(p);
        setComponentAlignment(p, Alignment.MIDDLE_CENTER);


        addComponent(footer);
    }
}
