/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.forms.BenefitTypeForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model.BenefitTypeBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.demographics.table.BenefitTypeTable;
import com.hashthrims.domain.employeelist.BenefitType;
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
public class BenefitTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private BenefitTypeForm cf;
    private ClientDataService data = new ClientDataService();
    private BenefitTypeTable table;

    public BenefitTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new BenefitTypeForm();
        form = cf.createBenefitTypeFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        BenefitTypeBean bean = new BenefitTypeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new BenefitTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            BenefitTypeBean benefitType = new BenefitTypeBean();

            benefitType.setBenefitTypeName(record.getItemProperty("Benefit Type").toString());
            benefitType.setBenefitTypeId(new Long(table.getValue().toString()));

            if (benefitType != form.getItemDataSource()) {
                BeanItem item = new BeanItem(benefitType);
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
        EmployeeListMenuView.demoSate = "BENEFIT TYPE";
        if (source == cf.getSave()) {
            saveNewBenefitType(form);
            
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
            saveEditedBenefitType(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        } else if (source == cf.getDelete()) {
            deleteBenefitType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.demoSate));

        }

    }

    public void saveNewBenefitType(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String benefitTypeName = form.getField("benefitTypeName").toString();
        BenefitType c = factory.createBenefitType(benefitTypeName);
        data.getBenefitTypeService().persist(c);

    }

    public void saveEditedBenefitType(Form form) {
       EmployeeFactory factory = data.getEmployeeFactory();
        String benefitTypeName = form.getField("benefitTypeName").toString();
        Long benefitTypeId = Long.parseLong(form.getField("benefitTypeId").getValue().toString());
        BenefitType sg = factory.updatedBenefitType(benefitTypeName, benefitTypeId);
        data.getBenefitTypeService().merge(sg);
    }

    public void deleteBenefitType(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        Long benefitTypeId = Long.parseLong(form.getField("benefitTypeId").getValue().toString());
        BenefitType c = factory.loadBenefitType(benefitTypeId);
        data.getBenefitTypeService().remove(c);
    }
}
