/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.repository.jpa.CompetencyListDAO;
import com.hashthrims.services.CompetencyListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("competencyListService")

public class CompetencyListImplService implements CompetencyListService{
     @Autowired
    private CompetencyListDAO competencyListDAO;


    @Override
    public CompetencyList find(Long id) {
        if(id!= null){
        return competencyListDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(CompetencyList entity) {
        competencyListDAO.persist(entity);
    }

    @Override
    public void merge(CompetencyList entity) {
        competencyListDAO.merge(entity);
    }

    @Override
    public void remove(CompetencyList entity) {

        competencyListDAO.remove(entity);
    }

    @Override
    public List<CompetencyList> findAll() {
         return  competencyListDAO.findAll();
    }

    @Override
    public List<CompetencyList> findInRange(int firstResult, int maxResults) {
         return competencyListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  competencyListDAO.count();
    }

    @Override
    public CompetencyList getByPropertyName(String propertyName, String propertyValue) {
                return competencyListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CompetencyListDAO getCompetencyListDAO() {
        return competencyListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCompetencyListDAO(CompetencyListDAO competencyListDAO) {
        this.competencyListDAO = competencyListDAO;
    }

    @Override
    public List<CompetencyList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return competencyListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
