/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.DistrictBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.DistrictForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.DistrictTable;
import com.hashthrims.domain.regionlist.County;
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
public class DistrictViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener{

    private final HashThrimsMain main;
    private final Form form;
    private final DistrictForm pform;
    private final ClientDataService data = new ClientDataService();
    private final DistrictTable table;
    private final LocationFactory factory = data.getClientDataFactory();

    public DistrictViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new DistrictForm();
        form = pform.createDistrictFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final DistrictBean bean = new DistrictBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new DistrictTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final DistrictBean district = new DistrictBean();
            district.setDistrictCode(record.getItemProperty("Sub District Code").toString());
            district.setDistrictId(new Long(table.getValue().toString()));
            district.setDistrictName(record.getItemProperty("Sub District Name").toString());
            district.setCounty(record.getItemProperty("District").getValue().toString());
            if (district != form.getItemDataSource()) {
                BeanItem item = new BeanItem(district);
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
            saveNewDistrict(form);
            main.mainView.setSecondComponent(new LocationMenuView(main,"DISTRICT"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main,"DISTRICT"));
        } else if (source == pform.getUpdate()) {
            saveEditedDistrict(form);

            main.mainView.setSecondComponent(new LocationMenuView(main,"DISTRICT"));

        } else if (source == pform.getDelete()) {
            deleteDistrict(form);
            main.mainView.setSecondComponent(new LocationMenuView(main,"DISTRICT"));

        }

    }

    public void saveNewDistrict(Form form) {
        
        final String districtName = form.getField("districtName").getValue().toString();
        final String districtCode = form.getField("districtCode").getValue().toString();
        final String county = form.getField("county").getValue().toString();
        final County d = factory.createDistrict(districtName, districtCode, county);
        data.getCountyService().persist(d);

    }

    public void saveEditedDistrict(Form form) {

        final String districtName = form.getField("districtName").getValue().toString();
        final String districtCode = form.getField("districtCode").getValue().toString();
        final String  county = form.getField("county").getValue().toString();
        final Long districtId = Long.parseLong(form.getField("districtId").getValue().toString());
        final County c = factory.updateDistrict(districtName,  districtCode, county, districtId);
        data.getCountyService().merge(c);
    }

    public void deleteDistrict(Form form) {
        
        final Long districtId = Long.parseLong(form.getField("districtId").getValue().toString());
       final  District c = factory.loadDistrict(districtId);
        data.getDistrictService().remove(c);
    }
}
