/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class MentoringCourseTable {

    private Person person;
    private GridLayout table;
    private List<GridLayout> tables;
    private HashThrimsMain main;
    private static final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();
    private MentoringCourseWindow subwindow;

    public Table getMentoringCourseTable(final Person person, HashThrimsMain app) {
        main = app;
        this.person = person;

        Table mt = new Table("Mentoring Sessions Participated In");
        mt.setSizeFull();
        mt.addContainerProperty("Date", Date.class, null);
        mt.addContainerProperty("Mentoring Session", String.class, null);
        mt.addContainerProperty("Number Patients ", String.class, null);
        mt.addContainerProperty("Hours", String.class, null);
        mt.addContainerProperty("Venue", String.class, null);
        mt.addContainerProperty("Competencies", String.class, null);
         mt.setNullSelectionAllowed(false);

        mt.setSelectable(true);
        // Send changes in selection immediately to server.
        mt.setImmediate(true);
        List<EmployeeMentoring> employeeMentorings = person.getMentoring();
        for (EmployeeMentoring em : employeeMentorings) {

            mt.addItem(new Object[]{
                        em.getMentoringDate(),
                        st.getTypeOfMentoringSession(em.getMentoringSession()),
                        em.getPatientsInitiated(),
                        em.getHoursSpent(),
                        st.getVenue(em.getVenue()),
                        st.getEmployeeCompetencies(em.getMentoringSession())
                        }, em.getId());
        }
        return mt;
    }
}
