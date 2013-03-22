/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.CourseTargetGroup;
import com.hashthrims.repository.jpa.CourseTargetGroupDAO;
import com.hashthrims.services.CourseTargetGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("courseTargetGroupService")

public class CourseTargetGroupImplService implements CourseTargetGroupService{
     @Autowired
    private CourseTargetGroupDAO courseTargetGroupDAO;


    @Override
    public CourseTargetGroup find(Long id) {
        if(id!= null){
        return courseTargetGroupDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(CourseTargetGroup entity) {
        courseTargetGroupDAO.persist(entity);
    }

    @Override
    public void merge(CourseTargetGroup entity) {
        courseTargetGroupDAO.merge(entity);
    }

    @Override
    public void remove(CourseTargetGroup entity) {

        courseTargetGroupDAO.remove(entity);
    }

    @Override
    public List<CourseTargetGroup> findAll() {
         return  courseTargetGroupDAO.findAll();
    }

    @Override
    public List<CourseTargetGroup> findInRange(int firstResult, int maxResults) {
         return courseTargetGroupDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  courseTargetGroupDAO.count();
    }

    @Override
    public CourseTargetGroup getByPropertyName(String propertyName, String propertyValue) {
                return courseTargetGroupDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CourseTargetGroupDAO getCourseTargetGroupDAO() {
        return courseTargetGroupDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCourseTargetGroupDAO(CourseTargetGroupDAO CourseTargetGroupDAO) {
        this.courseTargetGroupDAO = CourseTargetGroupDAO;
    }

    @Override
    public List<CourseTargetGroup> getEntitiesByProperName(String propertyName, String propertyValue) {
        return courseTargetGroupDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
