/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.forms.NewPersonForm;
import com.hashthrims.clients.web.vaadin.views.people.models.NewPersonBean;
import com.hashthrims.clients.web.vaadin.views.people.tables.NewPersonTable;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.infrastructure.factories.PersonFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.hashthrims.infrastructure.util.DateUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import java.util.*;

/**
 *
 * @author boniface
 */
public class AddNewPerson extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private HashThrimsMain main;
    private NewPersonForm pform = new NewPersonForm();
    private Form form = pform.createNewPersonFrom();
    private static ClientDataService data = new ClientDataService();
    private NewPersonTable table;
    private DateUtil date = new DateUtil();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final IndividualEnrollWindow window;
    private final ScheduledCourses course;

    public AddNewPerson(HashThrimsMain app, ScheduledCourses schduledCourse, IndividualEnrollWindow modal) {

        window = modal;
        course = schduledCourse;
        main = app;
        setSizeFull();
        // Add Listeners
        pform.getSave().addListener((Button.ClickListener) this);
        pform.getEdit().addListener((Button.ClickListener) this);
        pform.getCancel().addListener((Button.ClickListener) this);
        pform.getDelete().addListener((Button.ClickListener) this);
        pform.getUpdate().addListener((Button.ClickListener) this);

        NewPersonBean bean = new NewPersonBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);


        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);


    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        Property property = event.getProperty();


    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewPerson(form);
            main.getMainWindow().showNotification("Person Added to the System", "Person Enrolled In Course", Window.Notification.TYPE_HUMANIZED_MESSAGE);
            window.closeWindow();
        } else if (source == pform.getCancel()) {
            window.closeWindow();
        }
    }

    private void saveNewPerson(Form form) {
        Map<String, String> names = new HashMap<String, String>();
        Map<String, Collection<Long>> lists = new HashMap<String, Collection<Long>>();
        Map<String, Long> demo = new HashMap<String, Long>();
        Map<String, Date> dates = new HashMap<String, Date>();


        PersonFactory factory = data.getPersonFactory();
        String firstname = fieldValues.getStringFields(form.getField("firstname").getValue());
        String surname = fieldValues.getStringFields(form.getField("surname").getValue());
        String othername = fieldValues.getStringFields(form.getField("othername").getValue());

        String telephoneNumber = fieldValues.getStringFields(form.getField("telephoneNumber").getValue());
        String cellnumber = fieldValues.getStringFields(form.getField("cellnumber").getValue());
        String faxnumber = fieldValues.getStringFields(form.getField("faxnumber").getValue());
        String email = fieldValues.getStringFields(form.getField("email").getValue());
        String idType = fieldValues.getStringFields(form.getField("idType").getValue());
        String idValue = fieldValues.getStringFields(form.getField("idValue").getValue());

        names.put("firstname", firstname);
        names.put("surname", surname);
        names.put("othername", othername);
        names.put("telephoneNumber", telephoneNumber);
        names.put("cellnumber", cellnumber);
        names.put("faxnumber", faxnumber);
        names.put("email", email);
        names.put("idType", idType);
        names.put("idValue", idValue);


        Long genderId = fieldValues.getLongFields(form.getField("genderId").getValue());
        Long raceId = fieldValues.getLongFields(form.getField("raceid").getValue());
        Long positionId = fieldValues.getLongFields(form.getField("positionId").getValue());
        Long facilityId = fieldValues.getLongFields(form.getField("facilityId").getValue());
        demo.put("raceId", raceId);
        demo.put("genderId", genderId);
        demo.put("positionId", positionId);
        demo.put("facilityId", facilityId);

        Date dob = fieldValues.getDateFields(form.getField("dob").getValue());
        Date startDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        dates.put("dob", dob);
        dates.put("startDate", startDate);


        Collection<Long> rolesid = fieldValues.getSelectListLongFields(form.getField("rolesId").getValue());
        Collection<Long> competencyFieldId = fieldValues.getSelectListLongFields(form.getField("competencyFieldId").getValue());
        Collection<Long> expertiseId = fieldValues.getSelectListLongFields(form.getField("expertiseId").getValue());

        lists.put("rolesid", rolesid);
        lists.put("competencyFieldId", competencyFieldId);
        lists.put("expertiseId", expertiseId);


        Person p = null;
        if (firstname != null || surname != null) {
            p = factory.createNewPerson(names, lists, demo, dates);
            data.getPersonService().persist(p);
            enrollParticipant(course.getId(), p.getId());
        } else {
            throw new UnsupportedOperationException("First Name and Last Name needed");
        }


    }

    private void enrollParticipant(Long courseId, Long participantId) {
        ScheduledCourses scheduleCourse = data.getScheduledCoursesType().find(courseId);
        TrainingCourses tc = data.getTrainingCoursesService().find(scheduleCourse.getCourseId());
        TrainingCourseRequestors req = data.getTrainingCourseRequestorsType().find(scheduleCourse.getCourseRequestor());
        EmployeeCourses empc = new EmployeeCourses();
        empc.setCourse(tc);
        empc.setScheduledCourseSessionId(courseId);
        empc.setCourseEndDate(scheduleCourse.getEndDate());
        empc.setCourseStartDate(scheduleCourse.getStartDate());
        empc.setRequestor(req);
        if (scheduleCourse.getCourseCapacity() != scheduleCourse.getNumOfStuds()) {
            Person p = data.getPersonService().find(participantId);
            //ADD COURSE TO PERSON
            p.getCourses().add(empc);
            data.getPersonService().merge(p);
            int cp = scheduleCourse.getNumOfStuds();
            scheduleCourse.setNumOfStuds(++cp);
            //UPDATE NUMBER OF PEOPLE COUNTER 
            data.getScheduledCoursesType().merge(scheduleCourse);
        } else {
            main.getMainWindow().showNotification("Sorry!! This Course is Now FULL", "Either increase the Course capacity or Deregister Some People", Window.Notification.TYPE_ERROR_MESSAGE);
        }



    }
}
