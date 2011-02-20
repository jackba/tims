/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.List;

/**
 *
 * @author boniface
 */
public class TrainingFunderForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public TrainingFunderForm() {
    }

    public Form createTrainingFunderFrom() {
        Form form = new TrainingFunderGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new TrainingFunderFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.addComponent(update);
        footer.addComponent(delete);
        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:
        edit.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    class TrainingFunderFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCountry;
        private Select selectProvince;
        private Select selectCounty;
        private Select selectDistrict;
        private Select selectCity;
        
        private List<Province> provinces;
        private List<County> counties;
        private List<District> districts;
        private List<City> cities;
        private Country c;
        private Province p;
        private County ct;
        private District dt;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);



            if ("trainingFunderId".equals(propertyId)) {
                field = new TextField("TrainingFunder ID:");
                ((TextField) field).setVisible(false);
            } else if ("trainingFunder".equals(propertyId)) {
                field = new TextField("Training Funder :");
         
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
         
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Name");

            } else if ("countryName".equals(propertyId)) {
                List<Country> countries = data.getCountryService().findAll();
                selectCountry = new Select("Country:");
                selectCountry.addListener(this);
                selectCountry.setImmediate(true);
                for (Country country : countries) {
                    selectCountry.addItem(country.getCountryName());
                }
                selectCountry.setNewItemsAllowed(true);
                selectCountry.setWidth("250");
                selectCountry.setRequired(true);
                return selectCountry;
            } else if ("provinceName".equals(propertyId)) {
                selectProvince = new Select("Province:");
                selectProvince.addListener(this);
                selectProvince.setImmediate(true);
                if (c != null) {
                    provinces = c.getProvince();
                    for (Province province : provinces) {
                        selectProvince.addItem(province.getRegionName());
                    }
                } else {
                }
                selectProvince.setNewItemsAllowed(true);
                selectProvince.setWidth("250");
                selectProvince.setRequired(true);
                return selectProvince;
            } else if ("countyName".equals(propertyId)) {
                selectCounty = new Select("Municipality:");
                selectCounty.addListener(this);
                selectCounty.setImmediate(true);
                if (p != null) {
                    counties = p.getCounty();
                    for (County country : counties) {
                        selectCounty.addItem(country.getCountyName());
                    }
                } else {
                }
                selectCounty.setNewItemsAllowed(true);
                selectCounty.setWidth("250");
                selectCounty.setRequired(true);
                return selectCounty;

            } else if ("districtName".equals(propertyId)) {

                selectDistrict = new Select("District:");
                selectDistrict.addListener(this);
                selectDistrict.setImmediate(true);
                if (ct != null) {
                    districts = ct.getDistrict();
                    for (District district : districts) {
                        selectDistrict.addItem(district.getDistrictName());
                    }
                } else {
                }
                selectDistrict.setNewItemsAllowed(true);
                selectDistrict.setWidth("250");
                selectDistrict.setRequired(true);
                return selectDistrict;
            } else if ("cityName".equals(propertyId)) {
                selectCity = new Select("City:");
               if (dt != null) {
                    cities = dt.getCities();
                    for (City cti : cities) {
                        selectDistrict.addItem(cti.getName());
                    }
                } else {
                }
                selectCity.setNewItemsAllowed(true);
                selectCity.setWidth("250");
                selectCity.setRequired(true);
                return selectCity;
            } else if ("mailingAddress".equals(propertyId)) {
                field = new TextField("Mailing Address:");
           
                ((TextField) field).setColumns(20);
                ((TextField) field).setRows(4);
                ((TextField) field).setNullRepresentation("");
           
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Name");
            } else if ("telephoneNumber".equals(propertyId)) {
                field = new TextField("Telephone Number:");
           
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
             
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Code");

            } else if ("cellnumber".equals(propertyId)) {
                field = new TextField("Cell Number :");
           
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
             
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Name");
            } else if ("faxnumber".equals(propertyId)) {
                field = new TextField("Fax Number:");
          
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
             
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Name");
            } else if ("email".equals(propertyId)) {
                field = new TextField("E-mail Address:");
            
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
           
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Name");
            } else if ("notes".equals(propertyId)) {
                field = new TextField("Notes (Primary Contact Person):");
              
                ((TextField) field).setColumns(20);
                ((TextField) field).setRows(4);
                ((TextField) field).setNullRepresentation("");
          
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter TrainingFunder Name");
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

            if (property == selectCountry) {
                if (property.getValue() != null) {
                    c = data.getCountryService().getByPropertyName("countryName", property.getValue().toString());
                    provinces = c.getProvince();
                    if (!selectProvince.isReadOnly()) {
                        selectProvince.removeAllItems();
                    }
                    for (Province province : provinces) {
                        selectProvince.addItem(province.getRegionName());
                    }
                } else {
                    if (!selectProvince.isReadOnly()) {
                        selectProvince.removeAllItems();
                    }
                }
            } else if (property == selectProvince) {
                if (property.getValue() != null) {
                    p = data.getRegionService().getByPropertyName("regionName", property.getValue().toString());
                    counties = p.getCounty();
                    if (!selectCounty.isReadOnly()) {
                        selectCounty.removeAllItems();
                    }
                    for (County county : counties) {
                        selectCounty.addItem(county.getCountyName());
                    }
                } else {
                    if (!selectCounty.isReadOnly()) {
                        selectCounty.removeAllItems();
                    }
                }
            } else if (property == selectCounty) {
                if (property.getValue() != null) {
                    ct = data.getCountyService().getByPropertyName("countyName", property.getValue().toString());
                    districts = ct.getDistrict();
                    if (!selectDistrict.isReadOnly()) {
                        selectDistrict.removeAllItems();
                    }
                    for (District district : districts) {
                        selectDistrict.addItem(district.getDistrictName());
                    }
                } else {
                    if (!selectDistrict.isReadOnly()) {
                        selectDistrict.removeAllItems();
                    }
                }
            } else if (property == selectDistrict) {
                if (property.getValue() != null) {
                    dt = data.getDistrictService().getByPropertyName("districtName", property.getValue().toString());
                    cities = dt.getCities();
                    if (!selectCity.isReadOnly()) {
                        selectCity.removeAllItems();
                    }
                    for (City city : cities) {
                        selectCity.addItem(city.getName());
                    }
                } else {
                    if (!selectCity.isReadOnly()) {
                        selectCity.removeAllItems();
                    }
                }
            }
        }
    }

    class TrainingFunderGridForm extends Form {

        private GridLayout layout;

        public TrainingFunderGridForm() {
            setCaption("TrainingFunder Form");
            layout = new GridLayout(2, 12);
            layout.setMargin(true);
            layout.setSpacing(true);

            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Training Funder");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);


            HorizontalLayout secondHeader = new HorizontalLayout();
            secondHeader.setSizeFull();
            secondHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label secondHeaderLabel = new Label("Contact Information");
            secondHeaderLabel.setStyleName(Runo.LABEL_H2);
            secondHeader.addComponent(secondHeaderLabel);
            layout.addComponent(secondHeader, 0, 6, 1, 6);
            layout.setComponentAlignment(secondHeader, Alignment.MIDDLE_CENTER);


            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 11, 1, 11);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("trainingFunder")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("trainingFunderId")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("countryName")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("provinceName")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("countyName")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("districtName")) {
                layout.addComponent(field, 1, 4);
            } else if (propertyId.equals("cityName")) {
                layout.addComponent(field, 1, 5);
            } else if (propertyId.equals("mailingAddress")) {
                layout.addComponent(field, 0, 7, 0, 8);
            } else if (propertyId.equals("telephoneNumber")) {
                layout.addComponent(field, 0, 9);
            } else if (propertyId.equals("cellnumber")) {
                layout.addComponent(field, 0, 10);
            } else if (propertyId.equals("faxnumber")) {
                layout.addComponent(field, 1, 7);
            } else if (propertyId.equals("email")) {
                layout.addComponent(field, 1, 8);
            } else if (propertyId.equals("notes")) {
                layout.addComponent(field, 1, 9, 1, 10);
            }


        }
    }

    /**
     * @return the delete
     */
    public Button getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(Button delete) {
        this.delete = delete;
    }

    /**
     * @return the save
     */
    public Button getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(Button save) {
        this.save = save;
    }

    /**
     * @return the edit
     */
    public Button getEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(Button edit) {
        this.edit = edit;
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the update
     */
    public Button getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Button update) {
        this.update = update;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }
}
