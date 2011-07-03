/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.ManageTrainingMenuView;
import com.hashthrims.clients.web.vaadin.views.managetraining.forms.ScheduleTrainingForm;
import com.hashthrims.clients.web.vaadin.views.managetraining.model.ScheduleTrainingBean;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.infrastructure.factories.EmployeeFactory;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class EvaluateTrainingViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ScheduleTrainingForm pform;
    private final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final EmployeeFactory factory = data.getEmployeeFactory();

    public EvaluateTrainingViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new ScheduleTrainingForm();
        form = pform.createShortCourseFrom();

        // Add Listeners
        pform.getSubmitListofAttendant().addListener((ClickListener) this);

        ScheduleTrainingBean bean = new ScheduleTrainingBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSubmitListofAttendant()) {
            submitAttendanceList(form);
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "SCH"));
        }

    }

    public void submitAttendanceList(Form form) {
        String retraining = fieldValues.getStringFields(form.getField("retraining").getValue());
        Long course = Long.parseLong(form.getField("course").getValue().toString());
        Long requestor = Long.parseLong(form.getField("requestor").getValue().toString());

        Date dateRequested = fieldValues.getDateFields(form.getField("dateRequested").getValue());
        Date courseStartDate = fieldValues.getDateFields(form.getField("courseStartDate").getValue());
        Date courseEndDate = fieldValues.getDateFields(form.getField("courseEndDate").getValue());

        Map<String, Long> st = new HashMap<String, Long>();
        st.put("course", course);
        st.put("requestor", requestor);


        Map<String, Date> dates = new HashMap<String, Date>();
        dates.put("dateRequested", dateRequested);
        dates.put("courseStartDate", courseStartDate);
        dates.put("courseEndDate", courseEndDate);
        EmployeeCourses ec = factory.createEmployeeCourse(st, dates, retraining);

    }
}
