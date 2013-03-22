/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.positions.Positions;
import com.hashthrims.repository.jpa.PositionsDAO;
import com.hashthrims.services.PositionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author stud
 */
@Service("positionsService")

public class PositionsImplService implements PositionsService{
     @Autowired
    private PositionsDAO positionsDAO;


    @Override
    public Positions find(Long id) {
        if(id!= null){
        return positionsDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Positions entity) {
        positionsDAO.persist(entity);
    }

    @Override
    public void merge(Positions entity) {
        positionsDAO.merge(entity);
    }

    @Override
    public void remove(Positions entity) {

        positionsDAO.remove(entity);
    }

    @Override
    public List<Positions> findAll() {
         return  positionsDAO.findAll();
    }

    @Override
    public List<Positions> findInRange(int firstResult, int maxResults) {
         return positionsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  positionsDAO.count();
    }

    @Override
    public Positions getByPropertyName(String propertyName, String propertyValue) {
                return positionsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public PositionsDAO getPositionsDAO() {
        return positionsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setPositionsDAO(PositionsDAO positionDAO) {
        this.positionsDAO = positionsDAO;
    }

    @Override
    public List<Positions> getEntitiesByProperName(String propertyName, String propertyValue) {
        return positionsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
