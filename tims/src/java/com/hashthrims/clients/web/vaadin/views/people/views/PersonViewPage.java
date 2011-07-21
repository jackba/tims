/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.forms.PersonForm;
import com.hashthrims.clients.web.vaadin.views.people.models.PersonBean;
import com.hashthrims.clients.web.vaadin.views.people.tables.PersonTable;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.factories.PersonFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.hashthrims.infrastructure.util.DateUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class PersonViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private PersonForm pform = new PersonForm();
    private Form form = pform.createPersonFrom();
    private static ClientDataService data = new ClientDataService();
    private PersonTable table;
    private DateUtil date = new DateUtil();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();

    public PersonViewPage(HashThrimsMain app) {


        main = app;
        setSizeFull();
        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        PersonBean bean = new PersonBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);


        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        List<Person> persons = data.getPersonService().findAll();
        table = new PersonTable(main,persons);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {


            Item record = table.getItem(table.getValue());
            PersonBean bean = new PersonBean();
            bean.setPersonId(new Long(table.getValue().toString()));
            bean.setPersonName(record.getItemProperty("First Name").toString());
            bean.setPersonOtherName(record.getItemProperty("Surname").toString());
            bean.setPersonOtherName(record.getItemProperty("Other Name").toString());
            bean.setResidence(record.getItemProperty("Residence").toString());
            bean.setGender(record.getItemProperty("Gender").toString());
            bean.setDob(fieldValues.getDbDateFields(record.getItemProperty("Date of Birth").toString()));


            if (bean != form.getItemDataSource()) {
                BeanItem item = new BeanItem(bean);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);
            }
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {

            saveNewPerson(form);
           main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {

            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
        } else if (source == pform.getUpdate()) {
            saveEditedPerson(form);
           main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));

        } else if (source == pform.getDelete()) {
            deletePerson(form);
          main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));

        }
    }

    private void saveNewPerson(Form form) {
        PersonFactory factory = data.getPersonFactory();
        String personName = fieldValues.getStringFields(form.getField("personName").getValue());
        String personSurname = fieldValues.getStringFields(form.getField("personSurname").getValue());
        String personOtherName = fieldValues.getStringFields(form.getField("personOtherName").getValue());

        String gender = fieldValues.getStringFields(form.getField("gender").getValue());
        String residence = fieldValues.getStringFields(form.getField("residence").getValue());
        Date dob = fieldValues.getDateFields(form.getField("dob").getValue());

        String position = fieldValues.getStringFields(form.getField("position").getValue());
        String status =fieldValues.getStringFields(form.getField("status").getValue());

        Date  startDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        Date endDate = fieldValues.getDateFields(form.getField("endDate").getValue());

        Map<String, String> personValues = new HashMap<String, String>();
        personValues.put("gender", gender);
        personValues.put("residence", residence);
        personValues.put("personName", personName);
        personValues.put("personOtherName", personOtherName);
        personValues.put("personSurname", personSurname);
        personValues.put("status ", status );
        personValues.put("position", position);

        Map<String, Date> dates = new HashMap<String,Date>();
        dates.put("dob", dob);
        dates.put("startDate", startDate);
        dates.put("endDate", endDate);

        Person p = factory.createPerson(personValues, dates);
        data.getPersonService().persist(p);
    }

    private void saveEditedPerson(Form form) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void deletePerson(Form form) {
        PersonFactory factory = data.getPersonFactory();
        Long personId = Long.parseLong(form.getField("personId").getValue().toString());
        Person p = factory.loadPerson(personId);
        data.getPersonService().remove(p);
    }
}
