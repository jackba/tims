/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.TrainingFunderForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.TrainingFunderBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.TrainingFunderTable;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.traininglist.TrainingFunder;
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
public class TrainingFunderViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private TrainingFunderForm cf;
     private static ClientDataService data = new ClientDataService();
    private TrainingFunderTable table;

    public TrainingFunderViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new TrainingFunderForm();
        form = cf.createTrainingFunderFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        TrainingFunderBean bean = new TrainingFunderBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        //form.setVisibleItemProperties(cf.);

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new TrainingFunderTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            TrainingFunderBean funder = new TrainingFunderBean();

            funder.setCountryName(record.getItemProperty("Country Name").toString());
            funder.setProvinceName(record.getItemProperty("Province Name").toString());
            funder.setCountyName(record.getItemProperty("County Name").toString());
            funder.setDistrictName(record.getItemProperty("District Name").toString());
            funder.setCityName(record.getItemProperty("City Name").toString());



            funder.setTrainingFunder(record.getItemProperty("Funder Name").toString());


            funder.setTelephoneNumber(record.getItemProperty("Telephone Number").toString());
            funder.setEmail(record.getItemProperty("E-mail Address").toString());


            funder.setMailingAddress(record.getItemProperty("Mailing Address").toString());

            funder.setCellnumber(record.getItemProperty("Cell Phone").toString());
            funder.setFaxnumber(record.getItemProperty("Fax Number").toString());
            funder.setNotes(record.getItemProperty("Notes").toString());


            funder.setTrainingFunderId(new Long(table.getValue().toString()));




            if (funder != form.getItemDataSource()) {
//                WORKS System.out.println("COUNTY IN FACILITY IS "+facility.getCountryName());
//                System.out.println("DISTRICT IN FACILITY IS "+facility.getDistrictName());
                BeanItem item = new BeanItem(funder);
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
            saveNewTrainingFunder(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "FUNDER"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "FUNDER"));
        } else if (source == cf.getUpdate()) {
            saveEditedTrainingFunder(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "FUNDER"));

        } else if (source == cf.getDelete()) {
            deleteTrainingFunder(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "FUNDER"));

        }

    }

    public void saveNewTrainingFunder(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        //OrganisationListFactory factory = data.getOfficeFactory();

        String countryName = form.getField("countryName").getValue().toString();
        String provinceName = form.getField("provinceName").getValue().toString();
        String countyName = form.getField("countyName").getValue().toString();
        String districtName = form.getField("districtName").getValue().toString();


        String funderName = form.getField("trainingFunder").getValue().toString();
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


        TrainingFunder c = factory.createTrainingFunder(funderName, cityName, contacts);
        data.getTrainingFunderService().persist(c);
    }

    public void saveEditedTrainingFunder(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        //OrganisationListFactory factory = data.getOfficeFactory();

        String countryName = form.getField("countryName").getValue().toString();
        String provinceName = form.getField("provinceName").getValue().toString();
        String countyName = form.getField("countyName").getValue().toString();
        String districtName = form.getField("districtName").getValue().toString();


        String funderName = form.getField("trainingFunder").getValue().toString();
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

        Long funderId = Long.parseLong(form.getField("trainingFunderId").getValue().toString());
        TrainingFunder c = factory.updateTrainingFunder(funderName, cityName, contacts, funderId);


        data.getTrainingFunderService().merge(c);
    }

    public void deleteTrainingFunder(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long funderId = Long.parseLong(form.getField("trainingFunderId").getValue().toString());
        TrainingFunder c = factory.loadTrainingFunder(funderId);
        data.getTrainingFunderService().remove(c);
    }
}
