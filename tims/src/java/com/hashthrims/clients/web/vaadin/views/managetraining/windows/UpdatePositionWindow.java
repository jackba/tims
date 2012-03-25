/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PeopleUtil;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.position.form.EmployeePositionBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.position.form.EmployeePositionForm;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class UpdatePositionWindow extends Window implements Button.ClickListener, Property.ValueChangeListener {
    
    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final EmployeePositionForm pform = new EmployeePositionForm();
    private final PositionsFactory factory = data.getPositionFactory();
    private final PeopleUtil util = new PeopleUtil();
    
    public UpdatePositionWindow(Person p, HashThrimsMain app) {
        setModal(true);
        setClosable(true);
        setHeight("600px");
        setWidth("600px");
        setPositionX(100);
        setPositionY(100);
        setCaption("Change Position For  " + p.getPersonName() + " " + p.getPersonSurname());
        main = app;
        person = p;
        
        EmployeePositionBean bean = new EmployeePositionBean();
        
        final Form form = pform.createEmployeePositionFrom();
        final BeanItem item = new BeanItem(bean);
        pform.getUpdate().setVisible(true);
        
        pform.getSave().addListener((Button.ClickListener) this);
        pform.getUpdate().addListener((Button.ClickListener) this);
        pform.getCancel().addListener((Button.ClickListener) this);
        form.setItemDataSource(item);
        formData = form;
        
        addComponent(form);
        
    }
    
    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        
        if (source == pform.getCancel()) {
            close();
        } else if (source == pform.getUpdate()) {
            updateEmployeePosition(formData);
            close();
            
        } else if (source == pform.getSave()) {
            
            close();
            
        }
    }
    
    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private EmployeePosition getPosition(List<EmployeePosition> positions) {
        return util.getEmployeePosition(positions);
    }
    
    private Long getFacilitityId(Positions position) {
        if (position != null) {
            return getId(position.getFacililty());
        }
        return null;
    }
    
    private Long getId(Facility facililty) {
        if (facililty != null) {
            return facililty.getId();
        }
        return null;
    }
    
    private void updateEmployeePosition(Form formData) {
        final Long positionId = Long.parseLong(formData.getField("position").getValue().toString());
        final String status = fieldValues.getStringFields(formData.getField("status").getValue());
        final Date startDate = fieldValues.getDateFields(formData.getField("startDate").getValue());
        final Date endDate = fieldValues.getDateFields(formData.getField("enddate").getValue());
        //UPDATE POSITION TO FILLED 
        final Positions position = data.getPositionsService().find(positionId);
        final Status filled = data.getStatusService().getByPropertyName("status", "FILLED");
        position.setPositionStatus(filled);
        data.getPositionsService().merge(position);
        EmployeePosition employeePositions = factory.createEmployeePosition(position, status, startDate, endDate);

        //Get Old Position
        EmployeePosition oldPosition = data.getEmployeePositionService().getByPropertyName("status", "CURRENT");
        // Free Old Position
        if (oldPosition != null) {
            Positions freePosition = oldPosition.getPosition();            
            final Status vacant = data.getStatusService().getByPropertyName("status", "VACANT");
            freePosition.setPositionStatus(vacant);
            data.getPositionsService().merge(freePosition);
            oldPosition.setEnddate(new Date());
            oldPosition.setStatus("OLD");
            data.getEmployeePositionService().merge(oldPosition);
        }
        
        Person updatedPerson = data.getPersonService().find(person.getId());
        updatedPerson.getPosition().add(employeePositions);
        data.getPersonService().merge(updatedPerson);
    }
}
