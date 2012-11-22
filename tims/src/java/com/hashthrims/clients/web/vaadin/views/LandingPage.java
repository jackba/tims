/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class LandingPage extends VerticalLayout {

    private HashThrimsMain main;
    private Form form;
    private HorizontalLayout footer;
    private final GridLayout grid;
    private final Label header = new Label(" <font color=red><H1><CENTER>The System Snapshot Statistics</CENTER> </H1> </font>", Label.CONTENT_XHTML);
    private final Label underline = new Label(" <hr/>", Label.CONTENT_XHTML);
    private final Label people = new Label(" <font color=green><H1><CENTER><u>People</u></CENTER> </H1> </font>", Label.CONTENT_XHTML);
    private final Label courses = new Label(" <font color=green><H1><CENTER><u>Courses</u></CENTER> </H1> </font>", Label.CONTENT_XHTML);
    private final Label totalPeople = new Label(" <font color=blue><H3>Number of People on the System: </H3> </font>", Label.CONTENT_XHTML);
    private final Label totalCourses = new Label(" <font color=blue><H3>Number of Courses on the System: </H3> </font>", Label.CONTENT_XHTML);
    private final Label male = new Label(" <font color=blue><H3>Number of Courses on the System: </H3> </font>", Label.CONTENT_XHTML);
    private final Label female = new Label(" <font color=blue><H3>Number of Courses on the System: </H3> </font>", Label.CONTENT_XHTML);
    private final Label scheduled = new Label(" <font color=blue><H3>Number of Courses on the System: </H3> </font>", Label.CONTENT_XHTML);
    private final Label vacancies = new Label(" <font color=blue><H3>Number of Courses on the System: </H3> </font>", Label.CONTENT_XHTML);
    private final LandingPageData landingPageData = new LandingPageData();

    public LandingPage(HashThrimsMain app) {


        grid = new GridLayout(4, 10);
        grid.setSizeFull();



        grid.addComponent(landingPageData.getHeader(), 0, 0, 3, 0);
        grid.addComponent(landingPageData.getLine(), 0, 1, 3, 1);

        grid.addComponent(landingPageData.getPeopleLabel(), 0, 2, 1, 2);
//        
        grid.addComponent(totalPeople, 0, 3);
        grid.addComponent(new Label("<h3>1223</h3>", Label.CONTENT_XHTML), 1, 3);

        grid.addComponent(male, 0, 4);
        grid.addComponent(new Label("<h3>1223</h3>", Label.CONTENT_XHTML), 1, 4);

        grid.addComponent(female, 0, 5);
        grid.addComponent(new Label("<h3>1223</h3>", Label.CONTENT_XHTML), 1, 5);

        // Courses Grid Side
        grid.addComponent(landingPageData.getCourseLabel(), 2, 2, 3, 2);
//        
        grid.addComponent(totalCourses, 2, 3);
        grid.addComponent(new Label("<h3>33</h3>", Label.CONTENT_XHTML), 3, 3);

        grid.addComponent(scheduled, 2, 4);
        grid.addComponent(new Label("<h3>33</h3>", Label.CONTENT_XHTML), 3, 4);

        grid.addComponent(vacancies, 2, 5);
        grid.addComponent(new Label("<h3>33</h3>", Label.CONTENT_XHTML), 3, 5);


        main = app;
        setSizeFull();
        setMargin(true);
        form = new Form();
        form.setImmediate(true);
        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.setVisible(true);
        footer.setMargin(true);

        addComponent(grid);
        setComponentAlignment(grid, Alignment.MIDDLE_CENTER);


        addComponent(footer);
    }
}
