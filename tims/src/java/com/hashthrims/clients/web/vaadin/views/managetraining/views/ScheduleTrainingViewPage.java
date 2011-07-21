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
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.domain.traininglist.TrainingInstructors;
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
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class ScheduleTrainingViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {
    
    private final HashThrimsMain main;
    private final Form form;
    private final ScheduleTrainingForm pform;
    private final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final EmployeeFactory factory = data.getEmployeeFactory();
    
    public ScheduleTrainingViewPage(HashThrimsMain app) {
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
            scheduleTheCourse(form);
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
        
        Map<String, Long> st = new HashMap<String, Long>();
        st.put("course", course);
        st.put("requestor", requestor);
        
        
        Map<String, Date> dates = new HashMap<String, Date>();
        dates.put("dateRequested", dateRequested);
        dates.put("courseStartDate", courseStartDate);
        dates.put("courseEndDate", courseEndDate);
        
        
        
        
        scheduleCourse.setCourseCapacity(courseCapacity);
        
        TrainingCourses c = data.getTrainingCoursesService().find(course);
        scheduleCourse.setCourseId(course);
        scheduleCourse.setCourseName(c.getCourseName());
        
        scheduleCourse.setEndDate(courseEndDate);
        scheduleCourse.setStartDate(courseStartDate);
        
        TrainingCourseRequestors org = data.getTrainingCourseRequestorsType().find(requestor);
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
        if(institutionName.getTrainingInstitution()!=null)
            return institutionName.getTrainingInstitution();
        return null;
    }
}
