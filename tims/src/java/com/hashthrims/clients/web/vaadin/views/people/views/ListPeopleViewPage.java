/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.tables.NewPersonTable;
import com.hashthrims.clients.web.vaadin.views.people.tables.PersonTable;
import com.hashthrims.domain.Person;


import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ListPeopleViewPage extends VerticalLayout
  {

    private HashThrimsMain main;
    private NewPersonTable table;
    private ClientDataService data = new ClientDataService();
    public ListPeopleViewPage(HashThrimsMain app) {
        main = app;
         List<Person> persons = data.getPersonService().findAll();
        table = new NewPersonTable(main,persons);
        addComponent(table);

    }

   
   
}
