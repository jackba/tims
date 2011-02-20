/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location;

import com.hashthrims.clients.web.vaadin.views.location.views.ProvinceViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.CountryViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.CityViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.CountyViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.DistrictViewPage;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.location.views.AddressTypeViewPage;
import com.hashthrims.clients.web.vaadin.views.location.views.CurrencyViewPage;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class LocationMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public LocationMenuView(HashThrimsMain app, String selectedTab) {
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

        VerticalLayout districtTab = new VerticalLayout();
        districtTab.setMargin(true);
        districtTab.addComponent(new DistrictViewPage(app));

        VerticalLayout cityTab = new VerticalLayout();
        cityTab.setMargin(true);
        cityTab.addComponent(new CityViewPage(main));

        VerticalLayout currencyTab = new VerticalLayout();
        currencyTab.setMargin(true);
        currencyTab.addComponent(new CurrencyViewPage(main));

        VerticalLayout addressTypeTab = new VerticalLayout();
        addressTypeTab.setMargin(true);
        addressTypeTab.addComponent(new AddressTypeViewPage(main));



        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(countryTab, "Country", null);
        tab.addTab(provinceTab, "Province", null);
        tab.addTab(countyTab, "District", null);
        tab.addTab(districtTab, "Sub District", null);
        tab.addTab(cityTab, "City", null);
       // tab.addTab(currencyTab, "Currency", null);
        tab.addTab(addressTypeTab, "Address Type", null);
        if (selectedTab.equals("COUNTRY")) {
            tab.setSelectedTab(countryTab);
        } else if (selectedTab.equals("PROVINCE")) {
            tab.setSelectedTab(provinceTab);
        } else if (selectedTab.equals("COUNTY")) {
            tab.setSelectedTab(countyTab);
        } else if (selectedTab.equals("DISTRICT")) {
            tab.setSelectedTab(districtTab);
        } else if (selectedTab.equals("CITY")) {
            tab.setSelectedTab(cityTab);
        } else if (selectedTab.equals("CURRENCY")) {
            tab.setSelectedTab(currencyTab);
        } else if (selectedTab.equals("ADDRESS")) {
            tab.setSelectedTab(addressTypeTab);
        }
        addComponent(tab);
    }
}
