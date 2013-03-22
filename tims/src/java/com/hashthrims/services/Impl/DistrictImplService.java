/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.regionlist.District;
import com.hashthrims.repository.jpa.DistrictDAO;
import com.hashthrims.services.DistrictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("districtService")

public class DistrictImplService implements DistrictService{
     @Autowired
    private DistrictDAO districtDAO;


    @Override
    public District find(Long id) {
        if(id!= null){
        return districtDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(District entity) {
        districtDAO.persist(entity);
    }

    @Override
    public void merge(District entity) {
        districtDAO.merge(entity);
    }

    @Override
    public void remove(District entity) {

        districtDAO.remove(entity);
    }

    @Override
    public List<District> findAll() {
         return  districtDAO.findAll();
    }

    @Override
    public List<District> findInRange(int firstResult, int maxResults) {
         return districtDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  districtDAO.count();
    }

    @Override
    public District getByPropertyName(String propertyName, String propertyValue) {
                return districtDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DistrictDAO getDistrictDAO() {
        return districtDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDistrictDAO(DistrictDAO districtDAO) {
        this.districtDAO = districtDAO;
    }

    @Override
    public List<District> getEntitiesByProperName(String propertyName, String propertyValue) {
        return districtDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
