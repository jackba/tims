/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.repository.jpa.MaritalStatusListDAO;
import com.hashthrims.services.MaritalStatusListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("maritalStatusListService")
@Transactional
public class MaritalStatusListImplService implements MaritalStatusListService{
     @Autowired
    private MaritalStatusListDAO maritalStatusListDAO;


    @Override
    public MaritalStatusList find(Long id) {
        if(id!= null){
        return maritalStatusListDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(MaritalStatusList entity) {
        maritalStatusListDAO.persist(entity);
    }

    @Override
    public void merge(MaritalStatusList entity) {
        maritalStatusListDAO.merge(entity);
    }

    @Override
    public void remove(MaritalStatusList entity) {

        maritalStatusListDAO.remove(entity);
    }

    @Override
    public List<MaritalStatusList> findAll() {
         return  maritalStatusListDAO.findAll();
    }

    @Override
    public List<MaritalStatusList> findInRange(int firstResult, int maxResults) {
         return maritalStatusListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  maritalStatusListDAO.count();
    }

    @Override
    public MaritalStatusList getByPropertyName(String propertyName, String propertyValue) {
                return maritalStatusListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MaritalStatusListDAO getMaritalStatusListDAO() {
        return maritalStatusListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMaritalStatusListDAO(MaritalStatusListDAO maritalStatusListDAO) {
        this.maritalStatusListDAO = maritalStatusListDAO;
    }

    @Override
    public List<MaritalStatusList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return maritalStatusListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
