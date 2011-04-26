/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.EvaluateCourseForm;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.EvaluateddCourseBean;
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

/**
 *
 * @author boniface
 */
public class EvaluateCourseWindow extends Window implements ClickListener {

    private EvaluateCourseForm pform = new EvaluateCourseForm();
    private HashThrimsMain main;
    private Person person;
    private static ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private Form formData;
    private final EmployeeFactory factory = data.getEmployeeFactory();

    public EvaluateCourseWindow(Person p, EvaluateddCourseBean bean, HashThrimsMain app) {
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
        setHeight("500px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Evaluate Course");
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
            main.mainView.setSecondComponent(new PersonDetailsView(person, main,"COURSE"));
            close();
        } 
    }
    

    private void updateShortCourse(Form formData) {
       
        Long id = Long.parseLong(formData.getField("id").getValue().toString());
        Long evaluation = Long.parseLong(formData.getField("evaluation").getValue().toString());
        Date date = fieldValues.getDateFields(formData.getField("evaluationDate").getValue());
        EmployeeCourses ec = factory.updateEvalaution(id, evaluation,date);
        person.getCourses().add(ec);
        data.getPersonService().merge(person);
    }

  
}
