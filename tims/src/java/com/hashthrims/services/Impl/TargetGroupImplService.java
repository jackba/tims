/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.repository.jpa.TargetGroupDAO;
import com.hashthrims.services.TargetGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("targetGroupService")
@Transactional
public class TargetGroupImplService implements TargetGroupService{
     @Autowired
    private TargetGroupDAO targetGroupDAO;


    @Override
    public TargetGroup find(Long id) {
        if(id!= null){
        return targetGroupDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(TargetGroup entity) {
        targetGroupDAO.persist(entity);
    }

    @Override
    public void merge(TargetGroup entity) {
        targetGroupDAO.merge(entity);
    }

    @Override
    public void remove(TargetGroup entity) {

        targetGroupDAO.remove(entity);
    }

    @Override
    public List<TargetGroup> findAll() {
         return  targetGroupDAO.findAll();
    }

    @Override
    public List<TargetGroup> findInRange(int firstResult, int maxResults) {
         return targetGroupDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  targetGroupDAO.count();
    }

    @Override
    public TargetGroup getByPropertyName(String propertyName, String propertyValue) {
                return targetGroupDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public TargetGroupDAO getTargetGroupDAO() {
        return targetGroupDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setTargetGroupDAO(TargetGroupDAO TargetGroupDAO) {
        this.targetGroupDAO = TargetGroupDAO;
    }

    @Override
    public List<TargetGroup> getEntitiesByProperName(String propertyName, String propertyValue) {
        return targetGroupDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
