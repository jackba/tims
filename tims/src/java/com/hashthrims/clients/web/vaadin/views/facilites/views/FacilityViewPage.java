/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.facilites.forms.FacilityForm;
import com.hashthrims.clients.web.vaadin.views.facilites.model.FacilityBean;
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
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.clients.web.vaadin.views.facilites.table.FacilityTable;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.infrastructure.factories.offices.OrganisationListFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;


/**
 *
 * @author boniface
 */
public class FacilityViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final FacilityForm cf;
    private final ClientDataService data = new ClientDataService();
    private final FacilityTable table;
    private final OrganisationListFactory factory = data.getOfficeFactory();
    private final DataFieldsUtil val = new DataFieldsUtil();
    

    public FacilityViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        final FacilityBean bean = new FacilityBean();
        final BeanItem item = new BeanItem(bean);
        cf = new FacilityForm();
        form = cf.createFacilityFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);


        form.setItemDataSource(item);
        //form.setVisibleItemProperties(cf.orderList());
        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new FacilityTable(main);
        table.setColumnCollapsingAllowed(true);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {

        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final FacilityBean facility = new FacilityBean();

            facility.setCountryName(val.getTableFields(record.getItemProperty("Country Name")));
            facility.setProvinceName(val.getTableFields(record.getItemProperty("Province Name")));
            facility.setCountyName(val.getTableFields(record.getItemProperty("District Name")));
            facility.setDistrictName(val.getTableFields(record.getItemProperty("Sub District Name")));
            facility.setCityName(val.getTableFields(record.getItemProperty("City Name")));



            facility.setFacilityName(val.getTableFields(record.getItemProperty("Facility Name")));
            facility.setFacilityTypeName(val.getTableFields(record.getItemProperty("Facility Type")));

            facility.setTelephoneNumber(val.getTableFields(record.getItemProperty("Telephone Number")));
            facility.setEmail(val.getTableFields(record.getItemProperty("E-mail Address")));


            facility.setMailingAddress(val.getTableFields(record.getItemProperty("Mailing Address")));

            facility.setCellnumber(val.getTableFields(record.getItemProperty("Cell Phone")));
            facility.setFaxnumber(val.getTableFields(record.getItemProperty("Fax Number")));
            facility.setNotes(val.getTableFields(record.getItemProperty("Notes")));


            facility.setFacilityId(new Long(table.getValue().toString()));




            if (facility != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(facility);
                form.setItemDataSource(item);

                form.setReadOnly(true);

                cf.getSave().setVisible(false);
                cf.getEdit().setVisible(true);
                cf.getCancel().setVisible(true);
                cf.getDelete().setVisible(true);
                cf.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == cf.getSave()) {
            saveNewFacility(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITY"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITY"));
        } else if (source == cf.getUpdate()) {
            saveEditedFacility(form);

            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITY"));

        } else if (source == cf.getDelete()) {
            deleteFacility(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "FACILITY"));
        }

    }

    public void saveNewFacility(Form form) {

        final String countryName = val.getStringFields(form.getField("countryName").getValue());
        String provinceName = form.getField("provinceName").getValue().toString();
        String countyName = form.getField("countyName").getValue().toString();
        String districtName = form.getField("districtName").getValue().toString();

        String facilityTypeName = form.getField("facilityTypeName").getValue().toString();
        String facilityName = form.getField("facilityName").getValue().toString();
        String cityName = form.getField("cityName").getValue().toString();

        String mailingAddress =  val.getStringFields(form.getField("mailingAddress").getValue());
        String telephoneNumber =  val.getStringFields(form.getField("telephoneNumber").getValue());
        String cellnumber =  val.getStringFields(form.getField("cellnumber").getValue());
        String faxnumber =  val.getStringFields(form.getField("faxnumber").getValue());
        String email =  val.getStringFields(form.getField("email").getValue());
        String notes =  val.getStringFields(form.getField("notes").getValue());

        Contacts contacts = new Contacts();
        contacts.setCellnumber(cellnumber);
        contacts.setEmail(email);
        contacts.setFaxnumber(faxnumber);
        contacts.setTelephoneNumber(telephoneNumber);
        contacts.setNotes(notes);
        contacts.setMailingAddress(mailingAddress);
        Facility c = factory.createFacility(facilityTypeName, facilityName, cityName, contacts);
        data.getFacilityService().persist(c);

    }

    public void saveEditedFacility(Form form) {
       
        String countryName = form.getField("countryName").getValue().toString();
        String provinceName = form.getField("provinceName").getValue().toString();
        String countyName = form.getField("countyName").getValue().toString();
        String districtName = form.getField("districtName").getValue().toString();

        String facilityTypeName = form.getField("facilityTypeName").getValue().toString();
        String facilityName = form.getField("facilityName").getValue().toString();
        String cityName = form.getField("cityName").getValue().toString();

        String mailingAddress =  val.getStringFields(form.getField("mailingAddress").getValue());
        String telephoneNumber =  val.getStringFields(form.getField("telephoneNumber").getValue());
        String cellnumber =  val.getStringFields(form.getField("cellnumber").getValue());
        String faxnumber =  val.getStringFields(form.getField("faxnumber").getValue());
        String email =  val.getStringFields(form.getField("email").getValue());
        String notes =  val.getStringFields(form.getField("notes").getValue());

        Long facilityId = Long.parseLong(form.getField("facilityId").getValue().toString());

        Contacts contacts = new Contacts();
        contacts.setCellnumber(cellnumber);
        contacts.setEmail(email);
        contacts.setFaxnumber(faxnumber);
        contacts.setTelephoneNumber(telephoneNumber);
        contacts.setNotes(notes);
        contacts.setMailingAddress(mailingAddress);

        Facility f = factory.updatedFacility(facilityTypeName, facilityName, cityName, contacts, facilityId);
        data.getFacilityService().merge(f);
    }

    public void deleteFacility(Form form) {
        
        Long facilityId = Long.parseLong(form.getField("facilityId").getValue().toString());
        Facility f = factory.loadFacility(facilityId);
        data.getFacilityService().remove(f);
    }
}
