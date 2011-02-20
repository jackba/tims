/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingFunder;
import java.util.List;

/**
 *
 * @author stud
 */
public class TrainingFunderTable extends com.vaadin.ui.Table{
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
                                 funder.getCity().getName(),
                                 funder.getContact().getTelephoneNumber(),
                                 funder.getContact().getEmail(),


                                 funder.getCity().getDistrict().getCounty().getProvince().getCountry().getCountryName(),
                                 funder.getCity().getDistrict().getCounty().getProvince().getRegionName(),
                                 funder.getCity().getDistrict().getCounty().getCountyName(),
                                 funder.getCity().getDistrict().getDistrictName(),
                                 funder.getContact().getMailingAddress(),




                                 funder.getContact().getCellnumber(),
                                 funder.getContact().getFaxnumber(),
                                 funder.getContact().getNotes()

                                    }, funder.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
