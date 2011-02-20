/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists;


import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.CompetencyMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.DemographicsMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.IncidentsMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.QualificationsMenuView;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class EmployeeListMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;
    public static String demoSate = "";
    public static String qualSate = "";
    public static String compSate = "";
    public static String inciSate = "";

    public EmployeeListMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout demographicsTab = new VerticalLayout();
        demographicsTab.setMargin(true);
        demographicsTab.addComponent(new DemographicsMenuView(main,demoSate));


        VerticalLayout qualificationTab = new VerticalLayout();
        qualificationTab.setMargin(true);
        qualificationTab.addComponent(new QualificationsMenuView(main,qualSate));

        VerticalLayout competencyTab = new VerticalLayout();
        competencyTab.setMargin(true);
        competencyTab.addComponent(new CompetencyMenuView(main,compSate));

        VerticalLayout IncidentsTab = new VerticalLayout();
        IncidentsTab.setMargin(true);
        IncidentsTab.addComponent(new IncidentsMenuView(main,inciSate));

       
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(demographicsTab, "Demographics", null);
        tab.addTab(qualificationTab, "Qualifications", null);
        tab.addTab(competencyTab, "Competency", null);
       //N/A tab.addTab(IncidentsTab, "Incidents", null);

        //Qualification tab selection
        if(EmployeeListMenuView.qualSate.equals("EDUCATION TYPE")){
            tab.setSelectedTab(qualificationTab);
        } else if(EmployeeListMenuView.qualSate.equals("DEGREE")){
            tab.setSelectedTab(qualificationTab);
        } else if(EmployeeListMenuView.qualSate.equals("LANGUAGE")){
            tab.setSelectedTab(qualificationTab);
        }
        //Demographic tab selection
        if(EmployeeListMenuView.demoSate.equals("MARITAL STATUS")){
            tab.setSelectedTab(demographicsTab);
        } else if(EmployeeListMenuView.demoSate.equals("BENEFIT TYPE")){
            tab.setSelectedTab(demographicsTab);
        } else if(EmployeeListMenuView.demoSate.equals("IDENTIFICATION TYPE")){
            tab.setSelectedTab(demographicsTab);
        }
        else if(EmployeeListMenuView.demoSate.equals("GENDER LIST")){
            tab.setSelectedTab(demographicsTab);
        }
        //Competency tab selection
        if(EmployeeListMenuView.compSate.equals("COMPETENCY TYPE")){
            tab.setSelectedTab(competencyTab);
        } else if(EmployeeListMenuView.compSate.equals("COMPETENCY")){
            tab.setSelectedTab(competencyTab);
        } else if(EmployeeListMenuView.compSate.equals("COMPETENCY EVALUATION")){
            tab.setSelectedTab(competencyTab);
        }
        //Incident tab selection
        if(EmployeeListMenuView.inciSate.equals("ACCIDENT TYPE LIST")){
            tab.setSelectedTab(IncidentsTab);
        } else if(EmployeeListMenuView.inciSate.equals("DEPARTURE REASONS")){
            tab.setSelectedTab(IncidentsTab);
        } else if(EmployeeListMenuView.inciSate.equals("DISCIPLINARY ACTION TYPE")){
            tab.setSelectedTab(IncidentsTab);
        }


        EmployeeListMenuView.demoSate = "";
        EmployeeListMenuView.compSate = "";
        EmployeeListMenuView.qualSate = "";
        EmployeeListMenuView.inciSate = "";
        addComponent(tab);
    }
 }
