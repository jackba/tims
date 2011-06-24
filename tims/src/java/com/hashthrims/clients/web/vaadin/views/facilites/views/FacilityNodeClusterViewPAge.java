/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.facilites.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.facilites.OrganisationListMenuView;
import com.hashthrims.domain.offices.Clusters;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityGrouping;
import com.hashthrims.domain.offices.Nodes;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class FacilityNodeClusterViewPAge extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HorizontalSplitPanel horizotalPabel = new HorizontalSplitPanel();
    private final Button cancelButton = new Button("Cancel Process");
    private final Button addToNodeAndCluster = new Button("Add to Node and Cluster");
    private final VerticalLayout nodeAndClusterPanel = new VerticalLayout();
    private final VerticalLayout currentNodeAndClusterPanel = new VerticalLayout();
    private final ClientDataService data = new ClientDataService();
    private final ListSelect facilitiesList = new ListSelect("Please Select Facility");
    private final VerticalLayout leftWindow = new VerticalLayout();
    private final ComboBox nodesList = new ComboBox("Select Node ");
    private final ComboBox clustersList = new ComboBox("Select Clusters ");
    private final HorizontalLayout footer = new HorizontalLayout();
    private Collection<Long> facilitiesId;
    private final FacilityGrouping nodeAndCluster = new FacilityGrouping();
    private final VerticalLayout rightWindow = new VerticalLayout();
    private final HashThrimsMain main;

    public FacilityNodeClusterViewPAge(HashThrimsMain app) {
        main = app;


        cancelButton.addListener((ClickListener) this);
        addToNodeAndCluster.addListener((ClickListener) this);

        nodesList.addListener((ValueChangeListener) this);
        facilitiesList.addListener((ValueChangeListener) this);
        clustersList.addListener((ValueChangeListener) this);

        facilitiesList.setWidth("350px");

        facilitiesList.setNullSelectionAllowed(true);
        facilitiesList.setMultiSelect(true);
        facilitiesList.setImmediate(true);
        leftWindow.setMargin(true);
        List<Facility> facilities = data.getFacilityService().findAll();
        Collections.sort(facilities);
        for (Facility facility : facilities) {
            facilitiesList.setItemCaption(facility.getId(), facility.getFacilityName());
            facilitiesList.addItem(facility.getId());
        }
        leftWindow.addComponent(facilitiesList);
        horizotalPabel.setFirstComponent(leftWindow);
        horizotalPabel.addStyleName(Reindeer.SPLITPANEL_SMALL);
        horizotalPabel.setLocked(true);

        List<Nodes> nodes = data.getNodesService().findAll();
        for (Nodes node : nodes) {
            nodesList.setItemCaption(node.getId(), node.getNodesName());
            nodesList.addItem(node.getId());
        }

        List<Clusters> clusters = data.getClustersService().findAll();
        for (Clusters cluster : clusters) {
            clustersList.setItemCaption(cluster.getId(), cluster.getClusterName());
            clustersList.addItem(cluster.getId());
        }
        nodeAndClusterPanel.addComponent(nodesList);
        nodeAndClusterPanel.addComponent(clustersList);

        Panel curr = new Panel("Current Node and Cluster");
        
        curr.addComponent(currentNodeAndClusterPanel);

        Panel panel = new Panel("Node and Cluster");
        panel.addComponent(nodeAndClusterPanel);

        rightWindow.addComponent(curr);
        rightWindow.addComponent(panel);

        horizotalPabel.addComponent(rightWindow);

        addComponent(horizotalPabel);

        footer.addComponent(addToNodeAndCluster);
        footer.addComponent(cancelButton);
        addComponent(footer);
        setComponentAlignment(footer, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == addToNodeAndCluster) {
            for (Long faclityId : facilitiesId) {
                Facility fac = data.getFacilityService().find(faclityId);
                fac.setFacilityGrouping(nodeAndCluster);
                data.getFacilityService().merge(fac);
                main.mainView.setSecondComponent(new OrganisationListMenuView(main, "NODE"));
            }
        } else {
             main.mainView.setSecondComponent(new OrganisationListMenuView(main, "NODE"));
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == nodesList) {
            Long nodeId = (Long) property.getValue();
            Nodes node = data.getNodesService().find(nodeId);
            nodeAndCluster.setNode(node.getNodesName());
        }
        if (property == facilitiesList) {
            facilitiesId = (Collection<Long>) property.getValue();
            addCurrentGroping(facilitiesId);
        }
        if (property == clustersList) {
            Long clusterId = (Long) property.getValue();
            Clusters cluster = data.getClustersService().find(clusterId);
            nodeAndCluster.setCluster(cluster.getClusterName());
        }
    }

    private void addCurrentGroping(Collection<Long> facilitiesId) {
        if (facilitiesId.size() == 1) {
            currentNodeAndClusterPanel.removeAllComponents();
            for (Long faclityId : facilitiesId) {
                Facility facility = data.getFacilityService().find(faclityId);
                if (facility.getFacilityGrouping() != null) {
                    currentNodeAndClusterPanel.addComponent(new Label(" Node : " + getNode(facility.getFacilityGrouping())));
                    currentNodeAndClusterPanel.addComponent(new Label(" Cluster : " + getCLuster(facility.getFacilityGrouping())));
                } else {
                    currentNodeAndClusterPanel.addComponent(new Label(" Node : NODE NOT SET"));
                    currentNodeAndClusterPanel.addComponent(new Label(" Cluster : CLUSTER NOT SET"));
                }
            }


        } else {
            currentNodeAndClusterPanel.removeAllComponents();
            currentNodeAndClusterPanel.addComponent(new Label(" Mutiple Facilities or No Facility Selected"));

        }
    }

    private String getNode(FacilityGrouping facilityGrouping) {
        if (facilityGrouping != null) {
            return facilityGrouping.getNode();
        }
        return "NODE NOT SET";
    }

    private String getCLuster(FacilityGrouping facilityGrouping) {
        if (facilityGrouping != null) {
            return facilityGrouping.getCluster();
        }
        return "CLUSTER NOT SET";
    }
}
