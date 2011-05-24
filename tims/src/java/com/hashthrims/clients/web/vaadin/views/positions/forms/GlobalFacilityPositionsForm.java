/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.forms;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.forms.position.GlobalFacilityPositionsGridForm;
import com.hashthrims.clients.web.vaadin.views.positions.table.FacilityPositionTable;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class GlobalFacilityPositionsForm {


    private static final ClientDataService data = new ClientDataService();
    private List<Positions> positions =  new ArrayList<Positions>();
    private  FacilityPositionTable table;
    private final HashThrimsMain main;

    public GlobalFacilityPositionsForm(HashThrimsMain app) {
        main=app;
        table = new FacilityPositionTable(main,positions);
    }

    public Form createPositionFrom() {
        final Form form = new GlobalFacilityPositionsGridForm();
        form.setImmediate(false);
        form.setWriteThrough(false);
        form.setFormFieldFactory(new FormFields());
        return form;
    }

    /**
     * @return the positions
     */
    public List<Positions> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<Positions> positions) {
        this.positions = positions;
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

    public class FormFields extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectFacility;
        private List<Facility> facilities;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("positionId".equals(propertyId)) {
                field = new TextField("Position ID:");
                ((TextField) field).setVisible(false);

            } else if ("facilities".equals(propertyId)) {
                setSelectFacility(new Select("Select Facility:"));
                getSelectFacility().addListener(this);
                getSelectFacility().setImmediate(true);

                facilities = data.getFacilityService().findAll();
                Collections.sort(facilities);
                for (Facility f : facilities) {
                    getSelectFacility().addItem(f.getId());
                    getSelectFacility().setItemCaption(f.getId(), f.getFacilityName());
                }
                getSelectFacility().setNewItemsAllowed(false);
                getSelectFacility().setWidth("500");
                getSelectFacility().setRequired(true);
                return getSelectFacility();
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();

            Facility facility = null;
            if (property.getValue()!=null) {
                facility = data.getFacilityService().find(new Long(property.toString()));
                positions = facility.getPositions();
                setTable(new FacilityPositionTable(main,positions));                
            } else {
                positions= new ArrayList<Positions>();
                 setTable(new FacilityPositionTable(main, positions));   
            }
            
            
        }

        /**
         * @return the selectFacility
         */
        public Select getSelectFacility() {
            return selectFacility;
        }

        /**
         * @param selectFacility the selectFacility to set
         */
        public void setSelectFacility(Select selectFacility) {
            this.selectFacility = selectFacility;
        }

       
    }


}
