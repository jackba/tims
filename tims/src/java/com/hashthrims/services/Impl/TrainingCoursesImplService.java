/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.repository.jpa.CourseCompetenciesDAO;
import com.hashthrims.repository.jpa.CourseFundersDAO;
import com.hashthrims.repository.jpa.TrainingCoursesDAO;
import com.hashthrims.services.TrainingCoursesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author staff
 */
@Service("trainingCoursesService")

public class TrainingCoursesImplService implements TrainingCoursesService{

    @Autowired
    private TrainingCoursesDAO trainingCoursesDAO;

    @Autowired
    private CourseCompetenciesDAO courseCompetenciesDAO;

    @Autowired
    private CourseFundersDAO  courseFundersDAO;


    @Override
    public TrainingCourses find(Long id) {
        if(id!= null){
        return trainingCoursesDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(TrainingCourses entity) {
        trainingCoursesDAO.persist(entity);
    }

    @Override
    public void merge(TrainingCourses entity) {
        trainingCoursesDAO.merge(entity);
    }

    @Override
    public void remove(TrainingCourses entity) {

        trainingCoursesDAO.remove(entity);
    }

    @Override
    public List<TrainingCourses> findAll() {
         return  trainingCoursesDAO.findAll();
    }

    @Override
    public List<TrainingCourses> findInRange(int firstResult, int maxResults) {
         return trainingCoursesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingCoursesDAO.count();
    }

    @Override
    public TrainingCourses getByPropertyName(String propertyName, String propertyValue) {
                return trainingCoursesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingCoursesDAO getTrainingCoursesDAO() {
        return trainingCoursesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingCoursesDAO(TrainingCoursesDAO trainingCoursesDAO) {
        this.trainingCoursesDAO = trainingCoursesDAO;
    }

    @Override
    public List<TrainingCourses> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingCoursesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

    @Override
    public TrainingCourses resetFundsAndCompetencies(TrainingCourses cs) {
        cs.getCourseFunders().clear();
        cs.getCourseCompetencies().clear();
        trainingCoursesDAO.merge(cs);
        return cs;
    }

    /**
     * @return the courseCompetenciesDAO
     */
    public CourseCompetenciesDAO getCourseCompetenciesDAO() {
        return courseCompetenciesDAO;
    }

    /**
     * @param courseCompetenciesDAO the courseCompetenciesDAO to set
     */
    public void setCourseCompetenciesDAO(CourseCompetenciesDAO courseCompetenciesDAO) {
        this.courseCompetenciesDAO = courseCompetenciesDAO;
    }

    /**
     * @return the courseFundersDAO
     */
    public CourseFundersDAO getCourseFundersDAO() {
        return courseFundersDAO;
    }

    /**
     * @param courseFundersDAO the courseFundersDAO to set
     */
    public void setCourseFundersDAO(CourseFundersDAO courseFundersDAO) {
        this.courseFundersDAO = courseFundersDAO;
    }
}
