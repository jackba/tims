/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.position;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.position.form.EmployeePositionBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.position.form.EmployeePositionForm;

import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import java.util.Date;

/**
 *
 * @author boniface
 */
public class EmployeePositionWindow extends Window implements ClickListener {

    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final EmployeePositionForm pform = new EmployeePositionForm();
    private final PositionsFactory factory = data.getPositionFactory();

    public EmployeePositionWindow(Person p, EmployeePositionBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        final Form form = pform.createEmployeePositionFrom();
        final BeanItem item = new BeanItem(bean);

        pform.getSave().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        form.setItemDataSource(item);
        formData = form;
        setModal(true);
        setClosable(true);
        setHeight("450px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Add or Edit Employee Position");
        if (bean.getId() != null) {
            pform.getUpdate().setVisible(true);
        } else {
            pform.getSave().setVisible(true);
        }
        addComponent(form);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == pform.getCancel()) {
            close();
        } else if (source == pform.getUpdate()) {
            updateEmployeePosition(formData);
            final Person p = data.getPersonService().find(person.getId());
            final PersonDetailsView view = new PersonDetailsView(p, main, "POS");
            main.mainView.setSecondComponent(view);
            close();

        } else if (source == pform.getSave()) {
            saveEmployeePosition(formData);
            final PersonDetailsView view = new PersonDetailsView(person, main, "POS");
            main.mainView.setSecondComponent(view);
            close();

        }

    }

    private void updateEmployeePosition(Form formData) {
        final Long id = Long.parseLong(formData.getField("id").getValue().toString());
        final Long positionId = Long.parseLong(formData.getField("position").getValue().toString());
        final String status = fieldValues.getStringFields(formData.getField("status").getValue());
        final Date startDate = fieldValues.getDateFields(formData.getField("startDate").getValue());
        final Date endDate = fieldValues.getDateFields(formData.getField("enddate").getValue());
        final Positions position = data.getPositionsService().find(positionId);

        EmployeePosition employeePositions = factory.updateEmployeePosition(position, status, startDate, endDate, id);
        person.getPosition().add(employeePositions);
        data.getPersonService().merge(person);
    }

    private void saveEmployeePosition(Form formData) {
        final Long positionId = Long.parseLong(formData.getField("position").getValue().toString());
        final String status = fieldValues.getStringFields(formData.getField("status").getValue());
        final Date startDate = fieldValues.getDateFields(formData.getField("startDate").getValue());
        final Date endDate = fieldValues.getDateFields(formData.getField("enddate").getValue());
        final Positions position = data.getPositionsService().find(positionId);
        final Status st= data.getStatusService().getByPropertyName("status", "FILLED");
        position.setPositionStatus(st);
        data.getPositionsService().merge(position);
        EmployeePosition employeePositions = factory.createEmployeePosition(position, status, startDate, endDate);
        person.getPosition().add(employeePositions);
        data.getPersonService().merge(person);
    }
}
