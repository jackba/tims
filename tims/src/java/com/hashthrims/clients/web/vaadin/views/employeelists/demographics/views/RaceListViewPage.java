/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.forms.GenderListForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.forms.GenderListForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.forms.RaceListForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model.GenderListBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model.RaceListBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table.GenderListTable;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table.RaceListTable;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.infrastructure.factories.EmployeeFactory;
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
public class RaceListViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private RaceListForm cf;
    private ClientDataService data = new ClientDataService();
    private RaceListTable table;

    public RaceListViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new RaceListForm();
        form = cf.createRaceListFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        RaceListBean bean = new RaceListBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new RaceListTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            RaceListBean raceList = new RaceListBean();

            raceList.setRace(record.getItemProperty("Race").toString());
            raceList.setId(new Long(table.getValue().toString()));

            if (raceList != form.getItemDataSource()) {
                BeanItem item = new BeanItem(raceList);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
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
        EmployeeListMenuView.demoSate = "RACE LIST";
        if (source == cf.getSave()) {
            saveNewRaceList(form);
            
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));
        } else if (source == cf.getUpdate()) {
            saveEditedRaceList(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        } else if (source == cf.getDelete()) {
            deleteRaceList(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        }

    }

    public void saveNewRaceList(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String raceListName = form.getField("race").toString();
        RaceList c = factory.createRaceList(raceListName);
        data.getRaceListService().persist(c);

    }

    public void saveEditedRaceList(Form form) {
       EmployeeFactory factory = data.getEmployeeFactory();
        String raceListName = form.getField("race").toString();
        Long raceId = Long.parseLong(form.getField("id").getValue().toString());
        RaceList sg = factory.updatedRaceList(raceListName, raceId);
        data.getRaceListService().merge(sg);
    }

    public void deleteRaceList(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        Long raceListId = Long.parseLong(form.getField("id").getValue().toString());
        RaceList c = factory.loadRaceList(raceListId);
        data.getRaceListService().remove(c);
    }
}
