/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.repository.jpa.TrainingCourseRequestorsDAO;
import com.hashthrims.services.TrainingCourseRequestorsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("trainingCourseRequestorsService")
@Transactional
public class TrainingCourseRequestorsImplService implements TrainingCourseRequestorsService{
@Autowired
    private TrainingCourseRequestorsDAO trainingCourseRequestorsDAO;


    @Override
    public TrainingCourseRequestors find(Long id) {
        if(id!= null){
        return trainingCourseRequestorsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(TrainingCourseRequestors entity) {
        trainingCourseRequestorsDAO.persist(entity);
    }

    @Override
    public void merge(TrainingCourseRequestors entity) {
        trainingCourseRequestorsDAO.merge(entity);
    }

    @Override
    public void remove(TrainingCourseRequestors entity) {

        trainingCourseRequestorsDAO.remove(entity);
    }

    @Override
    public List<TrainingCourseRequestors> findAll() {
         return  trainingCourseRequestorsDAO.findAll();
    }

    @Override
    public List<TrainingCourseRequestors> findInRange(int firstResult, int maxResults) {
         return trainingCourseRequestorsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingCourseRequestorsDAO.count();
    }

    @Override
    public TrainingCourseRequestors getByPropertyName(String propertyName, String propertyValue) {
                return trainingCourseRequestorsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingCourseRequestorsDAO getTrainingCourseRequestorsDAO() {
        return trainingCourseRequestorsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingCourseRequestorsDAO(TrainingCourseRequestorsDAO trainingCourseRequestorsDAO) {
        this.trainingCourseRequestorsDAO = trainingCourseRequestorsDAO;
    }

    @Override
    public List<TrainingCourseRequestors> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingCourseRequestorsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
