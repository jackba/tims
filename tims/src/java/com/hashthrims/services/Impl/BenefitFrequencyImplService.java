/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.repository.jpa.BenefitFrequencyDAO;
import com.hashthrims.services.BenefitFrequencyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("benefitFrequencyService")

public class BenefitFrequencyImplService implements BenefitFrequencyService{
     @Autowired
    private BenefitFrequencyDAO benefitFrequencyDAO;


    @Override
    public BenefitFrequency find(Long id) {
        if(id!= null){
        return benefitFrequencyDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(BenefitFrequency entity) {
        benefitFrequencyDAO.persist(entity);
    }

    @Override
    public void merge(BenefitFrequency entity) {
        benefitFrequencyDAO.merge(entity);
    }

    @Override
    public void remove(BenefitFrequency entity) {

        benefitFrequencyDAO.remove(entity);
    }

    @Override
    public List<BenefitFrequency> findAll() {
         return  benefitFrequencyDAO.findAll();
    }

    @Override
    public List<BenefitFrequency> findInRange(int firstResult, int maxResults) {
         return benefitFrequencyDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  benefitFrequencyDAO.count();
    }

    @Override
    public BenefitFrequency getByPropertyName(String propertyName, String propertyValue) {
                return benefitFrequencyDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public BenefitFrequencyDAO getBenefitFrequencyDAO() {
        return benefitFrequencyDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setBenefitFrequencyDAO(BenefitFrequencyDAO benefitFrequencyDAO) {
        this.benefitFrequencyDAO = benefitFrequencyDAO;
    }

    @Override
    public List<BenefitFrequency> getEntitiesByProperName(String propertyName, String propertyValue) {
        return benefitFrequencyDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
