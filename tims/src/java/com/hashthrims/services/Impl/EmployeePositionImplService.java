/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.repository.jpa.EmployeePositionDAO;
import com.hashthrims.services.EmployeePositionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Service("employeePositionService")

public class EmployeePositionImplService implements EmployeePositionService{
     @Autowired
    private EmployeePositionDAO employeePositionDAO;


    @Override
    public EmployeePosition find(Long id) {
        if(id!= null){
        return employeePositionDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(EmployeePosition entity) {
        employeePositionDAO.persist(entity);
    }

    @Override
    public void merge(EmployeePosition entity) {
        employeePositionDAO.merge(entity);
    }

    @Override
    public void remove(EmployeePosition entity) {

        employeePositionDAO.remove(entity);
    }

    @Override
    public List<EmployeePosition> findAll() {
         return  employeePositionDAO.findAll();
    }

    @Override
    public List<EmployeePosition> findInRange(int firstResult, int maxResults) {
         return employeePositionDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  employeePositionDAO.count();
    }

    @Override
    public EmployeePosition getByPropertyName(String propertyName, String propertyValue) {
                return employeePositionDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmployeePositionDAO getEmployeePositionDAO() {
        return employeePositionDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEmployeePositionDAO(EmployeePositionDAO employeePositionDAO) {
        this.employeePositionDAO = employeePositionDAO;
    }

    @Override
    public List<EmployeePosition> getEntitiesByProperName(String propertyName, String propertyValue) {
        return employeePositionDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
