/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.tables;

import com.hashthrims.clients.web.vaadin.views.reports.people.forms.DateSearchCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.LocationCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.model.PeopleReport;
import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PeopleTable {

    public Table getTable(LocationCombo locationCombo, DateSearchCombo dateSearchCombo) {
        Table table = new Table();
        table.setSizeFull();



        table.addContainerProperty("First Name", String.class, null);
        table.addContainerProperty("Last Name", String.class, null);
        table.addContainerProperty("Profession ", String.class, null);
        table.addContainerProperty("Course ", String.class, null);
        table.addContainerProperty("Facility ", String.class, null);

        table.addContainerProperty("City", String.class, null);
        table.addContainerProperty("Sub District", String.class, null);
        table.addContainerProperty("District ", String.class, null);


        table.addContainerProperty("Course Start Date ", Date.class, null);
        table.addContainerProperty("Course End Date ", Date.class, null);

        List<PeopleReport> people = getListOfPeople(locationCombo, dateSearchCombo);

        for (PeopleReport p : people) {
            table.addItem(new Object[]{
                p.getFirstname(), 
                p.getLastname(), 
                p.getProfession(), 
                p.getCourseName(),
                p.getFacilityname(),
                p.getCityname(),
                p.getSubdistrict(),
                p.getDistrict(),
                p.getCourseStateDate(),
                p.getCourseEndDate()}, p.getId());
        }

        return table;
    }

    private List<PeopleReport> getListOfPeople(LocationCombo locationCombo, DateSearchCombo dateSearchCombo) {
        return new PeopleData().getPeople(locationCombo, dateSearchCombo);
    }

   
}
