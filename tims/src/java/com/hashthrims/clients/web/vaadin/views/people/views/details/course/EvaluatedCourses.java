/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.course.form.EvaluateddCourseBean;
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
public class EvaluatedCourses extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button addShortCourse = ButtonLink.getButton("EvaluateTraining Course");
    private final Button editLink = ButtonLink.getButton("Edit");
    private final Button deleteLink = ButtonLink.getButton("Delete");
    private EvaluateCourseWindow subwindow;
    private final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public EvaluatedCourses(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        addShortCourse.addListener((ClickListener) this);



        EvaluateddCourseBean bean = new EvaluateddCourseBean();
        subwindow = new EvaluateCourseWindow(person, bean, main);

        final Label headerLabel = new Label("Evaluate Courses :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        //menu.addComponent(addShortCourse);


        menu.setSpacing(true);

        Table table = new Table();
        table.setColumnCollapsingAllowed(true);
        table.setSizeFull();


        List<EmployeeCourses> list = null;
        if (person.getCourses() != null) {
            list = person.getCourses();
        } else {
            list = new ArrayList<EmployeeCourses>();
        }


        table.addContainerProperty("Course", String.class, null);
        table.addContainerProperty("Evaluation", String.class, null);
        table.addContainerProperty("Competencies", String.class, null);
        table.addContainerProperty("Evaluation Date", Date.class, null);
        table.addContainerProperty("Set Evaluation", Button.class, null);



        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final EmployeeCourses courses : list) {

            Button updateEvaluation = new Button("Set Evaluation", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    EvaluateddCourseBean bean = getBean();
                    subwindow = new EvaluateCourseWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private EvaluateddCourseBean getBean() {
                    EvaluateddCourseBean bean = new EvaluateddCourseBean();
                    bean.setEvaluation(getEvaluationId(courses.getEvaluation()));
                    bean.setEvaluationDate(courses.getLastEvaluated());
                    bean.setId(courses.getId());



                    return bean;
                }

                private String getCourseName(TrainingCourses course) {
                    if (course != null) {
                        return course.getCourseName();
                    }
                    return null;
                }

                private String getEvaluation(CompetencyEvaluation evaluation) {
                    if (evaluation != null) {
                        return evaluation.getCompt_type_name();
                    }
                    return null;
                }

                private String getRequestor(TrainingCourseRequestors requestor) {
                    if (requestor != null) {
                        return requestor.getRequestorName();
                    }
                    return null;
                }

                private Long getEvaluationId(CompetencyEvaluation evaluation) {
                    if (evaluation != null) {
                        return evaluation.getId();
                    }
                    return null;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    data.getEmployeeCourseService().remove(courses);
                    final Person p = data.getPersonService().find(person.getId());
                    final PersonDetailsView view = new PersonDetailsView(p, main, "COURSE");
                    main.mainView.setSecondComponent(view);

                }
            });

            updateEvaluation.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        st.getCourseName(courses.getCourse()),
                        st.getEvaluation(courses.getEvaluation()),
                        st.getCompetencies(courses.getCourse()),
                        courses.getLastEvaluated(),
                        updateEvaluation}, courses.getId());

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
