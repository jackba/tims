/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services.Impl;

import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.repository.jpa.SalaryGradesDAO;
import com.hashthrims.services.SalaryGradesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("salaryGradesService")
public class SalaryGradesImplService implements SalaryGradesService {

    @Autowired
    private SalaryGradesDAO salaryGradesDAO;

    @Override
    public SalaryGrade find(Long id) {
        if (id != null) {
            return salaryGradesDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(SalaryGrade entity) {
        salaryGradesDAO.persist(entity);
    }

    @Override
    public void merge(SalaryGrade entity) {
        salaryGradesDAO.merge(entity);
    }

    @Override
    public void remove(SalaryGrade entity) {

        salaryGradesDAO.remove(entity);
    }

    @Override
    public List<SalaryGrade> findAll() {
        return salaryGradesDAO.findAll();
    }

    @Override
    public List<SalaryGrade> findInRange(int firstResult, int maxResults) {
        return salaryGradesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return salaryGradesDAO.count();
    }

    @Override
    public SalaryGrade getByPropertyName(String propertyName, String propertyValue) {
        return salaryGradesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public SalaryGradesDAO getSalaryGradesDAO() {
        return salaryGradesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setSalaryGradesDAO(SalaryGradesDAO salaryGradesDAO) {
        this.salaryGradesDAO = salaryGradesDAO;
    }

    @Override
    public List<SalaryGrade> getEntitiesByProperName(String propertyName, String propertyValue) {
        return salaryGradesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
