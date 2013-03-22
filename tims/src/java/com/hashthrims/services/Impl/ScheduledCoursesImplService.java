/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.repository.jpa.ScheduledCoursesDAO;
import com.hashthrims.services.ScheduledCoursesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Service("scheduledCoursesService")

public class ScheduledCoursesImplService implements ScheduledCoursesService{

    @Autowired
    private ScheduledCoursesDAO scheduledCoursesDAO;


    @Override
    public ScheduledCourses find(Long id) {
        if(id!= null){
        return scheduledCoursesDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(ScheduledCourses entity) {
        scheduledCoursesDAO.persist(entity);
    }

    @Override
    public void merge(ScheduledCourses entity) {
        scheduledCoursesDAO.merge(entity);
    }

    @Override
    public void remove(ScheduledCourses entity) {

        scheduledCoursesDAO.remove(entity);
    }

    @Override
    public List<ScheduledCourses> findAll() {
         return  scheduledCoursesDAO.findAll();
    }

    @Override
    public List<ScheduledCourses> findInRange(int firstResult, int maxResults) {
         return scheduledCoursesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  scheduledCoursesDAO.count();
    }

    @Override
    public ScheduledCourses getByPropertyName(String propertyName, String propertyValue) {
                return scheduledCoursesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ScheduledCoursesDAO getScheduledCoursesDAO() {
        return scheduledCoursesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setScheduledCoursesDAO(ScheduledCoursesDAO scheduledCoursesDAO) {
        this.scheduledCoursesDAO = scheduledCoursesDAO;
    }

    @Override
    public List<ScheduledCourses> getEntitiesByProperName(String propertyName, String propertyValue) {
        return scheduledCoursesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

}
