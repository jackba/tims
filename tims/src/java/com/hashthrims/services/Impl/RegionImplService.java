/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.repository.jpa.RegionDAO;
import com.hashthrims.services.RegionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("regionService")
@Transactional
public class RegionImplService implements RegionService{
     @Autowired
    private RegionDAO regionDAO;


    @Override
    public Province find(Long id) {
        if(id!= null){
        return regionDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Province entity) {
        regionDAO.persist(entity);
    }

    @Override
    public void merge(Province entity) {
        regionDAO.merge(entity);
    }

    @Override
    public void remove(Province entity) {

        regionDAO.remove(entity);
    }

    @Override
    public List<Province> findAll() {
         return  regionDAO.findAll();
    }

    @Override
    public List<Province> findInRange(int firstResult, int maxResults) {
         return regionDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  regionDAO.count();
    }

    @Override
    public Province getByPropertyName(String propertyName, String propertyValue) {
                return regionDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    @Override
    public List<Province> getEntitiesByProperName(String propertyName, String propertyValue) {
        return regionDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
