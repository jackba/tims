/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.forms.StatusForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.StatusBean;
import com.hashthrims.clients.web.vaadin.views.positions.table.StatusTable;
import com.hashthrims.domain.positions.Status;

import com.hashthrims.infrastructure.factories.StatusFactory;

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
public class StatusViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final StatusForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final StatusTable table;
    private final StatusFactory factory = data.getStatusFactory();

    public StatusViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new StatusForm();
        form = pform.createStatusFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final StatusBean bean = new StatusBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new StatusTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final StatusBean status = new StatusBean();
            status.setStatusName(record.getItemProperty("Status").toString());
            status.setStatusId(new Long(table.getValue().toString()));

            if (status != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(status);
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
            saveNewStatus(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "STATUS"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "STATUS"));
        } else if (source == pform.getUpdate()) {
            saveEditedStatus(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "STATUS"));
        } else if (source == pform.getDelete()) {
            deleteStatus(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "STATUS"));
        }
    }

    public void saveNewStatus(Form form) {
        final String statusName = form.getField("statusName").getValue().toString();
        final Status s = factory.createStatus(statusName);
        data.getStatusService().persist(s);
    }

    public void saveEditedStatus(Form form) {
        final String statusName = form.getField("statusName").getValue().toString();
        final Long statusId = Long.parseLong(form.getField("statusId").getValue().toString());
        final Status s = factory.updateStatus(statusName, statusId);
        data.getStatusService().merge(s);
    }

    public void deleteStatus(Form form) {
        final Long statusId = Long.parseLong(form.getField("statusId").getValue().toString());
        final Status c = factory.loadStatus(statusId);
        data.getStatusService().remove(c);
    }
}
