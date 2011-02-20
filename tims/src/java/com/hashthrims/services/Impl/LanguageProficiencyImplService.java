/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.employeelist.LanguageProficiency;
import com.hashthrims.repository.jpa.LanguageProficiencyDAO;
import com.hashthrims.services.LangauageProficiencyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("languageProficiencyService")
@Transactional
public class LanguageProficiencyImplService implements LangauageProficiencyService{
     @Autowired
    private LanguageProficiencyDAO languageProficiencyDAO;


    @Override
    public LanguageProficiency find(Long id) {
        if(id!= null){
        return getLanguageProficiencyDAO().find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(LanguageProficiency entity) {
        getLanguageProficiencyDAO().persist(entity);
    }

    @Override
    public void merge(LanguageProficiency entity) {
        getLanguageProficiencyDAO().merge(entity);
    }

    @Override
    public void remove(LanguageProficiency entity) {

        getLanguageProficiencyDAO().remove(entity);
    }

    @Override
    public List<LanguageProficiency> findAll() {
         return  getLanguageProficiencyDAO().findAll();
    }

    @Override
    public List<LanguageProficiency> findInRange(int firstResult, int maxResults) {
         return getLanguageProficiencyDAO().findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  getLanguageProficiencyDAO().count();
    }

    @Override
    public LanguageProficiency getByPropertyName(String propertyName, String propertyValue) {
                return getLanguageProficiencyDAO().getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    

    @Override
    public List<LanguageProficiency> getEntitiesByProperName(String propertyName, String propertyValue) {
        return getLanguageProficiencyDAO().getEntitiesByProperName(propertyName, propertyValue);
    }

    /**
     * @return the languageProficiencyDAO
     */
    public LanguageProficiencyDAO getLanguageProficiencyDAO() {
        return languageProficiencyDAO;
    }

    /**
     * @param languageProficiencyDAO the languageProficiencyDAO to set
     */
    public void setLanguageProficiencyDAO(LanguageProficiencyDAO languageProficiencyDAO) {
        this.languageProficiencyDAO = languageProficiencyDAO;
    }


}
