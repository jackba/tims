/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin;

import com.hashthrims.clients.web.vaadin.components.TabAccordian;
import com.hashthrims.clients.web.vaadin.views.LandingPage;
import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;




import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author boniface
 */
public class HashThrimsMain extends Application {

    public HorizontalSplitPanel mainView = new HorizontalSplitPanel();
    private final TabAccordian accordian = new TabAccordian(this);
    private final LandingPage dashBoard = new LandingPage(this);

    @Override
    public void init() {
        setTheme("reindeer");

        //Set Root Layout
        final VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();
        rootLayout.setMargin(false);


        final Window root = new Window("Training Human Resource Information Systems", rootLayout);
        //root.setStyleName("tTunes");

        //Set Header
        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setMargin(false, true, false, true);
        top.setSpacing(true);
        top.setStyleName(Reindeer.LAYOUT_BLUE);
        final Embedded rtcLogo = new Embedded("", new ThemeResource("images/logo.png"));
        rtcLogo.setHeight("90px");
        //rtcLogo.set
        top.addComponent(rtcLogo);
        top.setComponentAlignment(rtcLogo, Alignment.TOP_LEFT);
        top.setExpandRatio(rtcLogo, 1);

        final Embedded wsuLogo = new Embedded("", new ThemeResource("images/wsu.png"));
        top.addComponent(wsuLogo);
        top.setComponentAlignment(wsuLogo, Alignment.TOP_RIGHT);
        top.setExpandRatio(wsuLogo, 1);

        


       

        top.setHeight("100px"); // Same as the background image height

        root.addComponent(top);
        root.addComponent(top);


        mainView.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        mainView.setFirstComponent(accordian);
        mainView.setSecondComponent(dashBoard);
        root.addComponent(mainView);
        ((VerticalLayout) root.getContent()).setExpandRatio(mainView, 1.0F);
        setMainWindow(root);


    }
}
