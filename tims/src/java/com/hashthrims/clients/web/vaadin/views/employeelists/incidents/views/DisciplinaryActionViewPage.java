/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.forms.DisciplinaryActionListForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.model.DisciplinaryActionListBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.incidents.table.DisciplinaryActionListTable;
import com.hashthrims.domain.DisciplinaryAction;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
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
public class DisciplinaryActionViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private DisciplinaryActionListForm cf;
    private ClientDataService data = new ClientDataService();
    private DisciplinaryActionListTable table;

    public DisciplinaryActionViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new DisciplinaryActionListForm();
        form = cf.createDisciplinaryActionFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        DisciplinaryActionListBean bean = new DisciplinaryActionListBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new DisciplinaryActionListTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            DisciplinaryActionListBean disciplinaryAction = new DisciplinaryActionListBean();
            disciplinaryAction.setDisplineName(record.getItemProperty("Disciplinary Action List").toString());
            disciplinaryAction.setDisciplinaryTypeListId(new Long(table.getValue().toString()));

            if (disciplinaryAction != form.getItemDataSource()) {
                BeanItem item = new BeanItem(disciplinaryAction);
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
        EmployeeListMenuView.inciSate = "DISCIPLINARY ACTION TYPE";
        if (source == cf.getSave()) {
            saveNewDisciplinaryAction(form);
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
            saveEditedDisciplinaryAction(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));
        } else if (source == cf.getDelete()) {
            deleteDisciplinaryAction(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.inciSate));

        }

    }

    public void saveNewDisciplinaryAction(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String displineName = form.getField("displineName").toString();
        DisciplineActionTypeList dcl = factory.createDisciplineActionList(displineName);
        data.getDisciplineActionType().persist(dcl);

    }

    public void saveEditedDisciplinaryAction(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        String displineName = form.getField("displineName").toString();
        Long disciplinaryTypeListId = Long.parseLong(form.getField("disciplinaryTypeListId").getValue().toString());
        DisciplineActionTypeList dcl = factory.updateDisciplineActionList(displineName, disciplinaryTypeListId);
        data.getDisciplineActionType().persist(dcl);

    }

    public void deleteDisciplinaryAction(Form form) {
        EmployeeFactory factory = data.getEmployeeFactory();
        Long disciplinaryTypeListId = Long.parseLong(form.getField("disciplinaryTypeListId").getValue().toString());
        DisciplineActionTypeList dcl = factory.loadDisciplinaryActionListService(disciplinaryTypeListId);
        data.getDisciplineActionType().remove(dcl);
    }
}
