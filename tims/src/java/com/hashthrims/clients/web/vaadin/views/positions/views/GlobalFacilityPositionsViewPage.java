/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;

import com.hashthrims.clients.web.vaadin.views.positions.table.FacilityPositionTable;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Runo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class GlobalFacilityPositionsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private static final ClientDataService data = new ClientDataService();
    // private final FacilityPositionTable table;
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final PositionsFactory factory = data.getPositionFactory();
    private final PositionTableHeaders tb = new PositionTableHeaders();
    private VerticalSplitPanel vert = new VerticalSplitPanel();
    private List<Positions> positions = new ArrayList<Positions>();
    private FacilityPositionTable table;

    public GlobalFacilityPositionsViewPage(HashThrimsMain app) {

        main = app;
        table= new FacilityPositionTable(main,positions);
        setSizeFull();
        ComboBox faciliList = new ComboBox("Please select a Facility");
        faciliList.setCaption("Facility Names");
        faciliList.setDescription("Facility Names");

        List<Facility> facilities = data.getFacilityService().findAll();
        Collections.sort(facilities);
        for (Facility f : facilities) {
            faciliList.addItem(f.getId());
            faciliList.setItemCaption(f.getId(), f.getFacilityName());
        }
        faciliList.setNewItemsAllowed(false);
        faciliList.setWidth("500");
        faciliList.setRequired(true);


        faciliList.setFilteringMode(Filtering.FILTERINGMODE_STARTSWITH);
        faciliList.setImmediate(true);
        faciliList.addListener(this);

        vert.setHeight("600px");
        vert.setWidth("100%");
        vert.setSplitPosition(150, Sizeable.UNITS_PIXELS);
        vert.setFirstComponent(faciliList);
        vert.setSecondComponent(table);
        Label selectFacility = new Label("Select Facility :");
        selectFacility.setStyleName(Runo.LABEL_H2);
        addComponent(selectFacility);
        addComponent(vert);
        setComponentAlignment(vert, Alignment.TOP_CENTER);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
       
        Facility facility = null;
        if (property.getValue() != null) {
            facility = data.getFacilityService().find(new Long(property.toString()));
            positions = facility.getPositions();
            setTable(new FacilityPositionTable(main,positions));
            vert.setSecondComponent(table);
        } else {
            positions = new ArrayList<Positions>();
            setTable(new FacilityPositionTable(main,positions));
            vert.setSecondComponent(table);
        }


    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();


    }

    public void saveNewPosition(Form form) {
    }

    public void saveEditedPosition(Form form) {
    }

    public void deletePosition(Form form) {
    }

    /**
     * @param vert the vert to set
     */
    public void setVert(VerticalSplitPanel vert) {
        this.vert = vert;
    }

    /**
     * @return the table
     */
    public FacilityPositionTable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(FacilityPositionTable table) {
        this.table = table;
    }
}
