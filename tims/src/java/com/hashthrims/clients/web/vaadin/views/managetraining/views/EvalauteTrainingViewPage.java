/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.ManageTrainingMenuView;
import com.hashthrims.clients.web.vaadin.views.managetraining.forms.EvaluateTrainingForm;
import com.hashthrims.clients.web.vaadin.views.managetraining.model.EvaluateTrainingBean;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
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
import java.util.List;

/**
 *
 * @author boniface
 */
public class EvalauteTrainingViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final EvaluateTrainingForm pform;
    private final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final EmployeeFactory factory = data.getEmployeeFactory();

    public EvalauteTrainingViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new EvaluateTrainingForm();
        form = pform.createShortCourseFrom();
        pform.getSubmitEvaluation().addListener((ClickListener) this);

        EvaluateTrainingBean bean = new EvaluateTrainingBean();
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
        if (source == pform.getSubmitEvaluation()) {
            updateEmployeeCourse(form);
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "EVA"));
        }

    }

    public void updateEmployeeCourse(Form form) {
        Long id = Long.parseLong(form.getField("id").getValue().toString());
        Long evaluation = Long.parseLong(form.getField("evaluation").getValue().toString());
        Date date = fieldValues.getDateFields(form.getField("evaluationDate").getValue());


        Object attendees = form.getField("trainees").getValue();
        List<Long> personsIds = fieldValues.getSelectListLongFields(attendees);

        for (Long personId : personsIds) {
            Person person = data.getPersonService().find(personId);
            List<EmployeeCourses> ecs = person.getCourses();
            for (EmployeeCourses employeeCourses : ecs) {
                if (employeeCourses.getEvaluation() == null) {
                    EmployeeCourses ec = factory.updateEvalaution(employeeCourses.getId(), evaluation, date);
                    data.getEmployeeCourseService().merge(ec);
                }
            }

        }





    }
}
