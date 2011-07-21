/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.repository.jpa.RaceListDAO;
import com.hashthrims.services.RaceListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("raceListService")
@Transactional
public class RaceListImplService implements RaceListService{
     @Autowired
    private RaceListDAO raceListDAO;


    @Override
    public RaceList find(Long id) {
        if(id!= null){
        return raceListDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(RaceList entity) {
        raceListDAO.persist(entity);
    }

    @Override
    public void merge(RaceList entity) {
        raceListDAO.merge(entity);
    }

    @Override
    public void remove(RaceList entity) {

        raceListDAO.remove(entity);
    }

    @Override
    public List<RaceList> findAll() {
         return  raceListDAO.findAll();
    }

    @Override
    public List<RaceList> findInRange(int firstResult, int maxResults) {
         return raceListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  raceListDAO.count();
    }

    @Override
    public RaceList getByPropertyName(String propertyName, String propertyValue) {
                return raceListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public RaceListDAO getRaceListDAO() {
        return raceListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setRaceListDAO(RaceListDAO RaceListDAO) {
        this.raceListDAO = RaceListDAO;
    }

    @Override
    public List<RaceList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return raceListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
