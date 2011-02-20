/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentorsForm;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentorsBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentorsTable;
import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
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

/**
 *
 * @author boniface
 */
public class MentorsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentorsForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final MentorsTable table;
    private final TrainingCoursesFactory factory = data.getTrainingCoursesFactory();

    public MentorsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentorsForm();
        form = pform.createMentorsForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final MentorsBean bean = new MentorsBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentorsTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();


        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final MentorsBean Mentors = new MentorsBean();
            Mentors.setTitle(record.getItemProperty("Title").toString());
            Mentors.setFirstName(record.getItemProperty("First Name").toString());
            Mentors.setLastName(record.getItemProperty("Last Name").toString());
            Mentors.setQualification(record.getItemProperty("Qualification").toString());
            Mentors.setId(new Long(table.getValue().toString()));


            if (Mentors != form.getItemDataSource()) {

                final BeanItem item = new BeanItem(Mentors);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(pform.orderList());
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
            saveNewMentors(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "MENTORS"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "MENTORS"));
        } else if (source == pform.getUpdate()) {
            saveEditedScheduleCourses(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "MENTORS"));
        } else if (source == pform.getDelete()) {
            deleteScheduleCourses(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "MENTORS"));

        }

    }

    public void saveNewMentors(Form form) {

        final String firstName = form.getField("firstName").getValue().toString();
        final String lastName = form.getField("lastName").getValue().toString();
        final String title = form.getField("title").getValue().toString();
        final String qualification = form.getField("qualification").getValue().toString();
        final Mentors c = factory.createMentor(firstName, lastName, title, qualification);
        data.getMentorsService().persist(c);
    }

    public void saveEditedScheduleCourses(Form form) {

        final String firstName = form.getField("firstName").getValue().toString();
        final String lastName = form.getField("lastName").getValue().toString();
        final String title = form.getField("title").getValue().toString();
        final String qualification = form.getField("qualification").getValue().toString();
        final Long mentorID = Long.parseLong(form.getField("id").getValue().toString());
        final Mentors c = factory.updateMentor(firstName, lastName, title, qualification, mentorID);
        data.getMentorsService().merge(c);
    }

    public void deleteScheduleCourses(Form form) {
        final Long mentorId = Long.parseLong(form.getField("id").getValue().toString());
        final Mentors c = factory.loadMentor(mentorId);
        data.getMentorsService().remove(c);
    }
}
