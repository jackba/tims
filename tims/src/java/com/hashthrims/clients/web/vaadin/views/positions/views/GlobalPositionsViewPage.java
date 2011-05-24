/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;

import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.forms.GlobalPositionsForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.GlobalPositionsBean;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.StringValues;
import com.hashthrims.clients.web.vaadin.views.positions.table.GlobalPositionTable;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.GlobalPositions;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class GlobalPositionsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final GlobalPositionsForm pform = new GlobalPositionsForm();
    private final Form form = pform.createPositionFrom();
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final PositionsFactory factory = data.getPositionFactory();
    private final PositionTableHeaders tb = new PositionTableHeaders();
    private final GlobalPositionTable table;

    public GlobalPositionsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final GlobalPositionsBean bean = new GlobalPositionsBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new GlobalPositionTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);



    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final GlobalPositionsBean positionBean = new GlobalPositionsBean();
            positionBean.setPositionCode(record.getItemProperty("Position Code").toString());
            positionBean.setSupervisor(tb.getSupervisorId(record.getItemProperty("Supervisor").toString()));
            positionBean.setDepartment(tb.getDepartmentId(record.getItemProperty("Department").toString()));
            positionBean.setJob(tb.getPotisionTitleId(record.getItemProperty("Position Title").toString()));
            positionBean.setPositionType(tb.getPositionTypeId(record.getItemProperty("Position Type").toString()));
            positionBean.setPositionStatus(tb.getPositionStatusId(record.getItemProperty("Position Status").toString()));

            positionBean.setPositionId(new Long(table.getValue().toString()));

            if (positionBean != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(positionBean);
                form.setItemDataSource(item);

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
            saveNewPosition(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "GLOBAL"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "GLOBAL"));
        } else if (source == pform.getUpdate()) {
            saveEditedPosition(form);

            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "GLOBAL"));

        } else if (source == pform.getDelete()) {
            deletePosition(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "GLOBAL"));

        }

    }

    public void saveNewPosition(Form form) {

        final String positionCode = fieldValues.getStringFields(form.getField("positionCode").getValue());


        final StringValues val = new StringValues();
        val.setPositionCode(positionCode);

        final Long supervisor = fieldValues.getLongFields(form.getField("supervisor").getValue());
        final Long department = fieldValues.getLongFields(form.getField("department").getValue());
        final Long job = fieldValues.getLongFields(form.getField("job").getValue());
        final Long positionType = fieldValues.getLongFields(form.getField("positionType").getValue());
        final Long positionStatus = fieldValues.getLongFields(form.getField("positionStatus").getValue());

        final Map<String, Long> dfields = new HashMap<String, Long>();
        dfields.put("department", department);
        dfields.put("job", job);
        dfields.put("positionStatus", positionStatus);
        dfields.put("positionType", positionType);
        dfields.put("supervisor", supervisor);

        final GlobalPositions p = factory.createGlobalPosition(val, dfields);
        data.getGlobalPositionsService().persist(p);
        addPositionsToAllFacilities(val, dfields);
    }

    public void saveEditedPosition(Form form) {
        final String positionCode = fieldValues.getStringFields(form.getField("positionCode").getValue());

        final StringValues val = new StringValues();
        val.setPositionCode(positionCode);

        final Long supervisor = fieldValues.getLongFields(form.getField("supervisor").getValue());
        final Long department = fieldValues.getLongFields(form.getField("department").getValue());
        final Long job = fieldValues.getLongFields(form.getField("job").getValue());
        final Long positionType = fieldValues.getLongFields(form.getField("positionType").getValue());
        final Long positionStatus = fieldValues.getLongFields(form.getField("positionStatus").getValue());

        final Map<String, Long> dfields = new HashMap<String, Long>();
        dfields.put("department", department);
        dfields.put("job", job);
        dfields.put("positionStatus", positionStatus);
        dfields.put("positionType", positionType);
        dfields.put("supervisor", supervisor);

        final Long positionId = Long.parseLong(form.getField("positionId").getValue().toString());
        final GlobalPositions p = factory.updateGlobalPosition(val, dfields, positionId);
        data.getGlobalPositionsService().merge(p);
    }

    public void deletePosition(Form form) {
        final Long positionId = Long.parseLong(form.getField("positionId").getValue().toString());
        final GlobalPositions p = factory.loadGlobalPositions(positionId);
        data.getGlobalPositionsService().remove(p);
    }

    private void addPositionsToAllFacilities(StringValues val, Map<String, Long> dfields) {

        List<Facility> facilities = data.getFacilityService().findAll();
        for (Facility facility : facilities) {
            final String positionComments = "No Comments";
            val.setPositionComments(positionComments);
            Long facililty = facility.getId();
            dfields.put("facililty", facililty);
            final Positions p = factory.createPosition(val, dfields);
            data.getPositionsService().persist(p);

        }
    }
}
