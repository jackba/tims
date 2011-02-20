/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.repository.jpa.TrainingDAO;
import com.hashthrims.services.TrainingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("trainingService")
@Transactional
public class TrainingImplService implements TrainingService{

    @Autowired
    private TrainingDAO trainingDAO;

    @Override
    public EmployeeMentoring find(Long id) {
        if(id!= null){
        return trainingDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(EmployeeMentoring entity) {
        trainingDAO.persist(entity);
    }

    @Override
    public void merge(EmployeeMentoring entity) {
        trainingDAO.merge(entity);
    }

    @Override
    public void remove(EmployeeMentoring entity) {

        trainingDAO.remove(entity);
    }

    @Override
    public List<EmployeeMentoring> findAll() {
         return  trainingDAO.findAll();
    }

    @Override
    public List<EmployeeMentoring> findInRange(int firstResult, int maxResults) {
         return trainingDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingDAO.count();
    }

    @Override
    public EmployeeMentoring getByPropertyName(String propertyName, String propertyValue) {
                return trainingDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingDAO getTrainingDAO() {
        return trainingDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public List<EmployeeMentoring> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

}
