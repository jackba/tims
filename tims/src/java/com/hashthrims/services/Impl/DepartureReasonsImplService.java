/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.repository.jpa.DepartureReasonsDAO;
import com.hashthrims.services.DepartureReasonsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("departureReasonsService")

public class DepartureReasonsImplService implements DepartureReasonsService{
     @Autowired
    private DepartureReasonsDAO departureReasonsDAO;


    @Override
    public DepartureReasons find(Long id) {
        if(id!= null){
        return departureReasonsDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(DepartureReasons entity) {
        departureReasonsDAO.persist(entity);
    }

    @Override
    public void merge(DepartureReasons entity) {
        departureReasonsDAO.merge(entity);
    }

    @Override
    public void remove(DepartureReasons entity) {

        departureReasonsDAO.remove(entity);
    }

    @Override
    public List<DepartureReasons> findAll() {
         return  departureReasonsDAO.findAll();
    }

    @Override
    public List<DepartureReasons> findInRange(int firstResult, int maxResults) {
         return departureReasonsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  departureReasonsDAO.count();
    }

    @Override
    public DepartureReasons getByPropertyName(String propertyName, String propertyValue) {
                return departureReasonsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DepartureReasonsDAO getDepartureReasonsDAO() {
        return departureReasonsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDepartureReasonsDAO(DepartureReasonsDAO departureReasonsDAO) {
        this.departureReasonsDAO = departureReasonsDAO;
    }

    @Override
    public List<DepartureReasons> getEntitiesByProperName(String propertyName, String propertyValue) {
        return departureReasonsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
