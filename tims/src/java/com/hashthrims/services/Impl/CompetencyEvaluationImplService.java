/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.repository.jpa.CompetencyEvaluationDAO;
import com.hashthrims.services.CompetencyEvaluationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("competencyEvaluationService")
@Transactional
public class CompetencyEvaluationImplService implements CompetencyEvaluationService{
     @Autowired
    private CompetencyEvaluationDAO competencyEvaluationDAO;


    @Override
    public CompetencyEvaluation find(Long id) {
        if(id!= null){
        return competencyEvaluationDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(CompetencyEvaluation entity) {
        competencyEvaluationDAO.persist(entity);
    }

    @Override
    public void merge(CompetencyEvaluation entity) {
        competencyEvaluationDAO.merge(entity);
    }

    @Override
    public void remove(CompetencyEvaluation entity) {

        competencyEvaluationDAO.remove(entity);
    }

    @Override
    public List<CompetencyEvaluation> findAll() {
         return  competencyEvaluationDAO.findAll();
    }

    @Override
    public List<CompetencyEvaluation> findInRange(int firstResult, int maxResults) {
         return competencyEvaluationDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  competencyEvaluationDAO.count();
    }

    @Override
    public CompetencyEvaluation getByPropertyName(String propertyName, String propertyValue) {
                return competencyEvaluationDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CompetencyEvaluationDAO getCompetencyEvaluationDAO() {
        return competencyEvaluationDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCompetencyEvaluationDAO(CompetencyEvaluationDAO competencyEvaluationDAO) {
        this.competencyEvaluationDAO = competencyEvaluationDAO;
    }

    @Override
    public List<CompetencyEvaluation> getEntitiesByProperName(String propertyName, String propertyValue) {
        return competencyEvaluationDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
