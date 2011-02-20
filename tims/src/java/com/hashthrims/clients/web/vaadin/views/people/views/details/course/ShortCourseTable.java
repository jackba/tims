/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ShortCourseTable {

    private Person person;
    private HashThrimsMain main;
    private static final ClientDataService data = new ClientDataService();
    private ScheduleCourseWindow subwindow;
    private final TimsUtil st = new TimsUtil();

    public Table getShortCourseTable(final Person person, HashThrimsMain app) {
        main = app;
        this.person = person;



        Table mt = new Table("Courses");
        mt.setSizeFull();
        mt.setColumnCollapsingAllowed(true);

        mt.addContainerProperty("Course Name", String.class, null);
        mt.addContainerProperty("Competencies", String.class, null);
        mt.addContainerProperty("Retraining ", String.class, null);
        mt.addContainerProperty("Start Date", Date.class, null);
        mt.addContainerProperty("End Date ", Date.class, null);
        mt.addContainerProperty("Training Requestor", String.class, null);
        mt.addContainerProperty("Date Requested", String.class, null);
        mt.addContainerProperty("Evaluation", String.class, null);
        mt.addContainerProperty("Last Evaluation", String.class, null);
        mt.addContainerProperty("Training Status", String.class, null);
        mt.addContainerProperty("Notes", String.class, null);

     



        mt.setNullSelectionAllowed(false);

        mt.setSelectable(true);
        // Send changes in selection immediately to server.
        mt.setImmediate(true);
        List<EmployeeCourses> courses = person.getCourses();
        for (final EmployeeCourses em : courses) {

            mt.addItem(new Object[]{
                        st.getCourseLabel(em.getCourse()),
                        st.getCompetencies(em.getCourse()),
                        em.getRetraining(),
                        em.getCourseStartDate(),
                        em.getCourseEndDate(),
                        st.getRequestor(em.getRequestor()),
                        em.getDateRequested(),
                        st.getEvaluation(em.getEvaluation()),
                        em.getLastEvaluated(),
                        em.getTrainingStatus(),
                        em.getCompetencyNotes(),}, em.getId());
        }
        return mt;

    }
}
