/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.Demography;
import com.hashthrims.repository.jpa.DemographyDAO;
import com.hashthrims.services.DemographyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("demographyService")

public class DemographyImplService implements DemographyService{
     @Autowired
    private DemographyDAO demographyDAO;


    @Override
    public Demography find(Long id) {
        if(id!= null){
        return demographyDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Demography entity) {
        demographyDAO.persist(entity);
    }

    @Override
    public void merge(Demography entity) {
        demographyDAO.merge(entity);
    }

    @Override
    public void remove(Demography entity) {

        demographyDAO.remove(entity);
    }

    @Override
    public List<Demography> findAll() {
         return  demographyDAO.findAll();
    }

    @Override
    public List<Demography> findInRange(int firstResult, int maxResults) {
         return demographyDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  demographyDAO.count();
    }

    @Override
    public Demography getByPropertyName(String propertyName, String propertyValue) {
                return demographyDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DemographyDAO getDemographyDAO() {
        return demographyDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDemographyDAO(DemographyDAO demographyDAO) {
        this.demographyDAO = demographyDAO;
    }

    @Override
    public List<Demography> getEntitiesByProperName(String propertyName, String propertyValue) {
        return demographyDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
