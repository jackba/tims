/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.ScheduledCourseBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.ScheduleCourseForm;
import com.hashthrims.domain.EmployeeCourses;
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
public class ScheduleCourseWindow extends Window implements ClickListener {

    private ScheduleCourseForm pform = new ScheduleCourseForm();
    private HashThrimsMain main;
    private Person person;
    private static ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private Form formData;
    private final EmployeeFactory factory = data.getEmployeeFactory();

    public ScheduleCourseWindow(Person p, ScheduledCourseBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        Form form = pform.createShortCourseFrom();
        BeanItem item = new BeanItem(bean);

        pform.getSave().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        form.setItemDataSource(item);
        formData = form;
        setModal(true);
        setClosable(true);
        setTheme("runo");
        setHeight("500px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Schedule Course");
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
            updateShortCourse(formData);
            main.mainView.setSecondComponent(new PersonDetailsView(person, main, "COURSE"));
            close();

        } else if (source == pform.getSave()) {
            saveShortCourse(formData);
            main.mainView.setSecondComponent(new PersonDetailsView(person, main, "COURSE"));
            close();

        }

    }

    private void updateShortCourse(Form formData) {
        Long id = Long.parseLong(formData.getField("id").getValue().toString());

        String retraining = fieldValues.getStringFields(formData.getField("retraining").getValue());
        Long course = Long.parseLong(formData.getField("course").getValue().toString());
        Long requestor = Long.parseLong(formData.getField("requestor").getValue().toString());

        Date dateRequested = fieldValues.getDateFields(formData.getField("dateRequested").getValue());
        Date courseStartDate = fieldValues.getDateFields(formData.getField("courseStartDate").getValue());
        Date courseEndDate = fieldValues.getDateFields(formData.getField("courseEndDate").getValue());

        Map<String, Long> st = new HashMap<String, Long>();
        st.put("course", course);
        st.put("requestor", requestor);
        st.put("id", id);

        Map<String, Date> dates = new HashMap<String, Date>();
        dates.put("dateRequested", dateRequested);
        dates.put("courseStartDate", courseStartDate);
        dates.put("courseEndDate", courseEndDate);


       EmployeeCourses ec = factory.updateEmployeeCourse(st, dates, retraining);
       person.getCourses().add(ec);
       data.getPersonService().merge(person);
    }

    private void saveShortCourse(Form formData) {

        String retraining = fieldValues.getStringFields(formData.getField("retraining").getValue());
        Long course = Long.parseLong(formData.getField("course").getValue().toString());
        Long requestor = Long.parseLong(formData.getField("requestor").getValue().toString());

        Date dateRequested = fieldValues.getDateFields(formData.getField("dateRequested").getValue());
        Date courseStartDate = fieldValues.getDateFields(formData.getField("courseStartDate").getValue());
        Date courseEndDate = fieldValues.getDateFields(formData.getField("courseEndDate").getValue());

        Map<String, Long> st = new HashMap<String, Long>();
        st.put("course", course);
        st.put("requestor", requestor);


        Map<String, Date> dates = new HashMap<String, Date>();
        dates.put("dateRequested", dateRequested);
        dates.put("courseStartDate", courseStartDate);
        dates.put("courseEndDate", courseEndDate);

        EmployeeCourses ec = factory.createEmployeeCourse(st, dates, retraining);
        person.getCourses().add(ec);
        data.getPersonService().persist(person);

    }
}
