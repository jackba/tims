/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.regionlist.County;
import com.hashthrims.repository.jpa.CountyDAO;
import com.hashthrims.services.CountyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("countyService")

public class CountyImplService implements CountyService{
     @Autowired
    private CountyDAO countyDAO;


    @Override
    public County find(Long id) {
        if(id!= null){
        return countyDAO.find(id);
        }
        return null;
    }

   
    @Override
    public void persist(County entity) {
        countyDAO.persist(entity);
    }

    @Override
    public void merge(County entity) {
        countyDAO.merge(entity);
    }

    @Override
    public void remove(County entity) {

        countyDAO.remove(entity);
    }

    @Override
    public List<County> findAll() {
         return  countyDAO.findAll();
    }

    @Override
    public List<County> findInRange(int firstResult, int maxResults) {
         return countyDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  countyDAO.count();
    }

    @Override
    public County getByPropertyName(String propertyName, String propertyValue) {
                return countyDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CountyDAO getCountyDAO() {
        return countyDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCountyDAO(CountyDAO countyDAO) {
        this.countyDAO = countyDAO;
    }

    @Override
    public List<County> getEntitiesByProperName(String propertyName, String propertyValue) {
        return countyDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
