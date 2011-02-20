/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.forms.AccidentTypeListForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.model.AccidentTypeListBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.table.AccidentTypeListTable;
import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.infrastructure.factories.employeelist.AccidentTypeListFactory;

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
public class AccidentTypeListViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private AccidentTypeListForm cf;
   private static ClientDataService data = new ClientDataService();
    private AccidentTypeListTable table;

    public AccidentTypeListViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new AccidentTypeListForm();
        form = cf.createAccidentTypeListForm();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        AccidentTypeListBean bean = new AccidentTypeListBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new AccidentTypeListTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            AccidentTypeListBean jc = new AccidentTypeListBean();
            jc.setAccidentName(record.getItemProperty("Accident Name").toString());
            jc.setAccId(new Long(table.getValue().toString()));

            if (jc != form.getItemDataSource()) {
                BeanItem item = new BeanItem(jc);
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
         EmployeeListMenuView.inciSate = "ACCIDENT TYPE LIST";
        final Button source = event.getButton();
        if (source == cf.getSave()) {
            saveNewAccidentTypeList(form);
           
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));
        } else if (source == cf.getUpdate()) {
            saveEditedAccidentTypeList(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));

        } else if (source == cf.getDelete()) {
            deleteAccidentTypeList(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));

        }

    }


    public void saveNewAccidentTypeList(Form form) {
        AccidentTypeListFactory factory = data.getAccidentTypeListFactory();
        String accidentName = form.getField("accidentName").getValue().toString();
        AccidentTypeList c = factory.createAccidentType(accidentName);
        data.getAccidentType().persist(c);

    }

    public void saveEditedAccidentTypeList(Form form) {
        AccidentTypeListFactory factory = data.getAccidentTypeListFactory();
        String accidentName = form.getField("accidentName").getValue().toString();
        Long accId = Long.parseLong(form.getField("accId").getValue().toString());
        AccidentTypeList c = factory.updatedAccidentTypeList(accidentName, accId);
        data.getAccidentType().merge(c);
    }

    public void deleteAccidentTypeList(Form form) {
        AccidentTypeListFactory factory = data.getAccidentTypeListFactory();
        Long accId = Long.parseLong(form.getField("accId").getValue().toString());
        AccidentTypeList c = factory.loadAccidentType(accId);
        data.getAccidentType().remove(c);
    }
}
