/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.repository.jpa.IdentificationTypeDAO;
import com.hashthrims.services.IdentificationTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("identificationTypeService")
@Transactional
public class IdentificationTypeImplService implements IdentificationTypeService{
     @Autowired
    private IdentificationTypeDAO identificationTypeDAO;


    @Override
    public IdentificationType find(Long id) {
        if(id!= null){
        return identificationTypeDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(IdentificationType entity) {
        identificationTypeDAO.persist(entity);
    }

    @Override
    public void merge(IdentificationType entity) {
        identificationTypeDAO.merge(entity);
    }

    @Override
    public void remove(IdentificationType entity) {

        identificationTypeDAO.remove(entity);
    }

    @Override
    public List<IdentificationType> findAll() {
         return  identificationTypeDAO.findAll();
    }

    @Override
    public List<IdentificationType> findInRange(int firstResult, int maxResults) {
         return identificationTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  identificationTypeDAO.count();
    }

    @Override
    public IdentificationType getByPropertyName(String propertyName, String propertyValue) {
                return identificationTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public IdentificationTypeDAO getIdentificationTypeDAO() {
        return identificationTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setIdentificationTypeDAO(IdentificationTypeDAO identificationTypeDAO) {
        this.identificationTypeDAO = identificationTypeDAO;
    }

    @Override
    public List<IdentificationType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return identificationTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
