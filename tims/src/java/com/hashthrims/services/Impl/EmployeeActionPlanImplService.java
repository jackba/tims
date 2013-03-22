/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeeActionPlan;
import com.hashthrims.repository.jpa.EmployeeActionPlanDAO;
import com.hashthrims.services.EmployeeActionPlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("employeeActionPlanService")

public class EmployeeActionPlanImplService implements EmployeeActionPlanService{
     @Autowired
    private EmployeeActionPlanDAO employeeActionPlanDAO;


    @Override
    public EmployeeActionPlan find(Long id) {
        if(id!= null){
        return employeeActionPlanDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(EmployeeActionPlan entity) {
        employeeActionPlanDAO.persist(entity);
    }

    @Override
    public void merge(EmployeeActionPlan entity) {
        employeeActionPlanDAO.merge(entity);
    }

    @Override
    public void remove(EmployeeActionPlan entity) {

        employeeActionPlanDAO.remove(entity);
    }

    @Override
    public List<EmployeeActionPlan> findAll() {
         return  employeeActionPlanDAO.findAll();
    }

    @Override
    public List<EmployeeActionPlan> findInRange(int firstResult, int maxResults) {
         return employeeActionPlanDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  employeeActionPlanDAO.count();
    }

    @Override
    public EmployeeActionPlan getByPropertyName(String propertyName, String propertyValue) {
                return employeeActionPlanDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmployeeActionPlanDAO getEmployeeActionPlanDAO() {
        return employeeActionPlanDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEmployeeActionPlanDAO(EmployeeActionPlanDAO EmployeeActionPlanDAO) {
        this.employeeActionPlanDAO = EmployeeActionPlanDAO;
    }

    @Override
    public List<EmployeeActionPlan> getEntitiesByProperName(String propertyName, String propertyValue) {
        return employeeActionPlanDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
