/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form.MentoringCourseBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form.MentoringCourseForm;
import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.factories.EmployeeFactory;
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
public class MentoringCourseWindow extends Window implements ClickListener {

    private final MentoringCourseForm pform = new MentoringCourseForm();
    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final EmployeeFactory factory = data.getEmployeeFactory();
    private final String selectedTab;
    private final Form form;

    public MentoringCourseWindow(Person p, MentoringCourseBean bean, HashThrimsMain app,String tab) {
        main = app;
        person = p;
        selectedTab=tab;



        form = pform.createMentoringFrom();
        final BeanItem item = new BeanItem(bean);

        pform.getSave().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
      
        form.setItemDataSource(item);
        formData = form;
        setModal(true);
        setClosable(true);
        
        setHeight("400px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(50);
        setCaption("Add or Edit Mentoring Session");
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
            updateMentoringCourse(formData);
            main.mainView.setSecondComponent(new PersonDetailsView(person, main,selectedTab));
            close();

        } else if (source == pform.getSave()) {
            saveMentoringCourse(formData);
            main.mainView.setSecondComponent(new PersonDetailsView(person, main,selectedTab));
            close();

        }

    }

    private void updateMentoringCourse(Form formData) {

        final Long id = Long.parseLong(formData.getField("id").getValue().toString());
        final Long patientsInitiated = Long.parseLong(formData.getField("patientsInitiated").getValue().toString());
        final Long hoursSpent = Long.parseLong(formData.getField("hoursSpent").getValue().toString());
        final Date mentoringDate = fieldValues.getDateFields(formData.getField("mentoringDate").getValue());

        final Long venue = Long.parseLong(formData.getField("venue").getValue().toString());
        final Long mentoringSession = Long.parseLong(formData.getField("mentoringSession").getValue().toString());

        final Map<String, Long> val = new HashMap<String, Long>();
        val.put("patientsInitiated", patientsInitiated);
        val.put("hoursSpent", hoursSpent);
        val.put("venue", venue);
        val.put("mentoringSession", mentoringSession);
        final EmployeeMentoring ec = factory.updateMentoringSession(val, mentoringDate, id);

        person.getMentoring().add(ec);
        data.getPersonService().merge(person);
    }

    private void saveMentoringCourse(Form formData) {

        final Long patientsInitiated = Long.parseLong(formData.getField("patientsInitiated").getValue().toString());
        final Long hoursSpent = Long.parseLong(formData.getField("hoursSpent").getValue().toString());
        final Date mentoringDate = fieldValues.getDateFields(formData.getField("mentoringDate").getValue());

        final Long venue = Long.parseLong(formData.getField("venue").getValue().toString());
        final Long mentoringSession = Long.parseLong(formData.getField("mentoringSession").getValue().toString());

        final Map<String, Long> val = new HashMap<String, Long>();
        val.put("patientsInitiated", patientsInitiated);
        val.put("hoursSpent", hoursSpent);
        val.put("venue", venue);
        val.put("mentoringSession", mentoringSession);

        final EmployeeMentoring ec = factory.saveMentoringSession(val, mentoringDate);
        person.getMentoring().add(ec);
        data.getPersonService().merge(person);

    }
}
