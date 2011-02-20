/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form.MentoringCourseBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form.ReportMentoringBean;
import com.hashthrims.domain.EmployeeMentoring;

import com.hashthrims.domain.Person;
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
public class MentoringPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button prePlanedMentoring = ButtonLink.getButton("Add Pre-Planned");
    private final Button mentoring = ButtonLink.getButton("Report Mentoring");
    private final TimsUtil st = new TimsUtil();
    private  MentoringCourseWindow subwindow;
    private ReportMentoringCourseWindow reportMentoringWindow;
    private final ClientDataService data = new ClientDataService();
    private final  Table table = new Table();

    public MentoringPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        prePlanedMentoring.addListener((ClickListener) this);
        mentoring.addListener((ClickListener) this);



        MentoringCourseBean bean = new MentoringCourseBean();
        ReportMentoringBean reportMentoringBean = new ReportMentoringBean();
        subwindow = new MentoringCourseWindow(person, bean, main,"MENT");
        reportMentoringWindow = new ReportMentoringCourseWindow(person,reportMentoringBean,main,"MENT");

        final Label headerLabel = new Label("Mentoring :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);

        menu.addComponent(mentoring);
        menu.addComponent(prePlanedMentoring);


        menu.setSpacing(true);

       
        
        table.setSizeFull();


        List<EmployeeMentoring> list = null;
        if (person.getMentoring()!=null) {
            list = person.getMentoring();
        } else {
           list = new ArrayList<EmployeeMentoring>();
        }

        table.addContainerProperty("Date", Date.class, null);
        table.addContainerProperty("Venue", String.class, null);
        table.addContainerProperty("Type of Session", String.class, null);
        table.addContainerProperty("Patients Initiated", String.class, null);
        table.addContainerProperty("Hours", String.class, null);
        table.addContainerProperty("Competencies", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final EmployeeMentoring values : list) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    MentoringCourseBean bean = getBean();
                    subwindow = new MentoringCourseWindow(person, bean, main,"MENT");
                    main.getMainWindow().addWindow(subwindow);

                }

                private MentoringCourseBean getBean() {
                    MentoringCourseBean bean = new MentoringCourseBean();
                    bean.setId(values.getId());
                    bean.setHoursSpent(values.getHoursSpent());
                    bean.setMentoringDate(values.getMentoringDate());
                    bean.setMentoringSession(st.getMentoringSessionId(values.getMentoringSession()));
//                    bean.setFaciltyType(st.getFacilityTypeId(values.getVenue()));
                    bean.setVenue(st.getFacilityId(values.getVenue()));
                    bean.setPatientsInitiated(values.getPatientsInitiated());
                    

                    return bean;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    data.getEmployeeMentoringService().remove(values);
                    final Person p = data.getPersonService().find(person.getId());
                    final PersonDetailsView view = new PersonDetailsView(p, main, "MENT");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        values.getMentoringDate(),
                        st.getVenue(values.getVenue()),
                        st.getTypeOfMentoringSession(values.getMentoringSession()),
                        values.getPatientsInitiated(),
                        values.getHoursSpent(),
                        st.getEmployeeCompetencies(values.getMentoringSession()),
                        edit,
                        delete
                        }, values.getId());

        }

        addComponent(menu);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == prePlanedMentoring) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        } else if (source == mentoring){
             if (reportMentoringWindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(reportMentoringWindow);
            }
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
     
    }
}
