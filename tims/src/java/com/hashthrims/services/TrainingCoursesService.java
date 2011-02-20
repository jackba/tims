/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services;

import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.repository.JpaDAO;

/**
 *
 * @author staff
 */
public interface TrainingCoursesService extends JpaDAO<TrainingCourses, Long>{

    public TrainingCourses resetFundsAndCompetencies(TrainingCourses cs);

}
