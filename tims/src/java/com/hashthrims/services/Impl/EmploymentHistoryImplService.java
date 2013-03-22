/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmploymentHistory;
import com.hashthrims.repository.jpa.EmploymentHistoryDAO;
import com.hashthrims.services.EmploymentHistoryService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Service("employmenthistoryService")

public class EmploymentHistoryImplService implements EmploymentHistoryService{
     @Autowired
    private EmploymentHistoryDAO employmentHistoryDAO;


    @Override
    public EmploymentHistory find(Long id) {
        if(id!= null){
        return employmentHistoryDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(EmploymentHistory entity) {
        employmentHistoryDAO.persist(entity);
    }

    @Override
    public void merge(EmploymentHistory entity) {
        employmentHistoryDAO.merge(entity);
    }

    @Override
    public void remove(EmploymentHistory entity) {

        employmentHistoryDAO.remove(entity);
    }

    @Override
    public List<EmploymentHistory> findAll() {
         return  employmentHistoryDAO.findAll();
    }

    @Override
    public List<EmploymentHistory> findInRange(int firstResult, int maxResults) {
         return employmentHistoryDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  employmentHistoryDAO.count();
    }

    @Override
    public EmploymentHistory getByPropertyName(String propertyName, String propertyValue) {
                return employmentHistoryDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmploymentHistoryDAO getEmploymentHistoryDAO() {
        return employmentHistoryDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEmploymentHistoryDAO(EmploymentHistoryDAO employmentHistoryDAO) {
        this.employmentHistoryDAO = employmentHistoryDAO;
    }

    @Override
    public List<EmploymentHistory> getEntitiesByProperName(String propertyName, String propertyValue) {
        return employmentHistoryDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
