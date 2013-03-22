/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class TrainedLocationCombo extends HorizontalLayout implements Property.ValueChangeListener {

    private List<Province> provinces = new ArrayList<Province>();
    private List<County> districts = new ArrayList<County>();
    private List<District> subDistricts = new ArrayList<District>();
    private List<City> cities = new ArrayList<City>();
    private ComboBox province = new ComboBox("Province");
    private ComboBox district = new ComboBox("District");
    private ComboBox subDistrict = new ComboBox("Sub District");
    private ComboBox city = new ComboBox("City");
    private ComboBox facility = new ComboBox("Facility");
    private static final ClientDataService data = new ClientDataService();

    public TrainedLocationCombo() {
        setSizeFull();
        province.addListener((ValueChangeListener) this);
        province.setImmediate(true);
        district.addListener((ValueChangeListener) this);
        district.setImmediate(true);
        subDistrict.addListener((ValueChangeListener) this);
        subDistrict.setImmediate(true);
        city.addListener((ValueChangeListener) this);
        city.setImmediate(true);

        provinces = data.getRegionService().findAll();

        for (Province prv : provinces) {
            province.addItem(prv.getId());
            province.setItemCaption(prv.getId(), prv.getRegionName());

        }



        addComponent(province);
        addComponent(district);
        addComponent(subDistrict);
        addComponent(city);
        addComponent(facility);
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == getProvince()) {
            Province p = null;
            if (getProvince().getValue() != null) {
                p = data.getRegionService().find(new Long(getProvince().getValue().toString()));
                setDistricts(p.getCounty());
            }

            getDistrict().removeAllItems();
            for (County dist : getDistricts()) {
                getDistrict().addItem(dist.getId());
                getDistrict().setItemCaption(dist.getId(), dist.getCountyName());
            }


        } else if (property == getDistrict()) {
            getSubDistrict().removeAllItems();
            County dist = null;
            if (getDistrict().getValue() != null) {
                dist = data.getCountyService().find(new Long(getDistrict().getValue().toString()));
                setSubDistricts(dist.getDistrict());
            }

            for (District subd : getSubDistricts()) {
                getSubDistrict().addItem(subd.getId());
                getSubDistrict().setItemCaption(subd.getId(), subd.getDistrictName());

            }
        } else if (property == getSubDistrict()) {
            District subdist = null;
            if (getSubDistrict().getValue() != null) {
                subdist = data.getDistrictService().find(new Long(getSubDistrict().getValue().toString()));
                setCities(subdist.getCities());
            }

            getCity().removeAllItems();
            for (City ct : getCities()) {
                getCity().addItem(ct.getId());
                getCity().setItemCaption(ct.getId(), ct.getName());

            }


        } else if (property == getCity()) {
            City cty = null;
            if (getCity().getValue() != null) {
                cty = data.getCityService().find(new Long(new Long(getCity().getValue().toString())));
                List<Facility> facilities = data.getFacilityService().findAll();
                getFacility().removeAllItems();
                for (Facility fac : facilities) {
                    if (cty.equals(fac.getCity())) {
                        getFacility().addItem(fac.getId());
                        getFacility().setItemCaption(fac.getId(), fac.getFacilityName());
                    }

                }
            }


        }
    }

    /**
     * @return the provinces
     */
    public List<Province> getProvinces() {
        return provinces;
    }

    /**
     * @param provinces the provinces to set
     */
    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    /**
     * @return the districts
     */
    public List<County> getDistricts() {
        return districts;
    }

    /**
     * @param districts the districts to set
     */
    public void setDistricts(List<County> districts) {
        this.districts = districts;
    }

    /**
     * @return the subDistricts
     */
    public List<District> getSubDistricts() {
        return subDistricts;
    }

    /**
     * @param subDistricts the subDistricts to set
     */
    public void setSubDistricts(List<District> subDistricts) {
        this.subDistricts = subDistricts;
    }

    /**
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * @param cities the cities to set
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * @return the province
     */
    public ComboBox getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(ComboBox province) {
        this.province = province;
    }

    /**
     * @return the district
     */
    public ComboBox getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(ComboBox district) {
        this.district = district;
    }

    /**
     * @return the subDistrict
     */
    public ComboBox getSubDistrict() {
        return subDistrict;
    }

    /**
     * @param subDistrict the subDistrict to set
     */
    public void setSubDistrict(ComboBox subDistrict) {
        this.subDistrict = subDistrict;
    }

    /**
     * @return the city
     */
    public ComboBox getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(ComboBox city) {
        this.city = city;
    }

    /**
     * @return the facility
     */
    public ComboBox getFacility() {
        return facility;
    }

    /**
     * @param facility the facility to set
     */
    public void setFacility(ComboBox facility) {
        this.facility = facility;
    }
}
