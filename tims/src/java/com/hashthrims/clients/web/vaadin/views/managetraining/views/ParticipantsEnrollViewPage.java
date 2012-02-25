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
import com.hashthrims.clients.web.vaadin.views.managetraining.windows.IndividualEnrollWindow;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingInstructors;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ParticipantsEnrollViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ScheduleTrainingForm pform;
    private final ClientDataService data = new ClientDataService();
    private final Table scheduledCoursesTable;
    private final HorizontalLayout people = new HorizontalLayout();
    private final HorizontalLayout enrollPanel = new HorizontalLayout();
    private final Button enrollButton = new Button("Enroll Paricipants");
    private final Button individualEnrollButton = new Button("Enroll Individual Paricipant");
    private final Button bulkyEnrollButton = new Button("Enroll Group Participants");
    private final ListSelect peopleList = new ListSelect("Select People");
    private final ListSelect trainers = new ListSelect("List of Trainers for selected Course");
    private Long courseId;
    private Collection<Long> participantsId;

    public ParticipantsEnrollViewPage(HashThrimsMain app) {
        individualEnrollButton.setSizeFull();

        bulkyEnrollButton.setSizeFull();

        enrollPanel.setSizeFull();
        enrollPanel.addComponent(individualEnrollButton);
        enrollPanel.addComponent(bulkyEnrollButton);
        scheduledCoursesTable = getTable();
        main = app;
        setSizeFull();
        pform = new ScheduleTrainingForm();
        form = pform.createShortCourseFrom();

        peopleList.setWidth("500px");
        trainers.setWidth("200px");
        trainers.setNullSelectionAllowed(false);

        // Add Listeners
        enrollButton.addListener((ClickListener) this);
        individualEnrollButton.addListener((ClickListener) this);
        bulkyEnrollButton.addListener((ClickListener) this);
        ScheduleTrainingBean bean = new ScheduleTrainingBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        List<ScheduledCourses> courses = data.getScheduledCoursesType().findAll();

        for (ScheduledCourses course : courses) {
            scheduledCoursesTable.addItem(new Object[]{
                        course.getCourseName(),
                        course.getCourseCapacity(),
                        course.getVenue(),
                        course.getOrganisationName(),
                        getDate(course.getStartDate()),
                        getDate(course.getEndDate()),
                        course.getNumOfStuds(),
                        getStatus(course),}, course.getId());

        }

        List<Person> personsList = data.getPersonService().findAll();
        Collections.sort(personsList);
        for (Person person : personsList) {
            peopleList.addItem(person.getId());
            peopleList.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }

        peopleList.setImmediate(true);
        peopleList.setNewItemsAllowed(false);
        peopleList.addListener((ValueChangeListener) this);
        peopleList.setNullSelectionAllowed(false);
        peopleList.setMultiSelect(true);

        Object[] properties = {"Start Date"};
        boolean[] ascending = {false};
        scheduledCoursesTable.sort(properties, ascending);
        addComponent(enrollPanel);
        Label headerLabel = new Label("List of Scheduled Courses");
        headerLabel.setStyleName(Reindeer.LABEL_H1);
        addComponent(headerLabel);
        addComponent(scheduledCoursesTable);
        setComponentAlignment(scheduledCoursesTable, Alignment.TOP_CENTER);
        people.addComponent(peopleList);
        people.addComponent(trainers);

        setComponentAlignment(scheduledCoursesTable, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == scheduledCoursesTable) {
            Long courseValue = (Long) property.getValue();
            courseId = courseValue;
            ScheduledCourses cour = data.getScheduledCoursesType().find(courseValue);
            List<TrainingInstructors> tr = cour.getClassInstructors();
            trainers.removeAllItems();
            for (TrainingInstructors trainer : tr) {
                trainers.addItem(trainer.getFirstName() + " " + trainer.getLastName());
            }
        }

        if (property == peopleList) {
            Collection<Long> ids = (Collection<Long>) property.getValue();
            participantsId = ids;

        }

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == enrollButton) {
            enrollParticipant(courseId, participantsId);
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "ENROLL"));
        }

        if (source == bulkyEnrollButton) {

            if (courseId != null) {
                addComponent(people);
                addComponent(enrollButton);
            } else {
                main.getMainWindow().showNotification("No Course Selection Made", "Please Select The Course and Click on Enroll Again", Notification.TYPE_ERROR_MESSAGE);
            }
        }

        if (source == individualEnrollButton) {

            if (courseId != null) {
                ScheduledCourses cour = data.getScheduledCoursesType().find(courseId);
                main.getMainWindow().addWindow(new IndividualEnrollWindow(cour, main));
            } else {
                main.getMainWindow().showNotification("No Course Selection Made", "Please Select The Course and Click on Enroll Again", Notification.TYPE_ERROR_MESSAGE);
            }
        }

    }

    private Table getTable() {
        Table table = new Table();
        table.setSizeFull();
        table.setMultiSelect(false);
        table.setSelectable(true);
        table.setImmediate(true);
        table.addListener((ValueChangeListener) this);
        table.setColumnCollapsingAllowed(true);
        table.addContainerProperty("Course Name", String.class, null);
        table.addContainerProperty("Places", Integer.class, null);
        table.addContainerProperty("Course Venue", String.class, null);
        table.addContainerProperty("Organisation Offering", String.class, null);
        table.addContainerProperty("Start Date", Date.class, null);
        table.addContainerProperty("End Date", Date.class, null);
        table.addContainerProperty("Enrollement", Integer.class, null);
        table.addContainerProperty("Status", String.class, null);
        return table;
    }

    private Date getDate(Date date) {
        //return new SimpleDateFormat("dd-MMMM-yyyy").format(startDate);
        return date;
    }

    private String getStatus(ScheduledCourses course) {
        if (course.getNumOfStuds() < course.getCourseCapacity()) {
            return "OPEN";
        }
        return "FULL";
    }

    private void enrollParticipant(Long courseId, Collection<Long> participantsId) {
        ScheduledCourses course = data.getScheduledCoursesType().find(courseId);
        TrainingCourses tc = data.getTrainingCoursesService().find(course.getCourseId());
        TrainingCourseRequestors req = data.getTrainingCourseRequestorsType().find(course.getCourseRequestor());
        EmployeeCourses empc = new EmployeeCourses();
        empc.setCourse(tc);
        empc.setScheduledCourseSessionId(courseId);
        empc.setCourseEndDate(course.getEndDate());
        empc.setCourseStartDate(course.getStartDate());
        empc.setRequestor(req);

        if (course.getCourseCapacity() >= participantsId.size()) {
            for (Long personId : participantsId) {
                if (course.getCourseCapacity() != course.getNumOfStuds()) {
                    Person p = data.getPersonService().find(personId);
                    //ADD COURSE TO PERSON
                    p.getCourses().add(empc);
                    data.getPersonService().merge(p);
                    int cp = course.getNumOfStuds();
                    course.setNumOfStuds(++cp);
                    //UPDATE NUMBER OF PEOPLE COUNTER 
                    data.getScheduledCoursesType().merge(course);
                } else {
                    main.getMainWindow().showNotification("Sorry!! This Course is Now FULL", "Either increase the Course capacity or Deregister Some People", Notification.TYPE_ERROR_MESSAGE);
                }
            }
        } else {

            main.getMainWindow().showNotification("Sorry!! Your Selection is Greater than Course Capacity", "Either increase the Course capacity or Deregister Some People", Notification.TYPE_ERROR_MESSAGE);

        }

    }
}
