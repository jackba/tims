/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows.traineeprofle;

import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class TraineeCoursesTable extends Table {

    public TraineeCoursesTable(Person person) {
        setSizeFull();
        addContainerProperty("Course Name", String.class, null);
        addContainerProperty("Course Start Date", String.class, null);
        addContainerProperty("Course End Date", String.class, null);
        addContainerProperty("Competency Evaluation", String.class, null);
        addContainerProperty("Evaluation  Date", String.class, null);
        addContainerProperty("Training Organisation ", String.class, null);




        final List<EmployeeCourses> employeeCourses = person.getCourses();
        for (EmployeeCourses employeeCourse : employeeCourses) {

            addItem(new Object[]{
                        getCourseName(employeeCourse.getCourse()),
                        employeeCourse.getCourseStartDate(),
                        employeeCourse.getCourseEndDate(),
                        getEvaluation(employeeCourse.getEvaluation()),
                        employeeCourse.getLastEvaluated(),
                        getInstitutionName(employeeCourse.getCourse())
                    }, employeeCourse.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }

    private String getCourseName(TrainingCourses course) {
        if (course != null) {
            return course.getCourseName();
        }
        return null;
    }

    private String getInstitutionName(TrainingCourses course) {
        if (course != null) {
            return getNameOfTrainingInstitution(course.getInstitutionName());
        }
        return null;
    }

    private String getEvaluation(CompetencyEvaluation evaluation) {
        if (evaluation != null) {
            return evaluation.getCompt_type_name();
        }
        return null;
    }

    private String getNameOfTrainingInstitution(TrainingInstitution institutionName) {
        if (institutionName != null) {
            return institutionName.getTrainingInstitution();
        }
        return null;
    }
}
