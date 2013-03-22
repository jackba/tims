/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.repository.jpa.DegreeDAO;
import com.hashthrims.services.DegreeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("degreeService")

public class DegreeImplService implements DegreeService{
     @Autowired
    private DegreeDAO degreeDAO;


    @Override
    public Degree find(Long id) {
        if(id!= null){
        return degreeDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Degree entity) {
        degreeDAO.persist(entity);
    }

    @Override
    public void merge(Degree entity) {
        degreeDAO.merge(entity);
    }

    @Override
    public void remove(Degree entity) {

        degreeDAO.remove(entity);
    }

    @Override
    public List<Degree> findAll() {
         return  degreeDAO.findAll();
    }

    @Override
    public List<Degree> findInRange(int firstResult, int maxResults) {
         return degreeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  degreeDAO.count();
    }

    @Override
    public Degree getByPropertyName(String propertyName, String propertyValue) {
                return degreeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DegreeDAO getDegreeDAO() {
        return degreeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDegreeDAO(DegreeDAO degreeDAO) {
        this.degreeDAO = degreeDAO;
    }

    @Override
    public List<Degree> getEntitiesByProperName(String propertyName, String propertyValue) {
        return degreeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
