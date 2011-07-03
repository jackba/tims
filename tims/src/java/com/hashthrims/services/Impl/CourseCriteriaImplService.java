/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.CourseCriteria;
import com.hashthrims.repository.jpa.CourseCriteriaDAO;
import com.hashthrims.services.CourseCriteriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("courseCriteriaService")
@Transactional
public class CourseCriteriaImplService implements CourseCriteriaService{
     @Autowired
    private CourseCriteriaDAO courseCriteriaDAO;


    @Override
    public CourseCriteria find(Long id) {
        if(id!= null){
        return courseCriteriaDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(CourseCriteria entity) {
        courseCriteriaDAO.persist(entity);
    }

    @Override
    public void merge(CourseCriteria entity) {
        courseCriteriaDAO.merge(entity);
    }

    @Override
    public void remove(CourseCriteria entity) {

        courseCriteriaDAO.remove(entity);
    }

    @Override
    public List<CourseCriteria> findAll() {
         return  courseCriteriaDAO.findAll();
    }

    @Override
    public List<CourseCriteria> findInRange(int firstResult, int maxResults) {
         return courseCriteriaDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  courseCriteriaDAO.count();
    }

    @Override
    public CourseCriteria getByPropertyName(String propertyName, String propertyValue) {
                return courseCriteriaDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CourseCriteriaDAO getCourseCriteriaDAO() {
        return courseCriteriaDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCourseCriteriaDAO(CourseCriteriaDAO CourseCriteriaDAO) {
        this.courseCriteriaDAO = CourseCriteriaDAO;
    }

    @Override
    public List<CourseCriteria> getEntitiesByProperName(String propertyName, String propertyValue) {
        return courseCriteriaDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
