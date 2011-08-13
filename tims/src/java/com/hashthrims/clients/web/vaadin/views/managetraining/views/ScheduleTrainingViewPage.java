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
import com.hashthrims.clients.web.vaadin.views.managetraining.tables.ScheduleTrainingTable;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.domain.traininglist.TrainingInstructors;
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
public class ScheduleTrainingViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ScheduleTrainingForm pform;
    private final ScheduleTrainingTable table;
    private final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();

    public ScheduleTrainingViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new ScheduleTrainingForm();
        form = pform.createShortCourseFrom();

        // Add Listeners
        pform.getSubmitListofAttendant().addListener((ClickListener) this);
        pform.getUpdateCources().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);

        ScheduleTrainingBean bean = new ScheduleTrainingBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);

        table = new ScheduleTrainingTable(main, pform, form);
        table.setColumnCollapsingAllowed(true);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Long id = new Long(table.getValue().toString());
            ScheduledCourses course = data.getScheduledCoursesType().find(id);
            final ScheduleTrainingBean bean = new ScheduleTrainingBean();
            bean.setCourse(course.getCourseId());
            bean.setId(course.getId());
            bean.setCourseCapacity(course.getCourseCapacity());
            bean.setCourseEndDate(course.getEndDate());
            bean.setCourseStartDate(course.getStartDate());
            bean.setDateRequested(course.getDateRequested());
            bean.setRequestor(course.getCourseRequestor());
            List<TrainingInstructors> inst = course.getClassInstructors();
            for (TrainingInstructors trainingInstructors : inst) {
                bean.getTrainers().add(trainingInstructors.getInstructorId());
            }

            bean.setVenue(course.getVenueId());

            if (bean != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(bean);
                form.setItemDataSource(item);
                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSubmitListofAttendant().setVisible(false);
                pform.getUpdateCources().setVisible(false);
                pform.getCancel().setVisible(true);
            }
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSubmitListofAttendant()) {
            scheduleTheCourse(form);
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "SCH"));
        }

        if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "SCH"));
        }

        if (source == pform.getUpdateCources()) {
            editScheduledCourse(form);
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "SCH"));
        }

    }

    public void scheduleTheCourse(Form form) {
        TrainingInstructors trainer = new TrainingInstructors();
        ScheduledCourses scheduleCourse = new ScheduledCourses();

        Long course = Long.parseLong(form.getField("course").getValue().toString());
        Long venueId = Long.parseLong(form.getField("venue").getValue().toString());
        Long requestor = Long.parseLong(form.getField("requestor").getValue().toString());
        int courseCapacity = Integer.parseInt(form.getField("courseCapacity").getValue().toString());

        Date dateRequested = fieldValues.getDateFields(form.getField("dateRequested").getValue());
        Date courseStartDate = fieldValues.getDateFields(form.getField("courseStartDate").getValue());
        Date courseEndDate = fieldValues.getDateFields(form.getField("courseEndDate").getValue());


        scheduleCourse.setCourseCapacity(courseCapacity);
        TrainingCourses c = data.getTrainingCoursesService().find(course);
        scheduleCourse.setCourseId(course);
        scheduleCourse.setCourseName(c.getCourseName());

        scheduleCourse.setEndDate(courseEndDate);
        scheduleCourse.setStartDate(courseStartDate);
        scheduleCourse.setDateRequested(dateRequested);

        scheduleCourse.setOrganisationId(c.getId());
        scheduleCourse.setCourseRequestor(requestor);
        scheduleCourse.setOrganisationName(getTrainingInstitution(c.getInstitutionName()));

        Facility fac = data.getFacilityService().find(venueId);
        scheduleCourse.setVenue(fac.getFacilityName());
        scheduleCourse.setVenueId(venueId);
        data.getScheduledCoursesType().persist(scheduleCourse);
        Long scheduleId = scheduleCourse.getId();
        Object trainers = form.getField("trainers").getValue();
        List<Long> personsIds = fieldValues.getSelectListLongFields(trainers);
        ScheduledCourses cs = data.getScheduledCoursesType().find(scheduleId);
        for (Long trainerIds : personsIds) {
            Person p = data.getPersonService().find(trainerIds);
            trainer.setFirstName(p.getPersonName());
            trainer.setLastName(p.getPersonSurname());
            trainer.setInstructorName(p.getPersonName() + " " + p.getPersonSurname());
            trainer.setInstructorId(trainerIds);
            cs.getClassInstructors().add(trainer);
            data.getScheduledCoursesType().merge(cs);
        }

    }

    private String getTrainingInstitution(TrainingInstitution institutionName) {
        if (institutionName.getTrainingInstitution() != null) {
            return institutionName.getTrainingInstitution();
        }
        return null;
    }

    private void editScheduledCourse(Form form) {
        TrainingInstructors trainer = new TrainingInstructors();
        Long id = Long.parseLong(form.getField("id").getValue().toString());
        ScheduledCourses scheduleCourse = data.getScheduledCoursesType().find(id);

        Long course = Long.parseLong(form.getField("course").getValue().toString());
        Long venueId = Long.parseLong(form.getField("venue").getValue().toString());
        Long requestor = Long.parseLong(form.getField("requestor").getValue().toString());
        int courseCapacity = Integer.parseInt(form.getField("courseCapacity").getValue().toString());

        Date dateRequested = fieldValues.getDateFields(form.getField("dateRequested").getValue());
        Date courseStartDate = fieldValues.getDateFields(form.getField("courseStartDate").getValue());
        Date courseEndDate = fieldValues.getDateFields(form.getField("courseEndDate").getValue());


        scheduleCourse.setCourseCapacity(courseCapacity);
        TrainingCourses c = data.getTrainingCoursesService().find(course);
        scheduleCourse.setCourseId(course);
        scheduleCourse.setCourseName(c.getCourseName());

        scheduleCourse.setEndDate(courseEndDate);
        scheduleCourse.setStartDate(courseStartDate);
        scheduleCourse.setDateRequested(dateRequested);

        scheduleCourse.setOrganisationId(c.getId());
        scheduleCourse.setCourseRequestor(requestor);
        scheduleCourse.setOrganisationName(getTrainingInstitution(c.getInstitutionName()));

        Facility fac = data.getFacilityService().find(venueId);
        scheduleCourse.setVenue(fac.getFacilityName());
        scheduleCourse.setVenueId(venueId);
        scheduleCourse.getClassInstructors().clear();
        data.getScheduledCoursesType().merge(scheduleCourse);

        Object trainers = form.getField("trainers").getValue();
        List<Long> personsIds = fieldValues.getSelectListLongFields(trainers);
        ScheduledCourses cs = data.getScheduledCoursesType().find(id);
        for (Long trainerIds : personsIds) {
            Person p = data.getPersonService().find(trainerIds);
            trainer.setFirstName(p.getPersonName());
            trainer.setLastName(p.getPersonSurname());
            trainer.setInstructorName(p.getPersonName() + " " + p.getPersonSurname());
            trainer.setInstructorId(trainerIds);
            cs.getClassInstructors().add(trainer);
            data.getScheduledCoursesType().merge(cs);
        }
    }
}
