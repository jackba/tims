/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.util.CityInformation;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.traininglist.TrainingFunder;
import java.util.List;

/**
 *
 * @author stud
 */
public class TrainingFunderTable extends com.vaadin.ui.Table {

    private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public TrainingFunderTable(HashThrimsMain app) {
        main = app;
        // Make Table fill all space
        setSizeFull();
        //setImmediate(true);
        // Define the names and data types of columns.




        addContainerProperty("Funder Name", String.class, null);
        addContainerProperty("City Name", String.class, null);
        addContainerProperty("Telephone Number", String.class, null);
        addContainerProperty("E-mail Address", String.class, null);

        addContainerProperty("Country Name", String.class, null);
        addContainerProperty("Province Name", String.class, null);
        addContainerProperty("County Name", String.class, null);
        addContainerProperty("District Name", String.class, null);

        addContainerProperty("Mailing Address", String.class, null);

        addContainerProperty("Cell Phone", String.class, null);
        addContainerProperty("Fax Number", String.class, null);
        addContainerProperty("Notes", String.class, null);




        // Add Data Columns
        List<TrainingFunder> funders = data.getTrainingFunderService().findAll();
        for (TrainingFunder funder : funders) {
            addItem(new Object[]{funder.getTrainingFunderName(),
                         CityInformation.getCityName(funder.getCity()),
                        getTelephoneNumber(funder.getContact()),
                        getEmail(funder.getContact()),
                        CityInformation.getCountryName(funder.getCity()),
                        CityInformation.getProvinceName(funder.getCity()),
                        CityInformation.getCountyName(funder.getCity()),
                        CityInformation.getDistrictName(funder.getCity()),

                        getMailingAddress(funder.getContact()),
                        getCellNumber(funder.getContact()),
                        getFaxNumber(funder.getContact()),
                        getNotes(funder.getContact())
                    }, funder.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    private String getTelephoneNumber(Contacts contact) {
        if (contact != null) {
            return contact.getTelephoneNumber();
        }
        return null;


    }

    private String getEmail(Contacts contact) {
        if (contact != null) {
            return contact.getEmail();
        }
        return null;
    }

    private String getMailingAddress(Contacts contact) {
        if (contact != null) {
            return contact.getMailingAddress();
        }
        return null;
    }

    private String getCellNumber(Contacts contact) {
        if (contact != null) {
            return contact.getCellnumber();
        }
        return null;
    }

    private String getFaxNumber(Contacts contact) {
        if (contact != null) {
            return contact.getFaxnumber();
        }
        return null;
    }

    private String getNotes(Contacts contact) {
        if (contact != null) {
            return contact.getNotes();
        }
        return null;
    }
}
