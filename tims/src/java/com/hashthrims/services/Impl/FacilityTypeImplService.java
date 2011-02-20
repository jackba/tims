/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.repository.jpa.FacilityTypeDAO;
import com.hashthrims.services.FacilityTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("facilityTypeService")
@Transactional
public class FacilityTypeImplService implements FacilityTypeService{
     @Autowired
    private FacilityTypeDAO facilityTypeDAO;


    @Override
    public FacilityType find(Long id) {
        if(id!= null){
        return facilityTypeDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(FacilityType entity) {
        facilityTypeDAO.persist(entity);
    }

    @Override
    public void merge(FacilityType entity) {
        facilityTypeDAO.merge(entity);
    }

    @Override
    public void remove(FacilityType entity) {

        facilityTypeDAO.remove(entity);
    }

    @Override
    public List<FacilityType> findAll() {
         return  facilityTypeDAO.findAll();
    }

    @Override
    public List<FacilityType> findInRange(int firstResult, int maxResults) {
         return facilityTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  facilityTypeDAO.count();
    }

    @Override
    public FacilityType getByPropertyName(String propertyName, String propertyValue) {
                return facilityTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public FacilityTypeDAO getFacilityTypeDAO() {
        return facilityTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setFacilityTypeDAO(FacilityTypeDAO facilityTypeDAO) {
        this.facilityTypeDAO = facilityTypeDAO;
    }

    @Override
    public List<FacilityType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return facilityTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
