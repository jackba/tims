/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import com.hashthrims.repository.jpa.TrainingCourseStatusDAO;
import com.hashthrims.services.TrainingCourseStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author staff
 */
@Service("trainingCourseStatusService")

public class TrainingCourseStatusImplService implements TrainingCourseStatusService{

    @Autowired
    private TrainingCourseStatusDAO trainingCourseStatusDAO;


    @Override
    public TrainingCourseStatus find(Long id) {
        if(id!= null){
        return trainingCourseStatusDAO.find(id);
        }
        return null;
    }

    @Override
    public void persist(TrainingCourseStatus entity) {
        trainingCourseStatusDAO.persist(entity);
    }

    @Override
    public void merge(TrainingCourseStatus entity) {
        trainingCourseStatusDAO.merge(entity);
    }

    @Override
    public void remove(TrainingCourseStatus entity) {

        trainingCourseStatusDAO.remove(entity);
    }

    @Override
    public List<TrainingCourseStatus> findAll() {
         return  trainingCourseStatusDAO.findAll();
    }

    @Override
    public List<TrainingCourseStatus> findInRange(int firstResult, int maxResults) {
         return trainingCourseStatusDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingCourseStatusDAO.count();
    }

    @Override
    public TrainingCourseStatus getByPropertyName(String propertyName, String propertyValue) {
                return trainingCourseStatusDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingCourseStatusDAO getTrainingCourseStatusDAO() {
        return trainingCourseStatusDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingCourseStatusDAO(TrainingCourseStatusDAO trainingCourseStatusDAO) {
        this.trainingCourseStatusDAO = trainingCourseStatusDAO;
    }

    @Override
    public List<TrainingCourseStatus> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingCourseStatusDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
