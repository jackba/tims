/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.forms.EducationTypeForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.model.EducationTypeBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.table.EducationTypeTable;
import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.infrastructure.factories.employeelist.EducationTypeFactory;

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

/**
 *
 * @author boniface
 */
public class EducationTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private EducationTypeForm cf;
   private static ClientDataService data = new ClientDataService();
    private EducationTypeTable table;
    Panel p = new Panel(" View Pages ");

    public EducationTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new EducationTypeForm();
        form = cf.createEducationTypeForm();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        EducationTypeBean bean = new EducationTypeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new EducationTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            EducationTypeBean jc = new EducationTypeBean();
            jc.setEducTypeName(record.getItemProperty("Education Type").toString());
            jc.setEduId(new Long(table.getValue().toString()));

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
        final Button source = event.getButton();
        EmployeeListMenuView.qualSate = "EDUCATION TYPE";
        if (source == cf.getSave()) {
            saveNewEducationType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));
        } else if (source == cf.getUpdate()) {
            saveEditedEducationType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));
        } else if (source == cf.getDelete()) {
            deleteEducationType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));

        }
    }

    public void saveNewEducationType(Form form) {
       EducationTypeFactory factory = data.getEducationTypeFactory();
        String educTypeName = form.getField("educTypeName").getValue().toString();
        EducationType et = factory.createEducation(educTypeName);
        data.getEducationTypeService().persist(et);

    }

    public void saveEditedEducationType(Form form) {
        EducationTypeFactory factory = data.getEducationTypeFactory();
        String educTypeName = form.getField("educTypeName").getValue().toString();
        Long eduId = Long.parseLong(form.getField("eduId").getValue().toString());
        EducationType et = factory.updatedEducaction(educTypeName, eduId);
        data.getEducationTypeService().merge(et);
    }

    public void deleteEducationType(Form form) {
        EducationTypeFactory factory = data.getEducationTypeFactory();
        Long eduId = Long.parseLong(form.getField("eduId").getValue().toString());
        EducationType et = factory.loadEducaction(eduId);
        data.getEducationTypeService().remove(et);
    }
}
