/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.repository.jpa.CountryDAO;
import com.hashthrims.services.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("countryService")

public class CountryImplService implements CountryService{
     @Autowired
    private CountryDAO countryDAO;


    @Override
    public Country find(Long id) {
        if(id!= null){
        return countryDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Country entity) {
        countryDAO.persist(entity);
    }

    @Override
    public void merge(Country entity) {
        countryDAO.merge(entity);
    }

    @Override
    public void remove(Country entity) {

        countryDAO.remove(entity);
    }

    @Override
    public List<Country> findAll() {
         return  countryDAO.findAll();
    }

    @Override
    public List<Country> findInRange(int firstResult, int maxResults) {
         return countryDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  countryDAO.count();
    }

    @Override
    public Country getByPropertyName(String propertyName, String propertyValue) {
                return countryDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCountryDAO(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public List<Country> getEntitiesByProperName(String propertyName, String propertyValue) {
        return countryDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
