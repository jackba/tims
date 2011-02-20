/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.ScheduledCourseBean;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ScheduledCourses extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button addShortCourse = ButtonLink.getButton("Schedule Training Course");
    private final Button editLink = ButtonLink.getButton("Edit");
    private final Button deleteLink = ButtonLink.getButton("Delete");
    private ScheduleCourseWindow subwindow;
    private final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public ScheduledCourses(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        addShortCourse.addListener((ClickListener) this);



        ScheduledCourseBean bean = new ScheduledCourseBean();
        subwindow = new ScheduleCourseWindow(person, bean, main);

        final Label headerLabel = new Label("Scheduled Courses :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(addShortCourse);


        menu.setSpacing(true);

        Table table = new Table();
        table.setColumnCollapsingAllowed(true);
        table.setSizeFull();


        List<EmployeeCourses> list = null;
        if (person.getCourses()!=null) {
            list = person.getCourses();
        } else {
            list= new ArrayList<EmployeeCourses>();
        }


        table.addContainerProperty("Course Name", String.class, null);
        table.addContainerProperty("Retraining ", String.class, null);
        table.addContainerProperty("Training Requestor", String.class, null);
        table.addContainerProperty("Start Date", Date.class, null);
        table.addContainerProperty("End Date ", Date.class, null);
        table.addContainerProperty("Date Requested", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final EmployeeCourses values : list) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    ScheduledCourseBean bean = getBean();
                    subwindow = new ScheduleCourseWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private ScheduledCourseBean getBean() {
                    ScheduledCourseBean bean = new ScheduledCourseBean();
                    bean.setCourseEndDate(values.getCourseEndDate());
                    bean.setCourseStartDate(values.getCourseStartDate());
                    bean.setId(values.getId());
                    bean.setDateRequested(values.getDateRequested());
                    bean.setRetraining(values.getRetraining());
                   
                   
                    return bean;
                }

                private String getCourseName(TrainingCourses course) {
                    if(course!=null)
                        return course.getCourseName();
                    return null;
                }

                private String getEvaluation(CompetencyEvaluation evaluation) {
                    if(evaluation!=null)
                        return evaluation.getCompt_type_name();
                    return null;
                }

                private String getRequestor(TrainingCourseRequestors requestor) {
                   if(requestor!=null)
                       return requestor.getRequestorName();
                   return null;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    data.getEmployeeCourseService().remove(values);
                    final Person p = data.getPersonService().find(person.getId());
                    final PersonDetailsView view = new PersonDetailsView(p, main, "COURSE");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        st.getCourseLabel(values.getCourse()),
                        values.getRetraining(),
                         st.getRequestor(values.getRequestor()),
                        values.getCourseStartDate(),
                        values.getCourseEndDate(),
                        values.getDateRequested(),
                        edit,
                        delete}, values.getId());

        }

        addComponent(menu);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == addShortCourse) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        } else if (source == editLink) {
        } else if (source == deleteLink) {
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
