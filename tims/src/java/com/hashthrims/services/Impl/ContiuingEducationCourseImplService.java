/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import com.hashthrims.repository.jpa.ContiuingEducationCourseDAO;
import com.hashthrims.services.ContiuingEducationCourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("contiuingEducationCourseService")

public class ContiuingEducationCourseImplService implements ContiuingEducationCourseService{

    @Autowired
    private ContiuingEducationCourseDAO ContiuingEducationCourseDAO;


    @Override
    public ContiuingEducationCourse find(Long id) {
        if(id!= null){
        return ContiuingEducationCourseDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(ContiuingEducationCourse entity) {
        ContiuingEducationCourseDAO.persist(entity);
    }

    @Override
    public void merge(ContiuingEducationCourse entity) {
        ContiuingEducationCourseDAO.merge(entity);
    }

    @Override
    public void remove(ContiuingEducationCourse entity) {

        ContiuingEducationCourseDAO.remove(entity);
    }

    @Override
    public List<ContiuingEducationCourse> findAll() {
         return  ContiuingEducationCourseDAO.findAll();
    }

    @Override
    public List<ContiuingEducationCourse> findInRange(int firstResult, int maxResults) {
         return ContiuingEducationCourseDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  ContiuingEducationCourseDAO.count();
    }

    @Override
    public ContiuingEducationCourse getByPropertyName(String propertyName, String propertyValue) {
                return ContiuingEducationCourseDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ContiuingEducationCourseDAO getContiuingEducationCourseDAO() {
        return ContiuingEducationCourseDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setContiuingEducationCourseDAO(ContiuingEducationCourseDAO ContiuingEducationCourseDAO) {
        this.ContiuingEducationCourseDAO = ContiuingEducationCourseDAO;
    }

    @Override
    public List<ContiuingEducationCourse> getEntitiesByProperName(String propertyName, String propertyValue) {
        return ContiuingEducationCourseDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

}
