/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa.EducationTypeDAO;
import com.hashthrims.services.EducationTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("educationTypeService")

public class EducationTypeImplService implements EducationTypeService{
     @Autowired
    private EducationTypeDAO educationTypeDAO;


    @Override
    public EducationType find(Long id) {
        if(id!= null){
        return educationTypeDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(EducationType entity) {
        educationTypeDAO.persist(entity);
    }

    @Override
    public void merge(EducationType entity) {
        educationTypeDAO.merge(entity);
    }

    @Override
    public void remove(EducationType entity) {

        educationTypeDAO.remove(entity);
    }

    @Override
    public List<EducationType> findAll() {
         return  educationTypeDAO.findAll();
    }

    @Override
    public List<EducationType> findInRange(int firstResult, int maxResults) {
         return educationTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  educationTypeDAO.count();
    }

    @Override
    public EducationType getByPropertyName(String propertyName, String propertyValue) {
                return educationTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EducationTypeDAO getEducationTypeDAO() {
        return educationTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEducationTypeDAO(EducationTypeDAO educationTypeDAO) {
        this.educationTypeDAO = educationTypeDAO;
    }

    @Override
    public List<EducationType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return educationTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
