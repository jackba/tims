/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.DetailsGrid;
import com.hashthrims.domain.Person;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class PersonDetailsView extends VerticalLayout implements Button.ClickListener {

    private final HorizontalLayout menu = new HorizontalLayout();
    private final Button newPerson = new Button("Add New Person");
    private final Button search = new Button("Search For Person");
    private final Button back = new Button("Go Back");
    private HashThrimsMain main;

    public PersonDetailsView(Person person, HashThrimsMain app,String selectedTab) {
        main=app;
        final  GridLayout layout = DetailsGrid.detailsGrid(person,main,selectedTab);
        menu.addComponent(newPerson);
        menu.addComponent(back);
        menu.addComponent(search);
        addComponent(menu);
        addComponent(layout);
        //Register Listeners
        newPerson.addListener((Button.ClickListener)this);
        search.addListener((Button.ClickListener)this);
        back.addListener((Button.ClickListener)this);
    }

    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source==newPerson) {
           main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
        } else if(source==search) {
         main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
        } else if(source==back) {
         main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
        }
    }
}
