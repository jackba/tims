/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.TrainingInstitutionForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.TrainingInstitutionBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.TrainingInstitutionTable;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.infrastructure.factories.TrainingFactory;
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

/**
 *
 * @author boniface
 */
public class TrainingInstitutionViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener{

    private HashThrimsMain main;
    private Form form;
    private TrainingInstitutionForm cf;
    private static ClientDataService data = new ClientDataService();
    private TrainingInstitutionTable table;

    public TrainingInstitutionViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new TrainingInstitutionForm();
        form = cf.createTrainingInstitutionFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        TrainingInstitutionBean bean = new TrainingInstitutionBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        //form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new TrainingInstitutionTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {

        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            TrainingInstitutionBean institution = new TrainingInstitutionBean();

            institution.setCountryName(record.getItemProperty("Country Name").toString());
            institution.setProvinceName(record.getItemProperty("Province Name").toString());
            institution.setCountyName(record.getItemProperty("County Name").toString());
            institution.setDistrictName(record.getItemProperty("District Name").toString());
            institution.setCityName(record.getItemProperty("City Name").toString());



            institution.setInstitutionName(record.getItemProperty("Training Institution").toString());

            institution.setTelephoneNumber(record.getItemProperty("Telephone Number").toString());
            institution.setEmail(record.getItemProperty("E-mail Address").toString());


            institution.setMailingAddress(record.getItemProperty("Mailing Address").toString());

            institution.setCellnumber(record.getItemProperty("Cell Phone").toString());
            institution.setFaxnumber(record.getItemProperty("Fax Number").toString());
            institution.setNotes(record.getItemProperty("Notes").toString());


            institution.setInstitutionId(new Long(table.getValue().toString()));




            if (institution != form.getItemDataSource()) {
//                WORKS System.out.println("COUNTY IN FACILITY IS "+facility.getCountryName());
//                System.out.println("DISTRICT IN FACILITY IS "+facility.getDistrictName());
                BeanItem item = new BeanItem(institution);
//                WROKS TOO System.out.println(" The ITEM IS "+item.getItemProperty("countyName"));
                form.setItemDataSource(item);

//                BUG START HERE System.out.println(" COUNTY DATA BEFORE READ"+form.getField("countyName").getValue().toString());
//                System.out.println(" DISTRICT DATA BEFORE READ"+form.getField("districtName").getValue().toString());



                form.setReadOnly(true);

//                WONT WORK System.out.println(" COUNTY DATA AFTER READ"+form.getField("countyName").getValue().toString());
//                System.out.println(" DISTRICT DATA AFTER READ"+form.getField("districtName").getValue().toString());

                //Buttons Behaviou
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
            saveNewTrainingInstitution(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"INSTITUTION"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"INSTITUTION"));
        } else if (source == cf.getUpdate()) {
            saveEditedTrainingInstitution(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"INSTITUTION"));

        } else if (source == cf.getDelete()) {
            deleteTrainingInstitution(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"INSTITUTION"));

        }

    }

    public void saveNewTrainingInstitution(Form form) {
       TrainingFactory factory = data.getTrainingFactory();
        //OrganisationListFactory factory = data.getOfficeFactory();

        String countryName = form.getField("countryName").getValue().toString();
        String provinceName = form.getField("provinceName").getValue().toString();
        String countyName = form.getField("countyName").getValue().toString();
        String districtName = form.getField("districtName").getValue().toString();


        String institutionName = form.getField("institutionName").getValue().toString();
        String cityName = form.getField("cityName").getValue().toString();

        String mailingAddress = form.getField("mailingAddress").getValue().toString();
        String telephoneNumber = form.getField("telephoneNumber").getValue().toString();
        String cellnumber = form.getField("cellnumber").getValue().toString();
        String faxnumber = form.getField("faxnumber").getValue().toString();
        String email = form.getField("email").getValue().toString();
        String notes = form.getField("notes").getValue().toString();

        Contacts contacts = new Contacts();
        contacts.setCellnumber(cellnumber);
        contacts.setEmail(email);
        contacts.setFaxnumber(faxnumber);
        contacts.setTelephoneNumber(telephoneNumber);
        contacts.setNotes(notes);
        contacts.setMailingAddress(mailingAddress);


        TrainingInstitution c = factory.createTrainingInstitution(institutionName, cityName, contacts);
        data.getTrainingInstitutionService().persist(c);
    }

    public void saveEditedTrainingInstitution(Form form) {
      TrainingFactory factory = data.getTrainingFactory();
        //OrganisationListFactory factory = data.getOfficeFactory();

        String countryName = form.getField("countryName").getValue().toString();
        String provinceName = form.getField("provinceName").getValue().toString();
        String countyName = form.getField("countyName").getValue().toString();
        String districtName = form.getField("districtName").getValue().toString();


        String institutionName = form.getField("institutionName").getValue().toString();
        String cityName = form.getField("cityName").getValue().toString();

        String mailingAddress = form.getField("mailingAddress").getValue().toString();
        String telephoneNumber = form.getField("telephoneNumber").getValue().toString();
        String cellnumber = form.getField("cellnumber").getValue().toString();
        String faxnumber = form.getField("faxnumber").getValue().toString();
        String email = form.getField("email").getValue().toString();
        String notes = form.getField("notes").getValue().toString();

        Contacts contacts = new Contacts();
        contacts.setCellnumber(cellnumber);
        contacts.setEmail(email);
        contacts.setFaxnumber(faxnumber);
        contacts.setTelephoneNumber(telephoneNumber);
        contacts.setNotes(notes);
        contacts.setMailingAddress(mailingAddress);

         Long institutionId = Long.parseLong(form.getField("institutionId").getValue().toString());
        TrainingInstitution c = factory.updateTrainingInstitution(institutionName, cityName, contacts, institutionId);


        data.getTrainingInstitutionService().merge(c);
    }

    public void deleteTrainingInstitution(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long institutionId = Long.parseLong(form.getField("institutionId").getValue().toString());
       TrainingInstitution c = factory.loadTrainingInstitution(institutionId);
       data.getTrainingInstitutionService().remove(c);
    }
}
