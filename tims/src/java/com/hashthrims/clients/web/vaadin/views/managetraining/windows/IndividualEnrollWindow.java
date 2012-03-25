/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.windows.traineeprofle.TraineePersonalInformation;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class IndividualEnrollWindow extends Window implements Button.ClickListener, Property.ValueChangeListener {

    private final HorizontalLayout enrollLayout = new HorizontalLayout();
    private final VerticalLayout layout = new VerticalLayout();
    private final VerticalLayout canvas = new VerticalLayout();
    private final Button enrollParticipant = new Button("Enroll Participant");
    private final Button cancelAdditionOfNewPerson = new Button("Cancel Addition of New Person");
    private final Button addNewPerson = new Button("Add New Person");
    private final ScheduledCourses course;
    private final HashThrimsMain main;
    private static ClientDataService data = new ClientDataService();
    private final ComboBox participatnts = new ComboBox();
    private final AddNewPerson newPerson;

    public IndividualEnrollWindow(ScheduledCourses course, HashThrimsMain main) {
        this.course = course;
        this.main = main;
        canvas.setSizeFull();
        newPerson = new AddNewPerson(main, course, this);
        cancelAdditionOfNewPerson.setSizeFull();
        // Add Listeners 
        participatnts.addListener((Property.ValueChangeListener) this);
        enrollParticipant.addListener((Button.ClickListener) this);
        addNewPerson.addListener((Button.ClickListener) this);
        cancelAdditionOfNewPerson.addListener((Button.ClickListener) this);
        List<Person> people = data.getPersonService().findAll();
        Collections.sort(people);
        for (Person person : people) {
            participatnts.addItem(person.getId());
            participatnts.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }

        participatnts.setImmediate(true);
        participatnts.setWidth("500px");
        setModal(true);
        setClosable(true);
        setHeight("800px");
        setWidth("1024px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Enroll Individual Into the Course " + course.getCourseName());
        enrollLayout.setSizeFull();
        enrollLayout.addComponent(participatnts);
        enrollLayout.addComponent(enrollParticipant);
        enrollLayout.addComponent(addNewPerson);
        layout.addComponent(new Label("<hr>", Label.CONTENT_XHTML));
        layout.addComponent(enrollLayout);
        layout.addComponent(new Label("<hr>", Label.CONTENT_XHTML));
        layout.addComponent(canvas);
        addComponent(layout);


    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == enrollParticipant) {
            enrollParticipant(course.getId(), new Long(participatnts.getValue().toString()));
            closeWindow();
        }
        if (source == addNewPerson) {
            canvas.removeAllComponents();
            canvas.addComponent(newPerson);
            canvas.addComponent(cancelAdditionOfNewPerson);


        }
        if (source == cancelAdditionOfNewPerson) {
            canvas.removeAllComponents();

        }
    }

    public void closeWindow() {
        close();
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
            main.getMainWindow().showNotification("Sorry!! This Course is Now FULL", "Either increase the Course capacity or Deregister Some People", Notification.TYPE_ERROR_MESSAGE);
        }



    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == participatnts) {
            canvas.removeAllComponents();
            if (participatnts.getValue()!=null) {
                Long id = new Long(participatnts.getValue().toString());
                canvas.addComponent(new TraineePersonalInformation(data.getPersonService().find(id),main));
            }


        }
    }
}
