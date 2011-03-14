/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin;

import com.hashthrims.clients.web.vaadin.components.Header;
import com.hashthrims.clients.web.vaadin.components.TabAccordian;
import com.hashthrims.clients.web.vaadin.views.LandingPage;
import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;




import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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
        Header header = new Header(this);

        root.addComponent(header.getHeader());

        mainView.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        mainView.setFirstComponent(accordian);
        mainView.setSecondComponent(dashBoard);
        root.addComponent(mainView);
        ((VerticalLayout) root.getContent()).setExpandRatio(mainView, 1.0F);
        setMainWindow(root);


    }
}
