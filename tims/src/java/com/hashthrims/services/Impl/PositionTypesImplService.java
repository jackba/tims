/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.repository.jpa.PositionTypesDAO;
import com.hashthrims.services.PositionTypesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("positionTypesService")
@Transactional
public class PositionTypesImplService implements PositionTypesService{
     @Autowired
    private PositionTypesDAO positionTypesDAO;


    @Override
    public PositionTypes find(Long id) {
        if(id!= null){
        return positionTypesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(PositionTypes entity) {
        positionTypesDAO.persist(entity);
    }

    @Override
    public void merge(PositionTypes entity) {
        positionTypesDAO.merge(entity);
    }

    @Override
    public void remove(PositionTypes entity) {

        positionTypesDAO.remove(entity);
    }

    @Override
    public List<PositionTypes> findAll() {
         return  positionTypesDAO.findAll();
    }

    @Override
    public List<PositionTypes> findInRange(int firstResult, int maxResults) {
         return positionTypesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  positionTypesDAO.count();
    }

    @Override
    public PositionTypes getByPropertyName(String propertyName, String propertyValue) {
                return positionTypesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public PositionTypesDAO getPositionTypesDAO() {
        return positionTypesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setPositionTypesDAO(PositionTypesDAO positionDAO) {
        this.positionTypesDAO = positionTypesDAO;
    }

    @Override
    public List<PositionTypes> getEntitiesByProperName(String propertyName, String propertyValue) {
        return positionTypesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
