/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.traininginformation.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import java.util.List;

/**
 *
 * @author stud
 */
public class TrainingInstitutionTable extends com.vaadin.ui.Table{
  private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;

    public TrainingInstitutionTable(HashThrimsMain app) {
        main = app;
        // Make Table fill all space
        setSizeFull();
        //setImmediate(true);
        // Define the names and data types of columns.




        addContainerProperty("Training Institution", String.class, null);
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
        List<TrainingInstitution> institutions = data.getTrainingInstitutionService().findAll();
        for (TrainingInstitution institution : institutions) {
            addItem(new Object[]{institution.getTrainingInstitution(),
                                 institution.getCity().getName(),
                                 institution.getContact().getTelephoneNumber(),
                                 institution.getContact().getEmail(),


                                 institution.getCity().getDistrict().getCounty().getProvince().getCountry().getCountryName(),
                                 institution.getCity().getDistrict().getCounty().getProvince().getRegionName(),
                                 institution.getCity().getDistrict().getCounty().getCountyName(),
                                 institution.getCity().getDistrict().getDistrictName(),
                                 institution.getContact().getMailingAddress(),




                                 institution.getContact().getCellnumber(),
                                 institution.getContact().getFaxnumber(),
                                 institution.getContact().getNotes()

                                    }, institution.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
