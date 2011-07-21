/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.forms.SearchPersonForm;
import com.hashthrims.clients.web.vaadin.views.people.models.SearchPersonBean;
import com.hashthrims.clients.web.vaadin.views.people.tables.NewPersonTable;
import com.hashthrims.clients.web.vaadin.views.people.tables.PersonTable;
import com.hashthrims.clients.web.vaadin.views.people.views.details.search.SearchForPeople;
import com.hashthrims.domain.Person;

import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class SearchForPersonViewPage extends VerticalLayout implements
        ClickListener {

    private HashThrimsMain main;
    private Form form;
    private SearchPersonForm pform;
    private static ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();

    public SearchForPersonViewPage(HashThrimsMain app, NewPersonTable table, List<Person> personLists) {
        main = app;
        setSizeFull();
        pform = new SearchPersonForm();
        form = pform.createSearchPersonForm();
        // Add Listeners
        pform.getSearch().addListener((ClickListener) this);
        pform.getAdd().addListener((ClickListener) this);
        SearchPersonBean bean = new SearchPersonBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);

        if (table != null) {
            table = new NewPersonTable(main, personLists);
            addComponent(table);
        } else {
            List<Person> p = data.getPersonService().findAll();
            table = new NewPersonTable(main, p);
            addComponent(table);
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSearch()) {
            List<Person> persons = searchForPerson(form);
            main.mainView.setSecondComponent(new PersonsReultsPageView(main, "SEARCH", persons));
        } else if (source == pform.getAdd()) {
            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
        }
    }

    private List<Person>  searchForPerson(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        String firstName = fieldValues.getStringFields(form.getField("firstName").getValue());
        String lastName = fieldValues.getStringFields(form.getField("lastName").getValue());
        Long facility = fieldValues.getLongFields(form.getField("facility").getValue());
        List<Person> persons = new SearchForPeople().results(firstName, lastName, facility);
        return persons;
    }
}
