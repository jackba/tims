/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.infrastructure.util.TimsUtil;
import java.util.List;

/**
 *
 * @author stud
 */
public class TrainingCoursesTable extends com.vaadin.ui.Table {

    private static ClientDataService data = new ClientDataService();
    private HashThrimsMain main;
    private TimsUtil st = new TimsUtil();

    public TrainingCoursesTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Training Field", String.class, null);
        addContainerProperty("Status", String.class, null);
        addContainerProperty("Type", String.class, null);
        addContainerProperty("Offered By", String.class, null);
        addContainerProperty("Objective", String.class, null);

        // Add Data Columns
        List<TrainingCourses> trainingCoursess = data.getTrainingCoursesService().findAll();
        for (TrainingCourses trainingCourses : trainingCoursess) {
            addItem(new Object[]{trainingCourses.getCourseName(),
                        st.getCategoryName(trainingCourses.getCourseCategory()),
                        st.getStatus(trainingCourses.getCourseStatus()),
                        st.getCourseType(trainingCourses.getCourseType()),
                        st.getTrainingInstitution(trainingCourses.getInstitutionName()),
                        trainingCourses.getCourseObjectives()}, trainingCourses.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
