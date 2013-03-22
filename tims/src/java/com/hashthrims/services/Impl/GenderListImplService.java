/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.repository.jpa.GenderListDAO;
import com.hashthrims.services.GenderListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("genderListService")

public class GenderListImplService implements GenderListService{
     @Autowired
    private GenderListDAO genderListDAO;


    @Override
    public GenderList find(Long id) {
        if(id!= null){
        return genderListDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(GenderList entity) {
        genderListDAO.persist(entity);
    }

    @Override
    public void merge(GenderList entity) {
        genderListDAO.merge(entity);
    }

    @Override
    public void remove(GenderList entity) {

        genderListDAO.remove(entity);
    }

    @Override
    public List<GenderList> findAll() {
         return  genderListDAO.findAll();
    }

    @Override
    public List<GenderList> findInRange(int firstResult, int maxResults) {
         return genderListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  genderListDAO.count();
    }

    @Override
    public GenderList getByPropertyName(String propertyName, String propertyValue) {
                return genderListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public GenderListDAO getGenderListDAO() {
        return genderListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setGenderListDAO(GenderListDAO GenderListDAO) {
        this.genderListDAO = GenderListDAO;
    }

    @Override
    public List<GenderList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return genderListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
