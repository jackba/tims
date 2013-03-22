/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.Facility;
import com.hashthrims.repository.jpa.FacilityDAO;
import com.hashthrims.services.FacilityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("facilityService")

public class FacilityImplService implements FacilityService{
     @Autowired
    private FacilityDAO facilityDAO;


    @Override
    public Facility find(Long id) {
        if(id!= null){
        return facilityDAO.find(id);
        }
        return null;
    }

   
    @Override
    public void persist(Facility entity) {
        facilityDAO.persist(entity);
    }

    @Override
    public void merge(Facility entity) {
        facilityDAO.merge(entity);
    }

    @Override
    public void remove(Facility entity) {

        facilityDAO.remove(entity);
    }

    @Override
    public List<Facility> findAll() {
         return  facilityDAO.findAll();
    }

    @Override
    public List<Facility> findInRange(int firstResult, int maxResults) {
         return facilityDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  facilityDAO.count();
    }

    @Override
    public Facility getByPropertyName(String propertyName, String propertyValue) {
                return facilityDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public FacilityDAO getFacilityDAO() {
        return facilityDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setFacilityDAO(FacilityDAO facilityDAO) {
        this.facilityDAO = facilityDAO;
    }

    @Override
    public List<Facility> getEntitiesByProperName(String propertyName, String propertyValue) {
        return facilityDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
