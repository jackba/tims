/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.repository.jpa.LanguageDAO;
import com.hashthrims.services.LanguageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("languageService")

public class LanguageImplService implements LanguageService{
     @Autowired
    private LanguageDAO languageDAO;


    @Override
    public Language find(Long id) {
        if(id!= null){
        return languageDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Language entity) {
        languageDAO.persist(entity);
    }

    @Override
    public void merge(Language entity) {
        languageDAO.merge(entity);
    }

    @Override
    public void remove(Language entity) {

        languageDAO.remove(entity);
    }

    @Override
    public List<Language> findAll() {
         return  languageDAO.findAll();
    }

    @Override
    public List<Language> findInRange(int firstResult, int maxResults) {
         return languageDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  languageDAO.count();
    }

    @Override
    public Language getByPropertyName(String propertyName, String propertyValue) {
                return languageDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public LanguageDAO getLanguageDAO() {
        return languageDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setLanguageDAO(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    @Override
    public List<Language> getEntitiesByProperName(String propertyName, String propertyValue) {
        return languageDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
