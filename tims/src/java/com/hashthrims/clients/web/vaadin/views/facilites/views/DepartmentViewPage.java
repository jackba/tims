/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.facilites.forms.DepartmentForm;
import com.hashthrims.clients.web.vaadin.views.facilites.table.DepartmentTable;
import com.hashthrims.domain.offices.Department;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.clients.web.vaadin.views.facilites.model.DepartmentBean;
import com.hashthrims.infrastructure.factories.offices.OrganisationListFactory;

/**
 *
 * @author boniface
 */
public class DepartmentViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final DepartmentForm cf;
    private final ClientDataService data = new ClientDataService();
    private final DepartmentTable table;
    private final OrganisationListFactory factory = data.getOfficeFactory();
   

    public DepartmentViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new DepartmentForm();
        form = cf.createDepartmentFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final DepartmentBean bean = new DepartmentBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new DepartmentTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final DepartmentBean department = new DepartmentBean();
            department.setDepartmentName(record.getItemProperty("Department Name").toString());
            department.setDepartmentId(new Long(table.getValue().toString()));
            if (department != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(department);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(cf.orderList());

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
        if (source == cf.getSave()) {
            saveNewDepartment(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "DEPARTMENT"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "DEPARTMENT"));
        } else if (source == cf.getUpdate()) {
            saveEditedDepartment(form);

            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "DEPARTMENT"));

        } else if (source == cf.getDelete()) {
            deleteDepartment(form);
            main.mainView.setSecondComponent(new OrganisationListMenuView(main, "DEPARTMENT"));

        }

    }

    public void saveNewDepartment(Form form) {
        
        final String departmentName = form.getField("departmentName").getValue().toString();
        final Department c = factory.createDepartment(departmentName);
        data.getDepartmentService().persist(c);

    }

    public void saveEditedDepartment(Form form) {
        final String departmentName = form.getField("departmentName").getValue().toString();
        final Long departmentId = Long.parseLong(form.getField("departmentId").getValue().toString());
        final Department c = factory.updateDepartment(departmentName, departmentId);
        data.getDepartmentService().merge(c);
    }

    public void deleteDepartment(Form form) {
        final Long departmentId = Long.parseLong(form.getField("departmentId").getValue().toString());
        final Department c = factory.loadDepartment(departmentId);
        data.getDepartmentService().remove(c);
    }
}
