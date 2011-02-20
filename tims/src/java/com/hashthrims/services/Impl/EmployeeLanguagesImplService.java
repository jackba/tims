package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.repository.jpa.EmployeeLanguagesDAO;
import com.hashthrims.services.EmployeeLanguagesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("employeeLanguagesService")
@Transactional
public class EmployeeLanguagesImplService implements EmployeeLanguagesService{
     @Autowired
    private EmployeeLanguagesDAO languagesDAO;


    @Override
    public EmployeeLanguages find(Long id) {
        if(id!= null){
        return languagesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(EmployeeLanguages entity) {
        languagesDAO.persist(entity);
    }

    @Override
    public void merge(EmployeeLanguages entity) {
        languagesDAO.merge(entity);
    }

    @Override
    public void remove(EmployeeLanguages entity) {

        languagesDAO.remove(entity);
    }

    @Override
    public List<EmployeeLanguages> findAll() {
         return  languagesDAO.findAll();
    }

    @Override
    public List<EmployeeLanguages> findInRange(int firstResult, int maxResults) {
         return languagesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  languagesDAO.count();
    }

    @Override
    public EmployeeLanguages getByPropertyName(String propertyName, String propertyValue) {
                return languagesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmployeeLanguagesDAO getLanguagesDAO() {
        return languagesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setLanguagesDAO(EmployeeLanguagesDAO languagesDAO) {
        this.languagesDAO = languagesDAO;
    }

    @Override
    public List<EmployeeLanguages> getEntitiesByProperName(String propertyName, String propertyValue) {
        return languagesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}