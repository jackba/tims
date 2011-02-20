/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.CityBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.CityForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.CityTable;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.infrastructure.factories.LocationFactory;
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
public class CityViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener{

    private final HashThrimsMain main;
    private final Form form;
    private final CityForm pform;
    private final ClientDataService data = new ClientDataService();
    private final CityTable table;
    private final LocationFactory factory = data.getClientDataFactory();

    public CityViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CityForm();
        form = pform.createCityFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        CityBean bean = new CityBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CityTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final CityBean city = new CityBean();
            city.setCityCode(record.getItemProperty("City Code").toString());
            city.setCityId(new Long(table.getValue().toString()));
            city.setCityName(record.getItemProperty("City Name").toString());
            city.setDistrict(record.getItemProperty("District").getValue().toString());
            if (city != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(city);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(pform.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewCity(form);
            main.mainView.setSecondComponent(new LocationMenuView(main,"CITY"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main,"CITY"));
        } else if (source == pform.getUpdate()) {
            saveEditedCity(form);

            main.mainView.setSecondComponent(new LocationMenuView(main,"CITY"));

        } else if (source == pform.getDelete()) {
            deleteCity(form);
            main.mainView.setSecondComponent(new LocationMenuView(main,"CITY"));

        }

    }

    public void saveNewCity(Form form) {
        final String cityName = form.getField("cityName").getValue().toString();
        final String cityCode = form.getField("cityCode").getValue().toString();
        final String district = form.getField("district").getValue().toString();
        final District c = factory.createCity(cityName, cityCode, district);
        data.getDistrictService().persist(c);
    }

    public void saveEditedCity(Form form) {
        final String cityName = form.getField("cityName").getValue().toString();
        final String cityCode = form.getField("cityCode").getValue().toString();
        final String  district = form.getField("district").getValue().toString();
        final Long districtId = Long.parseLong(form.getField("cityId").getValue().toString());
        final District  c = factory.updateCity(cityName,  cityCode, district, districtId);
        data.getDistrictService().merge(c);
    }

    public void deleteCity(Form form) {
        final Long cityId = Long.parseLong(form.getField("cityId").getValue().toString());
        final City c = factory.loadCity(cityId);
        data.getCityService().remove(c);
    }
}
