/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views.BenefitTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views.GenderListViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views.IdentificationTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views.MaritalStatusViewPage;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views.RaceListViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class DemographicsMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public DemographicsMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout maritalStatusTab = new VerticalLayout();
        maritalStatusTab.setMargin(true);
        maritalStatusTab.addComponent(new MaritalStatusViewPage(main));

        final VerticalLayout identificationTypeTab = new VerticalLayout();
        identificationTypeTab.setMargin(true);
        identificationTypeTab.addComponent(new IdentificationTypeViewPage(main));

        final VerticalLayout benefitTypeTab = new VerticalLayout();
        benefitTypeTab.setMargin(true);
        benefitTypeTab.addComponent(new BenefitTypeViewPage(main));

        final VerticalLayout genderListTab = new VerticalLayout();
        genderListTab.setMargin(true);
        genderListTab.addComponent(new GenderListViewPage(main));

        final VerticalLayout raceListTab = new VerticalLayout();
        raceListTab.setMargin(true);
        raceListTab.addComponent(new RaceListViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");


        // tab.addTab(maritalStatusTab, "Marital Status", null);
        tab.addTab(identificationTypeTab, "Identification Type", null);
        // tab.addTab(benefitTypeTab, "Benefit Type", null);
        tab.addTab(genderListTab, "Gender", null);
        tab.addTab(raceListTab, "Race", null);


        if (selectedTab.equals("IDENTIFICATION TYPE")) {
            tab.setSelectedTab(identificationTypeTab);
        } else if (selectedTab.equals("BENEFIT TYPE")) {
            tab.setSelectedTab(benefitTypeTab);
        } else if (selectedTab.equals("MARITAL STATUS")) {
            tab.setSelectedTab(maritalStatusTab);
        } else if (selectedTab.equals("GENDER LIST")) {
            tab.setSelectedTab(genderListTab);
        } else if (selectedTab.equals("RACE LIST")) {
            tab.setSelectedTab(raceListTab);
        }
        addComponent(tab);
    }
}
