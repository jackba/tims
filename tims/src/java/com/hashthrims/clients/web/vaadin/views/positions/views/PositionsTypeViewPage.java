/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.forms.PositionsTypeForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.PositionTypeBean;
import com.hashthrims.clients.web.vaadin.views.positions.table.PositionTypeTable;

import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
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
public class PositionsTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final PositionsTypeForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final PositionTypeTable table;
    private final PositionsFactory factory = data.getPositionFactory();
  

    public PositionsTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new PositionsTypeForm();
        form = pform.createPositionsTypeFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final PositionTypeBean bean = new PositionTypeBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new PositionTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final PositionTypeBean positionsType = new PositionTypeBean();
            positionsType.setPositionType(record.getItemProperty("Position Type").toString());
            positionsType.setPositionId(new Long(table.getValue().toString()));

            if (positionsType != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(positionsType);
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
            saveNewPositionType(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "TYPE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "TYPE"));
        } else if (source == pform.getUpdate()) {
            saveEditedPositionType(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "TYPE"));
        } else if (source == pform.getDelete()) {
            deletePositionType(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "TYPE"));
        }
    }

    public void saveNewPositionType(Form form) {
        final String positionType = form.getField("positionType").getValue().toString();
        final PositionTypes p = factory.createPositionTypes(positionType);
        data.getPositionTypes().persist(p);
    }

    public void saveEditedPositionType(Form form) {
        final String positionType = form.getField("positionType").getValue().toString();
        final Long positionTypeId = Long.parseLong(form.getField("positionId").getValue().toString());
        final PositionTypes p = factory.updatedPositionTypes(positionType, positionTypeId);
        data.getPositionTypes().merge(p);
    }

    public void deletePositionType(Form form) {
        final Long positionTypeId = Long.parseLong(form.getField("positionId").getValue().toString());
        final PositionTypes p = factory.loadPositionTypes(positionTypeId);
        data.getPositionTypes().remove(p);
    }
}
