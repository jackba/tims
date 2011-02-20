/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class FacilityTable extends Table {

    private final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;
    private final TimsUtil st = new TimsUtil();


    public FacilityTable(HashThrimsMain app) {
        main = app;
        // Make Table fill all space
        setSizeFull();
        //setImmediate(true);
        // Define the names and data types of columns.




        addContainerProperty("Facility Name", String.class, null);
        addContainerProperty("Facility Type", String.class, null);
        addContainerProperty("City Name", String.class, null);
        addContainerProperty("Telephone Number", String.class, null);
        addContainerProperty("E-mail Address", String.class, null);

        addContainerProperty("Country Name", String.class, null);
        addContainerProperty("Province Name", String.class, null);
        addContainerProperty("District  Name", String.class, null);
        addContainerProperty("Sub District Name", String.class, null);
        addContainerProperty("Mailing Address", String.class, null);

        addContainerProperty("Cell Phone", String.class, null);
        addContainerProperty("Fax Number", String.class, null);
        addContainerProperty("Notes", String.class, null);




        // Add Data Columns
        final List<Facility> facilites = data.getFacilityService().findAll();
        for (Facility facility : facilites) {
            addItem(new Object[]{facility.getFacilityName(),
                                 st.getFacilityTypeName(facility.getFacilityType()),
                                 st.getCityName(facility.getCity()),
                                 st.getTelephoneNumber(facility.getContact()),
                                 st.getEmail(facility.getContact()),
                
                
                                 facility.getCity().getDistrict().getCounty().getProvince().getCountry().getCountryName(),
                                 facility.getCity().getDistrict().getCounty().getProvince().getRegionName(),
                                 facility.getCity().getDistrict().getCounty().getCountyName(),
                                 facility.getCity().getDistrict().getDistrictName(),
                                 st.getMailingAdress(facility.getContact()),




                                 st.getCellNumber(facility.getContact()),
                                 st.getFaxNumber(facility.getContact()),
                                 st.getContactNotes(facility.getContact())

                                    }, facility.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
