/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.repository.jpa.EmployeeMentoringDAO;
import com.hashthrims.services.EmployeeMentoringService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("employeeMentoringService")
@Transactional
public class EmployeeMentoringImplService implements EmployeeMentoringService{
     @Autowired
    private EmployeeMentoringDAO employeeMentoringDAO;


    @Override
    public EmployeeMentoring find(Long id) {
        if(id!= null){
        return employeeMentoringDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(EmployeeMentoring entity) {
        employeeMentoringDAO.persist(entity);
    }

    @Override
    public void merge(EmployeeMentoring entity) {
        employeeMentoringDAO.merge(entity);
    }

    @Override
    public void remove(EmployeeMentoring entity) {

        employeeMentoringDAO.remove(entity);
    }

    @Override
    public List<EmployeeMentoring> findAll() {
         return  employeeMentoringDAO.findAll();
    }

    @Override
    public List<EmployeeMentoring> findInRange(int firstResult, int maxResults) {
         return employeeMentoringDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  employeeMentoringDAO.count();
    }

    @Override
    public EmployeeMentoring getByPropertyName(String propertyName, String propertyValue) {
                return employeeMentoringDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmployeeMentoringDAO getEmployeeMentoringDAO() {
        return employeeMentoringDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEmployeeMentoringDAO(EmployeeMentoringDAO EmployeeMentoringDAO) {
        this.employeeMentoringDAO = EmployeeMentoringDAO;
    }

    @Override
    public List<EmployeeMentoring> getEntitiesByProperName(String propertyName, String propertyValue) {
        return employeeMentoringDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
