/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.tables.PersonTable;
import com.hashthrims.clients.web.vaadin.views.people.views.ListPeopleViewPage;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonViewPage;
import com.hashthrims.clients.web.vaadin.views.people.views.SearchForPersonViewPage;
import com.hashthrims.clients.web.vaadin.views.people.views.SearchInFacilityViewPage;
import com.hashthrims.domain.Person;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GrimmTech
 */
public class ManagePeopleMenuView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public ManagePeopleMenuView(HashThrimsMain app, String selectedTab) {
        main = app;

        final VerticalLayout newPersonTab = new VerticalLayout();
        newPersonTab.setMargin(true);
        newPersonTab.addComponent(new PersonViewPage(main));
        
        final VerticalLayout searchTab = new VerticalLayout();
        searchTab.setMargin(true);
        PersonTable table = null;
        List<Person> personLists=new ArrayList<Person>();
        searchTab.addComponent(new SearchForPersonViewPage(main,table,personLists));

        final VerticalLayout searchByFacilityTab = new VerticalLayout();
        searchByFacilityTab.setMargin(true);
        searchByFacilityTab.addComponent(new SearchInFacilityViewPage(main));

        final VerticalLayout listPeapleTab = new VerticalLayout();
        listPeapleTab.setMargin(true);
        listPeapleTab.addComponent(new ListPeopleViewPage(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(searchTab, "Search For Person", null);
        tab.addTab(newPersonTab, "New Person", null);
        //tab.addTab(searchByFacilityTab, "Search by Town", null);
        tab.addTab(listPeapleTab, "List People", null);
        
        
        
        if (selectedTab.equals("LIST")) {
            tab.setSelectedTab(listPeapleTab);
        //} else if (selectedTab.equals("FACILITY")) {
        //    tab.setSelectedTab(searchByFacilityTab);
        } else if (selectedTab.equals("NEW")) {
            tab.setSelectedTab(newPersonTab);
        }else if (selectedTab.equals("SEARCH")) {
            tab.setSelectedTab(searchTab);
        }

        addComponent(tab);

    }
}
