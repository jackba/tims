/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.repository.jpa.BenefitTypeDAO;
import com.hashthrims.services.BenefitTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("benefitTypeService")
@Transactional
public class BenefitTypeImplService implements BenefitTypeService{
     @Autowired
    private BenefitTypeDAO benefitTypeDAO;


    @Override
    public BenefitType find(Long id) {
        if(id!= null){
        return benefitTypeDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(BenefitType entity) {
        benefitTypeDAO.persist(entity);
    }

    @Override
    public void merge(BenefitType entity) {
        benefitTypeDAO.merge(entity);
    }

    @Override
    public void remove(BenefitType entity) {

        benefitTypeDAO.remove(entity);
    }

    @Override
    public List<BenefitType> findAll() {
         return  benefitTypeDAO.findAll();
    }

    @Override
    public List<BenefitType> findInRange(int firstResult, int maxResults) {
         return benefitTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  benefitTypeDAO.count();
    }

    @Override
    public BenefitType getByPropertyName(String propertyName, String propertyValue) {
                return benefitTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public BenefitTypeDAO getBenefitTypeDAO() {
        return benefitTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setBenefitTypeDAO(BenefitTypeDAO benefitTypeDAO) {
        this.benefitTypeDAO = benefitTypeDAO;
    }

    @Override
    public List<BenefitType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return benefitTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
