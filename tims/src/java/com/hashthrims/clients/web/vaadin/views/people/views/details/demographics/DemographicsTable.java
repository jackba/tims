/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.demographics;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.forms.PersonEditForm;
import com.hashthrims.clients.web.vaadin.views.people.models.PersonBean;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class DemographicsTable implements ClickListener {

    private Person person;
    private GridLayout table;
    private List<GridLayout> tables;
    private Button edit, delete;
    private HashThrimsMain main;
    private static ClientDataService data = new ClientDataService();
    private TimsUtil st = new TimsUtil();

    public List<GridLayout> gemographicsTable(Person person, HashThrimsMain app) {
        main = app;
        this.person = person;
        //Create Table and Style it
        table = new GridLayout(3, 10);
        table.setSizeFull();

        //Create Arrays for tables;
        tables = new ArrayList<GridLayout>();


        //Set Table Headers Information
        Label editInfo = new Label("Manage Information");
        editInfo.addStyleName(Reindeer.LABEL_H2);
        table.addComponent(editInfo, 0, 0);


        Label personInformation = new Label("Person Information");
        personInformation.addStyleName(Reindeer.LABEL_H2);
        table.addComponent(personInformation, 1, 0, 2, 0);

        //Header Seperators
        table.addComponent(new Label("<hr />", Label.CONTENT_XHTML), 0, 1, 2, 1);

        edit = new Button("Update This Information");
        edit.addStyleName(Reindeer.BUTTON_LINK);
        delete = new Button("Delete This Information");
        delete.addStyleName("link");
        table.addComponent(edit, 0, 2);
       // Disabled Delete Information table.addComponent(delete, 0, 3);

        edit.addListener((ClickListener) (this));
        delete.addListener((ClickListener) this);

        table.addComponent(new Label("First Name: "), 1, 2);
        Label firstname = new Label(person.getPersonName());
        firstname.addStyleName(Reindeer.LABEL_SMALL);
   
        table.addComponent(firstname, 2, 2);

        table.addComponent(new Label("Surname: "), 1, 3);
        Label surname = new Label(person.getPersonSurname());
        surname.addStyleName(Reindeer.LABEL_SMALL);
    
        table.addComponent(surname, 2, 3);

        table.addComponent(new Label("Other Name: "), 1, 4);
        Label othername = new Label(person.getPersonOtherName());
        othername.addStyleName(Reindeer.LABEL_SMALL);

        table.addComponent(othername, 2, 4);

        table.addComponent(new Label("Gender: "), 1, 5);
        Label gender = new Label(st.getGender(person.getDemography()));
        gender.addStyleName(Reindeer.LABEL_SMALL);
  
        table.addComponent(gender, 2, 5);


        table.addComponent(new Label("Date of Birth: "), 1, 6);
        Label dob = new Label(st.getDobLabel(person.getDemography()));
        dob.addStyleName(Reindeer.LABEL_SMALL);

        table.addComponent(dob, 2, 6);

        table.addComponent(new Label("Resididence: "), 1, 7);
        Label resi = new Label(st.getResidence(person.getResidence()));
        resi.addStyleName(Reindeer.LABEL_SMALL);
        table.addComponent(resi, 2, 7);


        //Add Grid to tables
        tables.add(table);

        return tables;

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == edit) {
             editPerson(person);
        } else if (source == delete) {
            data.getPersonService().remove(person);
            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));

        }
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the table
     */
    public GridLayout getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(GridLayout table) {
        this.table = table;
    }

    /**
     * @return the tables
     */
    public List<GridLayout> getTables() {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(List<GridLayout> tables) {
        this.tables = tables;
    }

    private void editPerson(Person person) {
         PersonBean b = getBean(person);
       main.mainView.setSecondComponent(new PersonEditForm(main, b));
    }

    private PersonBean getBean(Person person) {
        PersonBean bean = new PersonBean();
        bean.setPersonId(person.getId());
        bean.setPersonName(person.getPersonName());
        bean.setPersonOtherName(person.getPersonOtherName());
        bean.setPersonSurname(person.getPersonSurname());
        

        bean.setResidence(st.getResidence(person.getResidence()));
        bean.setGender(st.getGender(person.getDemography()));
        bean.setDob(st.getDob(person.getDemography()));
        return bean;
    }
}
