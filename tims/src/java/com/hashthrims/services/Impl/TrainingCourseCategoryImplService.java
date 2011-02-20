/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.repository.jpa.TrainingCourseCategoryDAO;
import com.hashthrims.services.TrainingCourseCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("trainingCourseCategoryService")
@Transactional
public class TrainingCourseCategoryImplService implements TrainingCourseCategoryService{

    @Autowired
    private TrainingCourseCategoryDAO trainingCourseCategoryDAO;


    @Override
    public TrainingCourseCategory find(Long id) {
        if(id!= null){
        return trainingCourseCategoryDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(TrainingCourseCategory entity) {
        trainingCourseCategoryDAO.persist(entity);
    }

    @Override
    public void merge(TrainingCourseCategory entity) {
        trainingCourseCategoryDAO.merge(entity);
    }

    @Override
    public void remove(TrainingCourseCategory entity) {

        trainingCourseCategoryDAO.remove(entity);
    }

    @Override
    public List<TrainingCourseCategory> findAll() {
         return  trainingCourseCategoryDAO.findAll();
    }

    @Override
    public List<TrainingCourseCategory> findInRange(int firstResult, int maxResults) {
         return trainingCourseCategoryDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  trainingCourseCategoryDAO.count();
    }

    @Override
    public TrainingCourseCategory getByPropertyName(String propertyName, String propertyValue) {
                return trainingCourseCategoryDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TrainingCourseCategoryDAO getTrainingCourseCategoryDAO() {
        return trainingCourseCategoryDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTrainingCourseCategoryDAO(TrainingCourseCategoryDAO trainingCourseCategoryDAO) {
        this.trainingCourseCategoryDAO = trainingCourseCategoryDAO;
    }

    @Override
    public List<TrainingCourseCategory> getEntitiesByProperName(String propertyName, String propertyValue) {
        return trainingCourseCategoryDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

}
