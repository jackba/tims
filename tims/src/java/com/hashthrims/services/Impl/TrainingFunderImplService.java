/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.repository.jpa.TrainingFunderDAO;
import com.hashthrims.services.TrainingFunderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author staff
 */
@Service("trainingFunderService")

public class TrainingFunderImplService implements TrainingFunderService{

    @Autowired
    private TrainingFunderDAO trainingFunderDAO;


    @Override
    public TrainingFunder find(Long id) {
        if(id!= null){
        return trainingFunderDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(TrainingFunder entity) {
        trainingFunderDAO.persist(entity);
    }

    @Override
    public void merge(TrainingFunder entity) {
        trainingFunderDAO.merge(entity);
    }

    @Override
    public void remove(TrainingFunder entity) {

        trainingFunderDAO.remove(entity);
    }

    @Override
    public List<TrainingFunder> findAll() {
         return  trainingFunderDAO.findAll();
    }

    @Override
    public List<TrainingFunder> findInRange(int firstResult, int maxResults) {
         return trainingFunderDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingFunderDAO.count();
    }

    @Override
    public TrainingFunder getByPropertyName(String propertyName, String propertyValue) {
                return trainingFunderDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingFunderDAO getTrainingFunderDAO() {
        return trainingFunderDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingFunderDAO(TrainingFunderDAO trainingFunderDAO) {
        this.trainingFunderDAO = trainingFunderDAO;
    }

    @Override
    public List<TrainingFunder> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingFunderDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
