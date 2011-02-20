package com.hashthrims.services.Impl;

import com.hashthrims.domain.Education;
import com.hashthrims.repository.jpa.EducationDAO;
import com.hashthrims.services.EducationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("educationService")
@Transactional
public class EducationImplService implements EducationService{
     @Autowired
    private EducationDAO educationDAO;


    @Override
    public Education find(Long id) {
        if(id!= null){
        return educationDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Education entity) {
        educationDAO.persist(entity);
    }

    @Override
    public void merge(Education entity) {
        educationDAO.merge(entity);
    }

    @Override
    public void remove(Education entity) {

        educationDAO.remove(entity);
    }

    @Override
    public List<Education> findAll() {
         return  educationDAO.findAll();
    }

    @Override
    public List<Education> findInRange(int firstResult, int maxResults) {
         return educationDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  educationDAO.count();
    }

    @Override
    public Education getByPropertyName(String propertyName, String propertyValue) {
                return educationDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EducationDAO getEducationDAO() {
        return educationDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEducationDAO(EducationDAO educationDAO) {
        this.educationDAO = educationDAO;
    }

    @Override
    public List<Education> getEntitiesByProperName(String propertyName, String propertyValue) {
        return educationDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}