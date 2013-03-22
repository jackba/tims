/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.regionlist.Currency;
import com.hashthrims.repository.jpa.CurrencyDAO;
import com.hashthrims.services.CurrencyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("currencyService")

public class CurrencyImplService implements CurrencyService{
     @Autowired
    private CurrencyDAO currencyDAO;


    @Override
    public Currency find(Long id) {
        if(id!= null){
        return currencyDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Currency entity) {
        currencyDAO.persist(entity);
    }

    @Override
    public void merge(Currency entity) {
        currencyDAO.merge(entity);
    }

    @Override
    public void remove(Currency entity) {

        currencyDAO.remove(entity);
    }

    @Override
    public List<Currency> findAll() {
         return  currencyDAO.findAll();
    }

    @Override
    public List<Currency> findInRange(int firstResult, int maxResults) {
         return currencyDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  currencyDAO.count();
    }

    @Override
    public Currency getByPropertyName(String propertyName, String propertyValue) {
                return currencyDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CurrencyDAO getCurrencyDAO() {
        return currencyDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCurrencyDAO(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Override
    public List<Currency> getEntitiesByProperName(String propertyName, String propertyValue) {
        return currencyDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
