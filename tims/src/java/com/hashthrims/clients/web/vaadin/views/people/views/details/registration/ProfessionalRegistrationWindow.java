/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.registration;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.registration.form.RegistrationBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.registration.form.RegistrationForm;
import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.infrastructure.factories.LanguagesFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author boniface
 */
public class ProfessionalRegistrationWindow extends Window implements ClickListener {

    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final RegistrationForm pform = new RegistrationForm();
    LanguagesFactory factory = data.getLanguagesFactory();

    public ProfessionalRegistrationWindow(Person p, RegistrationBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        final Form form = pform.createRegistrationFrom();
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
        setCaption("Add or Edit Registration Information");
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
            updateRegitrationInformation(formData);
            final Person p = data.getPersonService().find(person.getId());
            final PersonDetailsView view = new PersonDetailsView(p, main,"REG");
            main.mainView.setSecondComponent(view);
            close();

        } else if (source == pform.getSave()) {
            saveRegistration(formData);
            final PersonDetailsView view = new PersonDetailsView(person, main,"REG");
            main.mainView.setSecondComponent(view);
            close();

        }

    }


    private void updateRegitrationInformation(Form formData) {
        final Long id = Long.parseLong(formData.getField("id").getValue().toString());
       final Long registrationBodyId = Long.parseLong(formData.getField("registrationBody").getValue().toString());
        final String registrationNumber = fieldValues.getStringFields(formData.getField("registrationNumber").getValue());
        final String licenceNumber = fieldValues.getStringFields(formData.getField("licenceNumber").getValue());
        final Date registrationDate = fieldValues.getDateFields(formData.getField("registrationDate").getValue());
        final Date expirationDate = fieldValues.getDateFields(formData.getField("expirationDate").getValue());
        final RegistrationBody  registrationBody = data.getRegistrationBodyService().find(registrationBodyId);
        Map<String,String> vals = new HashMap<String,String>();
        vals.put("registrationNumber", registrationNumber);
        vals.put("licenceNumber", licenceNumber);
      
        final ProfessionalRegistration profesionRegistration  = factory.updateProfessionalRegistration(registrationBody, vals,registrationDate,expirationDate,id);
        data.getProfessionalRegistrationService().merge(profesionRegistration);
    }

    private void saveRegistration(Form formData) {
        final Long registrationBodyId = Long.parseLong(formData.getField("registrationBody").getValue().toString());
        final String registrationNumber = fieldValues.getStringFields(formData.getField("registrationNumber").getValue());
        final String licenceNumber = fieldValues.getStringFields(formData.getField("licenceNumber").getValue());
        final Date registrationDate = fieldValues.getDateFields(formData.getField("registrationDate").getValue());
        final Date expirationDate = fieldValues.getDateFields(formData.getField("expirationDate").getValue());
        final RegistrationBody  registrationBody = data.getRegistrationBodyService().find(registrationBodyId);
        Map<String,String> vals = new HashMap<String,String>();
        vals.put("registrationNumber", registrationNumber);
        vals.put("licenceNumber", licenceNumber);
      
        final ProfessionalRegistration profesionRegistration  = factory.createProfessionalRegistration(registrationBody, vals,registrationDate,expirationDate);
        person.getProfessionalRegistration().add(profesionRegistration);
        data.getPersonService().merge(person);
    }
}
