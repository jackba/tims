/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;

import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.forms.FacilityPositionsForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.FacilityPositionsBean;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.StringValues;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
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
public class FacilityPositionsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final FacilityPositionsForm pform = new FacilityPositionsForm();
    private final Form form = pform.createPositionFrom();
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final PositionsFactory factory = data.getPositionFactory();
    private final PositionTableHeaders tb = new PositionTableHeaders();

    public FacilityPositionsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final FacilityPositionsBean bean = new FacilityPositionsBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);


        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);



    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewFacilityPositions(form);
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "POSITIONS"));
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "POSITIONS"));
        }

    }

    public void saveNewFacilityPositions(Form form) {

        Object facilities = form.getField("facilities").getValue();
        List<Long> facilityIds = fieldValues.getSelectListLongFields(facilities);

        for (Long facilityId : facilityIds) {

            final String positionCode = fieldValues.getStringFields(form.getField("positionCode").getValue());
            final String positionComments = "No Comments";

            final StringValues val = new StringValues();
            val.setPositionCode(positionCode);
            val.setPositionComments(positionComments);

            Long facililty = facilityId;

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

            final Positions p = factory.createPosition(val, dfields);
            data.getPositionsService().persist(p);

        }

    }
}
