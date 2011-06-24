/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.NodesBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.NodesForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.NodesTable;
import com.hashthrims.domain.offices.Nodes;
import com.hashthrims.infrastructure.factories.LocationFactory;
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
public class NodesViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final NodesForm cf;
    private final ClientDataService data = new ClientDataService();
    final private LocationFactory factory = data.getClientDataFactory();
    private final NodesTable table;

    public NodesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new NodesForm();
        form = cf.createNodesFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final NodesBean bean = new NodesBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new NodesTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final NodesBean Nodes = new NodesBean();
            Nodes.setNodeName(record.getItemProperty("Node Name").toString());
            Nodes.setId(new Long(table.getValue().toString()));

            if (Nodes != form.getItemDataSource()) {
                BeanItem item = new BeanItem(Nodes);
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
            saveNewNodes(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "NODE"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main, "NODE"));
        } else if (source == cf.getUpdate()) {
            saveEditedNodes(form);

            main.mainView.setSecondComponent(new LocationMenuView(main, "NODE"));

        } else if (source == cf.getDelete()) {
            deleteNodes(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "NODE"));

        }

    }

    public void saveNewNodes(Form form) {
        final String nodesName = form.getField("nodeName").getValue().toString();
        final Nodes c = factory.createNodes(nodesName);
        data.getNodesService().persist(c);

    }

    public void saveEditedNodes(Form form) {
        final String nodesName = form.getField("nodeName").getValue().toString();
        final Long NodesId = Long.parseLong(form.getField("id").getValue().toString());
        final Nodes c = factory.updatedNodes(nodesName, NodesId);
        data.getNodesService().merge(c);
    }

    public void deleteNodes(Form form) {
        final Long nodesId = Long.parseLong(form.getField("id").getValue().toString());
        final Nodes c = factory.loadNodes(nodesId);
        data.getNodesService().remove(c);
    }
}
