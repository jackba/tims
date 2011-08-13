/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.ManageTrainingMenuView;
import com.hashthrims.clients.web.vaadin.views.managetraining.forms.ScheduleTrainingForm;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Reindeer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ScheduleTrainingTable extends Table {

    private final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;
    private final TimsUtil st = new TimsUtil();

    public ScheduleTrainingTable(HashThrimsMain app, final ScheduleTrainingForm pform, final  Form form) {
        main = app;
        // Make Table fill all space
        setSizeFull();
        //setImmediate(true);
        // Define the names and data types of columns.




        addContainerProperty("Course Name", String.class, null);
        addContainerProperty("Places", Integer.class, null);
        addContainerProperty("Course Venue", String.class, null);
        addContainerProperty("Organisation Offering", String.class, null);
        addContainerProperty("Start Date", String.class, null);
        addContainerProperty("End Date", String.class, null);
        addContainerProperty("Enrollement", Integer.class, null);
        addContainerProperty("Status", String.class, null);
        addContainerProperty("Edit", Button.class, null);
        addContainerProperty("Delete", Button.class, null);




        // Add Data Columns
        final List<ScheduledCourses> courses = data.getScheduledCoursesType().findAll();
        for (final ScheduledCourses course : courses) {


            Button edit = new Button("Edit", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                   pform.getUpdateCources().setVisible(true); 
                   form.setReadOnly(false);
                }
            });

            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    
                    if (course.getNumOfStuds()<100) {
                        data.getScheduledCoursesType().remove(course);
                    } else {
                        
                        main.getMainWindow().showNotification("YOU CANNOT DELETE THIS SESSION", " There are participants registered for this Session. First Deregister Participants and then Try Again",Notification.TYPE_ERROR_MESSAGE);
                        
                    }
                    
                    main.mainView.setSecondComponent(new ManageTrainingMenuView(main, "SCH"));
                }
            });
            delete.setStyleName(Reindeer.BUTTON_LINK);
            edit.setStyleName(Reindeer.BUTTON_LINK);


            addItem(new Object[]{
                        course.getCourseName(),
                        course.getCourseCapacity(),
                        course.getVenue(),
                        course.getOrganisationName(),
                        getDate(course.getStartDate()),
                        getDate(course.getEndDate()),
                        course.getNumOfStuds(),
                        getStatus(course),
                        edit, delete}, course.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

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
}
