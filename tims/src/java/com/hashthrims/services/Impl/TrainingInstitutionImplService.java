/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.repository.jpa.TrainingInstitutionDAO;
import com.hashthrims.services.TrainingInstitutionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("trainingInstitutionService")
@Transactional
public class TrainingInstitutionImplService implements TrainingInstitutionService{

    @Autowired
    private TrainingInstitutionDAO trainingInstitutionDAO;


    @Override
    public TrainingInstitution find(Long id) {
        if(id!= null){
        return trainingInstitutionDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(TrainingInstitution entity) {
        trainingInstitutionDAO.persist(entity);
    }

    @Override
    public void merge(TrainingInstitution entity) {
        trainingInstitutionDAO.merge(entity);
    }

    @Override
    public void remove(TrainingInstitution entity) {

        trainingInstitutionDAO.remove(entity);
    }

    @Override
    public List<TrainingInstitution> findAll() {
         return  trainingInstitutionDAO.findAll();
    }

    @Override
    public List<TrainingInstitution> findInRange(int firstResult, int maxResults) {
         return trainingInstitutionDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingInstitutionDAO.count();
    }

    @Override
    public TrainingInstitution getByPropertyName(String propertyName, String propertyValue) {
                return trainingInstitutionDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingInstitutionDAO getTrainingInstitutionDAO() {
        return trainingInstitutionDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingInstitutionDAO(TrainingInstitutionDAO trainingInstitutionDAO) {
        this.trainingInstitutionDAO = trainingInstitutionDAO;
    }

    @Override
    public List<TrainingInstitution> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingInstitutionDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
