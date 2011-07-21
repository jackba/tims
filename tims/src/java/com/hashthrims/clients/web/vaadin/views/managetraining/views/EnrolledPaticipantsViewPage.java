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
import com.hashthrims.clients.web.vaadin.views.managetraining.windows.ActionPlanPersonWindow;
import com.hashthrims.clients.web.vaadin.views.managetraining.windows.EvaluatePersonCourseWindow;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.EvaluateddCourseBean;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TrainingCourses;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EnrolledPaticipantsViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ScheduleTrainingForm pform;
    private final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final EmployeeFactory factory = data.getEmployeeFactory();
    private final Table scheduledCoursesTable;
    private final Table enrolledPeopleTable;
    private final HorizontalLayout people = new HorizontalLayout();
    private final VerticalLayout layout = new VerticalLayout();
    private final Button enrollButton = new Button("Enroll Paricipants");
    private final ListSelect peopleList = new ListSelect("Select People");
    private final ListSelect trainers = new ListSelect("List of Trainers for selected Course");
    private EvaluatePersonCourseWindow evaluateSubWindow;
    private ActionPlanPersonWindow actionPlanSubWindow;

    public EnrolledPaticipantsViewPage(HashThrimsMain app) {

        scheduledCoursesTable = getTable();
        enrolledPeopleTable = getEnrolledTable();
        main = app;
        setSizeFull();
        pform = new ScheduleTrainingForm();
        form = pform.createShortCourseFrom();

        peopleList.setWidth("500px");
        trainers.setWidth("200px");
        trainers.setNullSelectionAllowed(false);

        // Add Listeners
        pform.getSubmitListofAttendant().addListener((ClickListener) this);

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
        for (Person person : personsList) {
            peopleList.addItem(person.getId());
            peopleList.setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
        }

        peopleList.setImmediate(true);
        peopleList.setNewItemsAllowed(false);
        peopleList.addListener((ValueChangeListener) this);
        peopleList.setNullSelectionAllowed(false);
        peopleList.setMultiSelect(true);

        addComponent(scheduledCoursesTable);
        setComponentAlignment(scheduledCoursesTable, Alignment.TOP_CENTER);
        people.setSizeFull();
        people.addComponent(enrolledPeopleTable);
        addComponent(people);
        setComponentAlignment(people, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == scheduledCoursesTable) {
            final Long courseValue = (Long) property.getValue();
            final ScheduledCourses cour = data.getScheduledCoursesType().find(courseValue);
            List<Person> participants = getCourseParticipants(cour);
            enrolledPeopleTable.removeAllItems();
            for (final Person person : participants) {


                Button evaluateLink = new Button("Evaluate", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        EvaluateddCourseBean bean = getBean();
                        evaluateSubWindow = new EvaluatePersonCourseWindow(person, bean, main);
                        main.getMainWindow().addWindow(evaluateSubWindow);

                    }

                    private EvaluateddCourseBean getBean() {
                        EvaluateddCourseBean bean = new EvaluateddCourseBean();
                        List<EmployeeCourses> ecourses = person.getCourses();
                        for (EmployeeCourses c : ecourses) {
                            if (iscourseSame(cour.getCourseId(), c.getCourse())) {
                                bean.setEvaluation(getIdEvaluation(c.getEvaluation()));
                                bean.setEvaluationDate(c.getLastEvaluated());
                                bean.setId(c.getId());
                            }

                        }


                        return bean;
                    }

                    private Long getIdEvaluation(CompetencyEvaluation evaluation) {
                        if (evaluation != null) {
                            return evaluation.getId();
                        }
                        return null;
                    }

                    private boolean iscourseSame(Long courseId, TrainingCourses course) {
                        boolean isSame = false;
                        if (courseId != null && course != null) {
                            if (courseId.equals(course.getId())) {
                                isSame = true;
                            }
                        }
                        return isSame;
                    }
                });

                Button actionPlanLink = new Button("Action Plan", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        actionPlanSubWindow = new ActionPlanPersonWindow(person, cour, main);
                        main.getMainWindow().addWindow(actionPlanSubWindow);
                    }
                });


                evaluateLink.setStyleName(Reindeer.BUTTON_LINK);
                actionPlanLink.setStyleName(Reindeer.BUTTON_LINK);


                enrolledPeopleTable.addItem(new Object[]{
                            person.getPersonName(),
                            person.getPersonSurname(),
                            evaluateLink,
                            actionPlanLink}, person.getId());
            }

            List<TrainingInstructors> tr = cour.getClassInstructors();
            trainers.removeAllItems();
            for (TrainingInstructors trainer : tr) {
                trainers.addItem(trainer.getFirstName() + " " + trainer.getLastName());
            }
        }

        if (property == peopleList) {
            Collection<Long> mentorsIds = (Collection<Long>) property.getValue();

        }

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSubmitListofAttendant()) {


            //submitAttendanceList(form);
            main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "SCH"));
        }

    }

    private Table getTable() {
        Table table = new Table("Scheduled Courses");
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
        table.addContainerProperty("Start Date", String.class, null);
        table.addContainerProperty("End Date", String.class, null);
        table.addContainerProperty("Enrollement", Integer.class, null);
        table.addContainerProperty("Status", String.class, null);
        return table;
    }

    private String getDate(Date startDate) {
        return new SimpleDateFormat("dd-MMMM-yyyy").format(startDate);
    }

    private String getStatus(ScheduledCourses course) {
        if (course.getNumOfStuds() < course.getCourseCapacity()) {
            return "OPEN";
        }
        return "FULL";
    }

    private List<Person> getCourseParticipants(ScheduledCourses course) {
        List<Person> participants = checkPeopleWithThisCourse(data.getPersonService().findAll(), course);
        return participants;
    }

    private List<Person> checkPeopleWithThisCourse(List<Person> persons, ScheduledCourses course) {
        List<Person> participants = new ArrayList<Person>();
        for (Person person : persons) {
            List<EmployeeCourses> empc = person.getCourses();
            if (isCourseInEmployeList(empc, course)) {
                participants.add(person);
            }

        }

        return participants;
    }

    private boolean isCourseInEmployeList(List<EmployeeCourses> empc, ScheduledCourses course) {
        boolean isInList = false;
        for (EmployeeCourses employeeCourses : empc) {
            if (employeeCourses.getCourse() != null) {
                if (course.getCourseId().equals(employeeCourses.getCourse().getId())) {
                    isInList = true;
                }
            }
        }
        return isInList;
    }

    private Table getEnrolledTable() {
        Table table = new Table("Enrolled Participants");
        table.setSizeFull();
        table.setMultiSelect(false);
        table.setSelectable(true);
        table.setImmediate(true);
        table.addListener((ValueChangeListener) this);
        table.setColumnCollapsingAllowed(true);
        table.addContainerProperty("First Name", String.class, null);
        table.addContainerProperty("Surname", String.class, null);
        table.addContainerProperty("Evaluation", Button.class, null);
        table.addContainerProperty("Action Plane", Button.class, null);
        return table;
    }
}
