/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.forms.DepartureReasonsForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.model.DepartureReasonsBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.table.DepartureReasonsTable;
import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.infrastructure.factories.employeelist.DepartureReasonsFactory;

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
public class DepartureReasonsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private DepartureReasonsForm cf;
   private static ClientDataService data = new ClientDataService();
    private DepartureReasonsTable table;

    public DepartureReasonsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new DepartureReasonsForm();
        form = cf.createDepartureReasonsForm();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        DepartureReasonsBean bean = new DepartureReasonsBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new DepartureReasonsTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            DepartureReasonsBean drbean = new DepartureReasonsBean();
            drbean.setReasonName(record.getItemProperty("Departure Reasons").toString());
            drbean.setDepId(new Long(table.getValue().toString()));
            if (drbean != form.getItemDataSource()) {
                BeanItem item = new BeanItem(drbean);
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
         EmployeeListMenuView.inciSate = "DEPARTURE REASONS";
        if (source == cf.getSave()) {
            saveNewDepartureReasons(form);
           
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
            saveEditedDepartureReasons(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));

        } else if (source == cf.getDelete()) {
            deleteDepartureReasons(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));

        }

    }

    public void saveNewDepartureReasons(Form form) {
        DepartureReasonsFactory factory = data.getDepartureReasonsFactory();
        String reasonName = form.getField("reasonName").getValue().toString();
        DepartureReasons dr = factory.createDepartureReasons(reasonName);
        data.getDepartureReasonsService().persist(dr);

    }

    public void saveEditedDepartureReasons(Form form) {
       DepartureReasonsFactory factory = data.getDepartureReasonsFactory();
        String reasonName = form.getField("reasonName").getValue().toString();
        Long depId = Long.parseLong(form.getField("depId").getValue().toString());
        DepartureReasons dr = factory.updatedDepartureReasons(reasonName, depId);
        data.getDepartureReasonsService().merge(dr);
    }

    public void deleteDepartureReasons(Form form) {
        DepartureReasonsFactory factory = data.getDepartureReasonsFactory();
        Long depId = Long.parseLong(form.getField("depId").getValue().toString());
        DepartureReasons dr = factory.loadDepartureReasons(depId);
        data.getDepartureReasonsService().remove(dr);
    }
}
