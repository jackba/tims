/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.positions.Status;
import com.hashthrims.repository.jpa.StatusDAO;
import com.hashthrims.services.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author stud
 */
@Service("statusService")

public class StatusImplService implements StatusService{
     @Autowired
    private StatusDAO statusDAO;


    @Override
    public Status find(Long id) {
        if(id!= null){
        return statusDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Status entity) {
        statusDAO.persist(entity);
    }

    @Override
    public void merge(Status entity) {
        statusDAO.merge(entity);
    }

    @Override
    public void remove(Status entity) {

        statusDAO.remove(entity);
    }

    @Override
    public List<Status> findAll() {
         return  statusDAO.findAll();
    }

    @Override
    public List<Status> findInRange(int firstResult, int maxResults) {
         return statusDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  statusDAO.count();
    }

    @Override
    public Status getByPropertyName(String propertyName, String propertyValue) {
                return statusDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public StatusDAO getStatusDAO() {
        return statusDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setStatusDAO(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @Override
    public List<Status> getEntitiesByProperName(String propertyName, String propertyValue) {
        return statusDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
