/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingInstructors;
import com.hashthrims.repository.jpa.TrainingInstructorsDAO;
import com.hashthrims.services.TrainingInstructorsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("trainingInstructorsService")
@Transactional
public class TrainingInstructorsImplService implements TrainingInstructorsService{

    @Autowired
    private TrainingInstructorsDAO trainingInstructorsDAO;


    @Override
    public TrainingInstructors find(Long id) {
        if(id!= null){
        return trainingInstructorsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(TrainingInstructors entity) {
        trainingInstructorsDAO.persist(entity);
    }

    @Override
    public void merge(TrainingInstructors entity) {
        trainingInstructorsDAO.merge(entity);
    }

    @Override
    public void remove(TrainingInstructors entity) {

        trainingInstructorsDAO.remove(entity);
    }

    @Override
    public List<TrainingInstructors> findAll() {
         return  trainingInstructorsDAO.findAll();
    }

    @Override
    public List<TrainingInstructors> findInRange(int firstResult, int maxResults) {
         return trainingInstructorsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingInstructorsDAO.count();
    }

    @Override
    public TrainingInstructors getByPropertyName(String propertyName, String propertyValue) {
                return trainingInstructorsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingInstructorsDAO getTrainingInstructorsDAO() {
        return trainingInstructorsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingInstructorsDAO(TrainingInstructorsDAO trainingInstructorsDAO) {
        this.trainingInstructorsDAO = trainingInstructorsDAO;
    }

    @Override
    public List<TrainingInstructors> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingInstructorsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
