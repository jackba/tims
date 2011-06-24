/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.location.LocationMenuView;
import com.hashthrims.clients.web.vaadin.views.location.model.ClustersBean;
import com.hashthrims.clients.web.vaadin.views.location.forms.ClustersForm;
import com.hashthrims.clients.web.vaadin.views.location.tables.ClustersTable;
import com.hashthrims.domain.offices.Clusters;
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
public class ClustersViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ClustersForm cf;
    private final ClientDataService data = new ClientDataService();
    final private LocationFactory factory = data.getClientDataFactory();
    private final ClustersTable table;

    public ClustersViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new ClustersForm();
        form = cf.createClustersFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final ClustersBean bean = new ClustersBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new ClustersTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final ClustersBean clusters = new ClustersBean();
            clusters.setClusterName(record.getItemProperty("Cluster Name").toString());
            clusters.setId(new Long(table.getValue().toString()));

            if (clusters != form.getItemDataSource()) {
                BeanItem item = new BeanItem(clusters);
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
            saveNewClusters(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "CLUSTERS"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new LocationMenuView(main, "CLUSTERS"));
        } else if (source == cf.getUpdate()) {
            saveEditedClusters(form);

            main.mainView.setSecondComponent(new LocationMenuView(main, "CLUSTERS"));

        } else if (source == cf.getDelete()) {
            deleteClusters(form);
            main.mainView.setSecondComponent(new LocationMenuView(main, "CLUSTERS"));

        }

    }

    public void saveNewClusters(Form form) {
        final String clustersName = form.getField("clusterName").getValue().toString();
        final Clusters c = factory.createClusters(clustersName);
        data.getClustersService().persist(c);

    }

    public void saveEditedClusters(Form form) {
        final String clustersName = form.getField("clusterName").getValue().toString();
        final Long clustersId = Long.parseLong(form.getField("id").getValue().toString());
        final Clusters c = factory.updatedClusters(clustersName, clustersId);
        data.getClustersService().merge(c);
    }

    public void deleteClusters(Form form) {
        final Long ClustersId = Long.parseLong(form.getField("id").getValue().toString());
        final Clusters c = factory.loadClusters(ClustersId);
        data.getClustersService().remove(c);
    }
}
