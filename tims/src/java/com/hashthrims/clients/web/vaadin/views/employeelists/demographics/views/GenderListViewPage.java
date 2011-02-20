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
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model.GenderListBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table.GenderListTable;
import com.hashthrims.domain.employeelist.GenderList;
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
public class GenderListViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private GenderListForm cf;
    private ClientDataService data = new ClientDataService();
    private GenderListTable table;

    public GenderListViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new GenderListForm();
        form = cf.createGenderListFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        GenderListBean bean = new GenderListBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new GenderListTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            GenderListBean genderList = new GenderListBean();

            genderList.setGenderListName(record.getItemProperty("Gender").toString());
            genderList.setGenderId(new Long(table.getValue().toString()));

            if (genderList != form.getItemDataSource()) {
                BeanItem item = new BeanItem(genderList);
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
        EmployeeListMenuView.demoSate = "GENDER LIST";
        if (source == cf.getSave()) {
            saveNewGenderList(form);
            
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
            saveEditedGenderList(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        } else if (source == cf.getDelete()) {
            deleteGenderList(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        }

    }

    public void saveNewGenderList(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String genderListName = form.getField("genderListName").toString();
        GenderList c = factory.createGenderList(genderListName);
        data.getGenderListService().persist(c);

    }

    public void saveEditedGenderList(Form form) {
       EmployeeFactory factory = data.getEmployeeFactory();
        String genderListName = form.getField("genderListName").toString();
        Long GenderListId = Long.parseLong(form.getField("genderId").getValue().toString());
        GenderList sg = factory.updatedGenderList(genderListName, GenderListId);
        data.getGenderListService().merge(sg);
    }

    public void deleteGenderList(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        Long genderListId = Long.parseLong(form.getField("genderId").getValue().toString());
        GenderList c = factory.loadGenderList(genderListId);
        data.getGenderListService().remove(c);
    }
}
