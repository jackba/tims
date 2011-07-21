/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.tables.NewPersonTable;
import com.hashthrims.clients.web.vaadin.views.people.tables.PersonTable;
import com.hashthrims.domain.Person;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author GrimmTech
 */
public class PersonsReultsPageView extends VerticalLayout {

    private HashThrimsMain main;
    private TabSheet tab;

    public PersonsReultsPageView(HashThrimsMain app, String selectedTab, List<Person> personLists) {
        main = app;
       

        final VerticalLayout newPersonTab = new VerticalLayout();
        newPersonTab.setMargin(true);
        newPersonTab.addComponent(new PersonViewPage(main));
        
        final VerticalLayout searchTab = new VerticalLayout();
        searchTab.setMargin(true);
        NewPersonTable table = new NewPersonTable(app, personLists);
               
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
        tab.addTab(listPeapleTab, "List People", null);
        if (selectedTab.equals("LIST")) {
            tab.setSelectedTab(listPeapleTab);
        } else if (selectedTab.equals("NEW")) {
            tab.setSelectedTab(newPersonTab);
        }else if (selectedTab.equals("SEARCH")) {
            tab.setSelectedTab(searchTab);
        }

        addComponent(tab);

    }
}
