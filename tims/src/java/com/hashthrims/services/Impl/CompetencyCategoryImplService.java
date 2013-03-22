/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.nimart.CompetencyCategory;
import com.hashthrims.repository.jpa.CompetencyCategoryDAO;
import com.hashthrims.services.CompetencyCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("competencyCategoryService")

public class CompetencyCategoryImplService implements  CompetencyCategoryService{
     @Autowired
    private CompetencyCategoryDAO competencyCategoryDAO;


    @Override
    public CompetencyCategory find(Long id) {
        if(id!= null){
        return competencyCategoryDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(CompetencyCategory entity) {
        competencyCategoryDAO.persist(entity);
    }

    @Override
    public void merge(CompetencyCategory entity) {
        competencyCategoryDAO.merge(entity);
    }

    @Override
    public void remove(CompetencyCategory entity) {

        competencyCategoryDAO.remove(entity);
    }

    @Override
    public List<CompetencyCategory> findAll() {
         return  competencyCategoryDAO.findAll();
    }

    @Override
    public List<CompetencyCategory> findInRange(int firstResult, int maxResults) {
         return competencyCategoryDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  competencyCategoryDAO.count();
    }

    @Override
    public CompetencyCategory getByPropertyName(String propertyName, String propertyValue) {
                return competencyCategoryDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CompetencyCategoryDAO getCompetencyCategoryDAO() {
        return competencyCategoryDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCompetencyCategoryDAO(CompetencyCategoryDAO competencyCategoryDAO) {
        this.competencyCategoryDAO = competencyCategoryDAO;
    }

    @Override
    public List<CompetencyCategory> getEntitiesByProperName(String propertyName, String propertyValue) {
        return competencyCategoryDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
