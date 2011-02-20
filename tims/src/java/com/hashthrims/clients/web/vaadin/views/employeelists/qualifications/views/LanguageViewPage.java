/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.forms.LanguageForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.model.LanguageBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.table.LanguageTable;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.infrastructure.factories.employeelist.LanguageFactory;
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
public class LanguageViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private LanguageForm cf;
   private static ClientDataService data = new ClientDataService();
    private LanguageTable table;

    public LanguageViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new LanguageForm();
        form = cf.createLanguageFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        LanguageBean bean = new LanguageBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new LanguageTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            LanguageBean language = new LanguageBean();
            language.setLanguageName(record.getItemProperty("Languages").toString());
            language.setLanguageId(new Long(table.getValue().toString()));
            if (language != form.getItemDataSource()) {
                BeanItem item = new BeanItem(language);
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
        EmployeeListMenuView.qualSate = "LANGUAGE";
       if (source == cf.getSave()) {
            saveNewLanguage(form);
            
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
            saveEditedLanguage(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));

        } else if (source == cf.getDelete()) {
            deleteLanguage(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));

        }

    }

    public void saveNewLanguage(Form form) {
        LanguageFactory factory = data.getLanguageFactory();
        String name = form.getField("languageName").getValue().toString();
        Language l = factory.createLanguage(name);
        data.getLanguageService().persist(l);

    }

    public void saveEditedLanguage(Form form) {
        LanguageFactory factory = data.getLanguageFactory();
        String name = form.getField("languageName").getValue().toString();
        Long languageId = Long.parseLong(form.getField("languageId").getValue().toString());
        Language l = factory.updatedLanguage(name, languageId);
        data.getLanguageService().merge(l);
    }

    public void deleteLanguage(Form form) {
        LanguageFactory factory = data.getLanguageFactory();
        Long languageId = Long.parseLong(form.getField("languageId").getValue().toString());
        Language l = factory.loadLanguage(languageId);
        data.getLanguageService().remove(l);
    }
}
