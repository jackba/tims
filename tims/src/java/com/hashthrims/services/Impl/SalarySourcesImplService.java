/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.repository.jpa.SalarySourcesDAO;
import com.hashthrims.services.SalarySourcesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("salarySourcesService")
@Transactional
public class SalarySourcesImplService implements SalarySourcesService{
     @Autowired
    private SalarySourcesDAO salarySourcesDAO;


    @Override
    public SalarySources find(Long id) {
        if(id!= null){
        return salarySourcesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(SalarySources entity) {
        salarySourcesDAO.persist(entity);
    }

    @Override
    public void merge(SalarySources entity) {
        salarySourcesDAO.merge(entity);
    }

    @Override
    public void remove(SalarySources entity) {

        salarySourcesDAO.remove(entity);
    }

    @Override
    public List<SalarySources> findAll() {
         return  salarySourcesDAO.findAll();
    }

    @Override
    public List<SalarySources> findInRange(int firstResult, int maxResults) {
         return salarySourcesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  salarySourcesDAO.count();
    }

    @Override
    public SalarySources getByPropertyName(String propertyName, String propertyValue) {
                return salarySourcesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public SalarySourcesDAO getSalarySourcesDAO() {
        return salarySourcesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setSalarySourcesDAO(SalarySourcesDAO positionDAO) {
        this.salarySourcesDAO = salarySourcesDAO;
    }

    @Override
    public List<SalarySources> getEntitiesByProperName(String propertyName, String propertyValue) {
        return salarySourcesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
