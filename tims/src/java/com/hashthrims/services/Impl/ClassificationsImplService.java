/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.repository.jpa.ClassificationDAO;
import com.hashthrims.services.ClassificationsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("classificationsService")
@Transactional
public class ClassificationsImplService implements ClassificationsService{
     @Autowired
    private ClassificationDAO classificationsDAO;


    @Override
    public Classifications find(Long id) {
        if(id!= null){
        return classificationsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Classifications entity) {
        classificationsDAO.persist(entity);
    }

    @Override
    public void merge(Classifications entity) {
        classificationsDAO.merge(entity);
    }

    @Override
    public void remove(Classifications entity) {

        classificationsDAO.remove(entity);
    }

    @Override
    public List<Classifications> findAll() {
         return  classificationsDAO.findAll();
    }

    @Override
    public List<Classifications> findInRange(int firstResult, int maxResults) {
         return classificationsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  classificationsDAO.count();
    }

    @Override
    public Classifications getByPropertyName(String propertyName, String propertyValue) {
                return classificationsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ClassificationDAO getClassificationsDAO() {
        return classificationsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setClassificationsDAO(ClassificationDAO classificationsDAO) {
        this.classificationsDAO = classificationsDAO;
    }

    @Override
    public List<Classifications> getEntitiesByProperName(String propertyName, String propertyValue) {
        return classificationsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
