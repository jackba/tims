/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.regionlist.City;
import com.hashthrims.repository.jpa.CityDAO;
import com.hashthrims.services.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("cityService")

public class CityImplService implements CityService{
     @Autowired
    private CityDAO cityDAO;


    @Override
    public City find(Long id) {
        if(id!= null){
        return cityDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(City entity) {
        cityDAO.persist(entity);
    }

    @Override
    public void merge(City entity) {
        cityDAO.merge(entity);
    }

    @Override
    public void remove(City entity) {

        cityDAO.remove(entity);
    }

    @Override
    public List<City> findAll() {
         return  cityDAO.findAll();
    }

    @Override
    public List<City> findInRange(int firstResult, int maxResults) {
         return cityDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  cityDAO.count();
    }

    @Override
    public City getByPropertyName(String propertyName, String propertyValue) {
                return cityDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CityDAO getCityDAO() {
        return cityDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public List<City> getEntitiesByProperName(String propertyName, String propertyValue) {
        return cityDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
