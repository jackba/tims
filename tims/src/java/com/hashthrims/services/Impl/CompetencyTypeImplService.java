/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.CompetencyTypeDAO;
import com.hashthrims.services.CompetencyTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("competencyTypeService")

public class CompetencyTypeImplService implements CompetencyTypeService{
     @Autowired
    private CompetencyTypeDAO competencyTypeDAO;


    @Override
    public CompetencyType find(Long id) {
        if(id!= null){
        return competencyTypeDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(CompetencyType entity) {
        competencyTypeDAO.persist(entity);
    }

    @Override
    public void merge(CompetencyType entity) {
        competencyTypeDAO.merge(entity);
    }

    @Override
    public void remove(CompetencyType entity) {

        competencyTypeDAO.remove(entity);
    }

    @Override
    public List<CompetencyType> findAll() {
         return  competencyTypeDAO.findAll();
    }

    @Override
    public List<CompetencyType> findInRange(int firstResult, int maxResults) {
         return competencyTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  competencyTypeDAO.count();
    }

    @Override
    public CompetencyType getByPropertyName(String propertyName, String propertyValue) {
                return competencyTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CompetencyTypeDAO getCompetencyTypeDAO() {
        return competencyTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCompetencyTypeDAO(CompetencyTypeDAO competencyTypeDAO) {
        this.competencyTypeDAO = competencyTypeDAO;
    }

    @Override
    public List<CompetencyType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return competencyTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
