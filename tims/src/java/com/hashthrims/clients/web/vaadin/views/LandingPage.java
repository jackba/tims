/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 *
 * @author boniface
 */
public class LandingPage extends VerticalLayout{
       private HashThrimsMain main;
       private Form form;
       private HorizontalLayout footer;
      // private Label message = new Label("To get started using iHRIS Manage, please click one of the options below. Return to this page at any time by clicking the Home button in the bar above. If you need help with any function, click the Help button. We encourage you to send us any errors you find, suggestions for improvements or additional features, and any other feedback you may have about this software; contact us at any time by clicking the Feedback button or visiting the HRIS Strengthening Website. ");

       public LandingPage(HashThrimsMain app) {

         Embedded pic = new Embedded();
         pic.setHeight("300px");
         pic.setWidth("1000px");
         pic.setIcon(new ThemeResource("images/calender.png"));
       
        Panel p = new Panel("Welcome To iHRIS Manage");
//        Label msg = new Label("THIS IS A LANDING PAGE iHRIS Manage is a human resources management tool that enables an organization to design and manage a comprehensive human resources strategy.iHRIS Manage helps an organization manage its workforce more effectively and efficiently, while reducing costs and data errors.Using the system, the HR professional can create a hierarchy of positions for an organization based on standard titles, job classifications and job descriptions, even spread over diverse geographic locations, offices and facilities. HR staff can solicit job applications for open positions, assign employees to fill positions and maintain a searchable database of all employees, their identifying information and their qualifications. Managers can track each employee's history with the organization, including their position and salary histories, and record the reason for departure when the employee leaves.");
//        Label image = new Label();
        
        p.setHeight("400px");
       // msg.setHeight("300px");
        main = app;
        setSizeFull();
        form = new Form();
        form.setImmediate(true);


        footer = new HorizontalLayout();
        footer.setSpacing(true);
        //footer.addComponent(message);
        footer.setVisible(true);
        footer.setMargin(true);
      
      //  p.addComponent(msg);
       // p.setIcon(new ThemeResource("images/layout/hr.jpg"));
 
        addComponent(p);
        footer.addComponent(pic);
        addComponent(footer);
 }
}
