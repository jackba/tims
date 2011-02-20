/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring;

import com.hashthrims.clients.web.vaadin.views.location.views.ProvinceViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.CountryViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.CountyViewPage;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class ManageMentoringMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManageMentoringMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        VerticalLayout countryTab = new VerticalLayout();
        countryTab.setMargin(true);
        countryTab.addComponent(new CountryViewPage(main));

        VerticalLayout provinceTab = new VerticalLayout();
        provinceTab.setMargin(true);
        provinceTab.addComponent(new ProvinceViewPage(main));

        VerticalLayout countyTab = new VerticalLayout();
        countyTab.setMargin(true);
        countyTab.addComponent(new CountyViewPage(main));





        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(countryTab, "Schedule Mentoring Session", null);
        tab.addTab(provinceTab, "Assign Evaluations", null);
        tab.addTab(countyTab, "Assign Mentors", null);
        
        
        if (selectedTab.equals("COUNTRY")) {
            tab.setSelectedTab(countryTab);
        } else if (selectedTab.equals("PROVINCE")) {
            tab.setSelectedTab(provinceTab);
        } else if (selectedTab.equals("COUNTY")) {
            tab.setSelectedTab(countyTab);
        }
        addComponent(tab);
    }
}
