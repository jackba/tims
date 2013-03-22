/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import com.hashthrims.repository.jpa.TrainingCourseEvaluationDAO;
import com.hashthrims.services.TrainingCourseEvaluationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author staff
 */
@Service("trainingCourseEvaluationService")

public class TrainingCourseEvaluationImplService implements TrainingCourseEvaluationService{

    @Autowired
    private TrainingCourseEvaluationDAO trainingCourseEvaluationDAO;


    @Override
    public TrainingCourseEvaluation find(Long id) {
        if(id!= null){
        return trainingCourseEvaluationDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(TrainingCourseEvaluation entity) {
        trainingCourseEvaluationDAO.persist(entity);
    }

    @Override
    public void merge(TrainingCourseEvaluation entity) {
        trainingCourseEvaluationDAO.merge(entity);
    }

    @Override
    public void remove(TrainingCourseEvaluation entity) {

        trainingCourseEvaluationDAO.remove(entity);
    }

    @Override
    public List<TrainingCourseEvaluation> findAll() {
         return  trainingCourseEvaluationDAO.findAll();
    }

    @Override
    public List<TrainingCourseEvaluation> findInRange(int firstResult, int maxResults) {
         return trainingCourseEvaluationDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingCourseEvaluationDAO.count();
    }

    @Override
    public TrainingCourseEvaluation getByPropertyName(String propertyName, String propertyValue) {
                return trainingCourseEvaluationDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingCourseEvaluationDAO getTrainingCourseEvaluationDAO() {
        return trainingCourseEvaluationDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingCourseEvaluationDAO(TrainingCourseEvaluationDAO trainingCourseEvaluationDAO) {
        this.trainingCourseEvaluationDAO = trainingCourseEvaluationDAO;
    }

    @Override
    public List<TrainingCourseEvaluation> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingCourseEvaluationDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

}
