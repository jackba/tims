/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;

import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.forms.PositionForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.PositionBean;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.StringValues;
import com.hashthrims.clients.web.vaadin.views.positions.table.PositionTable;
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
import java.util.Map;
import org.joda.time.DateTime;

/**
 *
 * @author boniface
 */
public class PositionsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final PositionForm pform = new PositionForm();
    private final Form form = pform.createPositionFrom();
    private static final ClientDataService data = new ClientDataService();
    private final PositionTable table;
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final PositionsFactory factory = data.getPositionFactory();
    private final PositionTableHeaders tb = new PositionTableHeaders();

    public PositionsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final PositionBean bean = new PositionBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);


        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new PositionTable(main);
        table.addListener((ValueChangeListener) this);
        // addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final PositionBean positionBean = new PositionBean();

            positionBean.setPositionCode(record.getItemProperty("Position Code").toString());

            positionBean.setPositionComments(record.getItemProperty("Position Comments").toString());


            positionBean.setFacililty(tb.getFacilityId(record.getItemProperty("Facility").toString()));
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
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "POSITION"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "POSITION"));
        } else if (source == pform.getUpdate()) {
            saveEditedPosition(form);

            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "POSITION"));

        } else if (source == pform.getDelete()) {
            deletePosition(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "POSITION"));

        }

    }

    public void saveNewPosition(Form form) {
        System.out.println(" The Save Starts Now " + new DateTime().getSecondOfMinute());

        final String positionCode = fieldValues.getStringFields(form.getField("positionCode").getValue());
        final String positionComments = fieldValues.getStringFields(form.getField("positionComments").getValue());

        final StringValues val = new StringValues();
        val.setPositionCode(positionCode);
        val.setPositionComments(positionComments);

        final Long facililty = fieldValues.getLongFields(form.getField("facililty").getValue());
        final Long supervisor = fieldValues.getLongFields(form.getField("supervisor").getValue());
        final Long department = fieldValues.getLongFields(form.getField("department").getValue());
        final Long job = fieldValues.getLongFields(form.getField("job").getValue());
        final Long positionType = fieldValues.getLongFields(form.getField("positionType").getValue());
        final Long positionStatus = fieldValues.getLongFields(form.getField("positionStatus").getValue());

        final Map<String, Long> dfields = new HashMap<String, Long>();
        dfields.put("department", department);
        dfields.put("facililty", facililty);
        dfields.put("job", job);
        dfields.put("positionStatus", positionStatus);
        dfields.put("positionType", positionType);
        dfields.put("supervisor", supervisor);
        System.out.println(" All Fields Collected and Creation of the Position Object Starts Now  " + new DateTime().getSecondOfMinute());
        final Positions p = factory.createPosition(val, dfields);
        System.out.println(" Position Object Created and the Saving Starts Now   " + new DateTime().getSecondOfMinute());
        data.getPositionsService().persist(p);

        System.out.println(" Data Finally Save in the Database " + new DateTime().getSecondOfMinute());
    }

    public void saveEditedPosition(Form form) {
        final String positionCode = fieldValues.getStringFields(form.getField("positionCode").getValue());
        final String positionComments = fieldValues.getStringFields(form.getField("positionComments").getValue());
        final StringValues val = new StringValues();
        val.setPositionCode(positionCode);
        val.setPositionComments(positionComments);

        final Long facililty = fieldValues.getLongFields(form.getField("facililty").getValue());
        final Long supervisor = fieldValues.getLongFields(form.getField("supervisor").getValue());
        final Long department = fieldValues.getLongFields(form.getField("department").getValue());
        final Long job = fieldValues.getLongFields(form.getField("job").getValue());
        final Long positionType = fieldValues.getLongFields(form.getField("positionType").getValue());
        final Long positionStatus = fieldValues.getLongFields(form.getField("positionStatus").getValue());

        final Map<String, Long> dfields = new HashMap<String, Long>();
        dfields.put("department", department);
        dfields.put("facililty", facililty);
        dfields.put("job", job);
        dfields.put("positionStatus", positionStatus);
        dfields.put("positionType", positionType);
        dfields.put("supervisor", supervisor);

        final Long positionId = Long.parseLong(form.getField("positionId").getValue().toString());
        final Positions p = factory.updatePosition(val, dfields, positionId);
        data.getPositionsService().merge(p);
    }

    public void deletePosition(Form form) {
        final Long positionId = Long.parseLong(form.getField("positionId").getValue().toString());
        final Positions p = factory.loadPositions(positionId);
        data.getPositionsService().remove(p);
    }
}
