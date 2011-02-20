/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.repository.jpa.RegistrationBodyDAO;
import com.hashthrims.services.RegistrationBodyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("registrationBodyService")
@Transactional
public class RegistrationBodyImplService implements RegistrationBodyService{
     @Autowired
    private RegistrationBodyDAO registrationBodyDAO;


    @Override
    public RegistrationBody find(Long id) {
        if(id!= null){
        return registrationBodyDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(RegistrationBody entity) {
        registrationBodyDAO.persist(entity);
    }

    @Override
    public void merge(RegistrationBody entity) {
        registrationBodyDAO.merge(entity);
    }

    @Override
    public void remove(RegistrationBody entity) {

        registrationBodyDAO.remove(entity);
    }

    @Override
    public List<RegistrationBody> findAll() {
         return  registrationBodyDAO.findAll();
    }

    @Override
    public List<RegistrationBody> findInRange(int firstResult, int maxResults) {
         return registrationBodyDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  registrationBodyDAO.count();
    }

    @Override
    public RegistrationBody getByPropertyName(String propertyName, String propertyValue) {
                return registrationBodyDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public RegistrationBodyDAO getRegistrationBodyDAO() {
        return registrationBodyDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setRegistrationBodyDAO(RegistrationBodyDAO registrationBodyDAO) {
        this.registrationBodyDAO = registrationBodyDAO;
    }

    @Override
    public List<RegistrationBody> getEntitiesByProperName(String propertyName, String propertyValue) {
        return registrationBodyDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
