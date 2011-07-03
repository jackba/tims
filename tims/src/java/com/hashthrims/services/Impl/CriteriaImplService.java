/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.Criteria;
import com.hashthrims.repository.jpa.CriteriaDAO;
import com.hashthrims.services.CriteriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("criteriaService")
@Transactional
public class CriteriaImplService implements CriteriaService{
     @Autowired
    private CriteriaDAO criteriaDAO;


    @Override
    public Criteria find(Long id) {
        if(id!= null){
        return criteriaDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Criteria entity) {
        criteriaDAO.persist(entity);
    }

    @Override
    public void merge(Criteria entity) {
        criteriaDAO.merge(entity);
    }

    @Override
    public void remove(Criteria entity) {

        criteriaDAO.remove(entity);
    }

    @Override
    public List<Criteria> findAll() {
         return  criteriaDAO.findAll();
    }

    @Override
    public List<Criteria> findInRange(int firstResult, int maxResults) {
         return criteriaDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  criteriaDAO.count();
    }

    @Override
    public Criteria getByPropertyName(String propertyName, String propertyValue) {
                return criteriaDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CriteriaDAO getCriteriaDAO() {
        return criteriaDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCriteriaDAO(CriteriaDAO CriteriaDAO) {
        this.criteriaDAO = CriteriaDAO;
    }

    @Override
    public List<Criteria> getEntitiesByProperName(String propertyName, String propertyValue) {
        return criteriaDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
