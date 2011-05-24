/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.positions.GlobalPositions;
import com.hashthrims.repository.jpa.GlobalPositionsDAO;
import com.hashthrims.services.GlobalPositionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("globalPositionsService")
@Transactional
public class GlobalPositionsImplService implements GlobalPositionsService{
     @Autowired
    private GlobalPositionsDAO globalPositionsDAO;

    @Override
    public GlobalPositions find(Long id) {
        if(id!= null){
        return getGlobalPositionsDAO().find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(GlobalPositions entity) {
        getGlobalPositionsDAO().persist(entity);
    }

    @Override
    public void merge(GlobalPositions entity) {
        getGlobalPositionsDAO().merge(entity);
    }

    @Override
    public void remove(GlobalPositions entity) {

        getGlobalPositionsDAO().remove(entity);
    }

    @Override
    public List<GlobalPositions> findAll() {
         return  getGlobalPositionsDAO().findAll();
    }

    @Override
    public List<GlobalPositions> findInRange(int firstResult, int maxResults) {
         return getGlobalPositionsDAO().findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  getGlobalPositionsDAO().count();
    }

    @Override
    public GlobalPositions getByPropertyName(String propertyName, String propertyValue) {
                return getGlobalPositionsDAO().getByPropertyName(propertyName, propertyValue);
    }

   

    @Override
    public List<GlobalPositions> getEntitiesByProperName(String propertyName, String propertyValue) {
        return getGlobalPositionsDAO().getEntitiesByProperName(propertyName, propertyValue);
    }

    /**
     * @return the globalPositionsDAO
     */
    public GlobalPositionsDAO getGlobalPositionsDAO() {
        return globalPositionsDAO;
    }

    /**
     * @param globalPositionsDAO the globalPositionsDAO to set
     */
    public void setGlobalPositionsDAO(GlobalPositionsDAO globalPositionsDAO) {
        this.globalPositionsDAO = globalPositionsDAO;
    }


}
