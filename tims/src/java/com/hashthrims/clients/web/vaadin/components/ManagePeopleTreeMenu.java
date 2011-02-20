/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonViewPage;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class ManagePeopleTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object SEARCH_FOR_PERSON = "People";
    public static final Object ADD_NEW_PERSON = "People Management";
    public static final Object LIST_ALL_PEOPLE = "List All People";

    public ManagePeopleTreeMenu(HashThrimsMain app) {
        this.main = app;

        //add Items to Menu
        addItem(ADD_NEW_PERSON);
//        addItem(SEARCH_FOR_PERSON);
//        addItem(LIST_ALL_PEOPLE);

        //Add Listeners
        addListener((ItemClickListener) this);

    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (ADD_NEW_PERSON.equals(itemId)) {
                managePeopleView();
            } else if (SEARCH_FOR_PERSON.equals(itemId)) {
               // managePositionsView();
            } else if (LIST_ALL_PEOPLE.equals(itemId)) {
               // organisationListView();
            }
        }

    }



    private void managePeopleView() {
        main.mainView.setSecondComponent( new ManagePeopleMenuView(main, "SEARCH"));
    }

   
}
