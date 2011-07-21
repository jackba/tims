/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
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
public class PersonForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public PersonForm() {
    }

    public Form createPersonFrom() {
        final Form form = new PersonGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new PersonFieldFactory());


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

    class PersonFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCountry;
        private Select selectProvince;
        private Select selectCounty;
        private Select selectDistrict;
        private Select selectCity;
        private Select selectGenderList;
        private Select selectFacilityList;
        private Select selectPositionList;
        private Select selectStatus;
        private List<Province> provinces;
        private List<County> counties;
        private List<District> districts;
        private List<City> cities;
        private List<Positions> positions;
        private Country c;
        private Province p;
        private County ct;
        private District dt;
        private Facility facility;
        private Positions position;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("personId".equals(propertyId)) {
                field = new TextField("Person ID:");
                ((TextField) field).setVisible(false);
            } else if ("personName".equals(propertyId)) {
                field = new TextField("First Name:");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Person Name");
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
                selectCounty = new Select("District:");
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

                selectDistrict = new Select("Sub District:");
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
            } else if ("residence".equals(propertyId)) {
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
            } else if ("personSurname".equals(propertyId)) {
                field = new TextField("Surname:");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");

            } else if ("personOtherName".equals(propertyId)) {
                field = new TextField("Other Name :");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("gender".equals(propertyId)) {
                List<GenderList> genderLists = data.getGenderListService().findAll();
                selectGenderList = new Select("Gender");
                for (GenderList gender : genderLists) {
                    selectGenderList.addItem(gender.getGender());
                }
                selectGenderList.setNewItemsAllowed(false);
                selectGenderList.addListener(this);
                selectGenderList.setImmediate(true);
                selectGenderList.setWidth("250");
                selectGenderList.setRequired(true);
                return selectGenderList;
            } else if ("facility".equals(propertyId)) {
                List<Facility> facilities = data.getFacilityService().findAll();
                selectFacilityList = new Select("Facility");
                for (Facility fac : facilities) {
                    selectFacilityList.addItem(fac.getId());
                    selectFacilityList.setItemCaption(fac.getId(), fac.getFacilityName());
                }
                selectFacilityList.setNewItemsAllowed(false);
                selectFacilityList.addListener(this);
                selectFacilityList.setImmediate(true);
                selectFacilityList.setWidth("250");

                return selectFacilityList;
            } else if ("position".equals(propertyId)) {

                selectPositionList = new Select("Position");
                selectPositionList.setNewItemsAllowed(false);
                selectPositionList.addListener(this);
                if (facility != null) {
                    positions = facility.getPositions();
                    for (Positions pos : positions) {

                        if (st.checkPositionAvalaibality(pos)) {
                            selectPositionList.addItem(pos.getPositionCode());
                        }
                    }
                } else {
                }
                selectPositionList.setImmediate(true);
                selectPositionList.setWidth("250");

                return selectPositionList;
            } else if ("status".equals(propertyId)) {
                List<Status> statuses = data.getStatusService().findAll();
                selectStatus = new Select("Postion Status");
                for (Status status : statuses) {
                    if (!(status.getStatus().equals("OPEN") || status.getStatus().equals("CLOSED"))) {
                        selectStatus.addItem(status.getStatus());
                    }
                }
                selectStatus.setNewItemsAllowed(false);
                selectStatus.addListener(this);
                selectStatus.setImmediate(true);
                selectStatus.setWidth("250");
                selectStatus.setRequired(true);
                return selectStatus;
            } else if ("dob".equals(propertyId)) {
                field = new DateField("Date of Birth:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
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
            } else if (property == selectFacilityList) {
                if (property.getValue() != null) {
                    facility = data.getFacilityService().find(Long.parseLong(property.getValue().toString()));
                    positions = facility.getPositions();
                    if (!selectPositionList.isReadOnly()) {
                        selectPositionList.removeAllItems();
                    }
                    for (Positions pos : positions) {

                        if (st.checkPositionAvalaibality(pos)) {
                            selectPositionList.addItem(pos.getPositionCode());
                        }
                    }
                } else {
                    if (!selectPositionList.isReadOnly()) {
                        selectPositionList.removeAllItems();
                    }
                }
            }
        }
    }

    class PersonGridForm extends Form {

        private GridLayout layout;

        public PersonGridForm() {
            setCaption("Person Form");
            layout = new GridLayout(2, 11);
            layout.setMargin(true);
            layout.setSpacing(true);

            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Person Demographics");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);


            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 10, 1, 10);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("personSurname")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("personName")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("personOtherName")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("dob")) {
                layout.addComponent(field, 0, 4);
            } else if (propertyId.equals("gender")) {
                layout.addComponent(field, 0, 5);
            } else if (propertyId.equals("countyName")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("districtName")) {
                layout.addComponent(field, 1, 4);
            } else if (propertyId.equals("residence")) {
                layout.addComponent(field, 1, 5);
            } else if (propertyId.equals("personId")) {
                layout.addComponent(field, 1, 9);
            } else if (propertyId.equals("countryName")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("provinceName")) {
                layout.addComponent(field, 1, 2);

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
