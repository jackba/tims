/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.Nodes;
import com.hashthrims.repository.jpa.NodesDAO;
import com.hashthrims.services.NodesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("nodesService")
@Transactional
public class NodesImplService implements NodesService{
     @Autowired
    private NodesDAO nodesDAO;


    @Override
    public Nodes find(Long id) {
        if(id!= null){
        return nodesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Nodes entity) {
        nodesDAO.persist(entity);
    }

    @Override
    public void merge(Nodes entity) {
        nodesDAO.merge(entity);
    }

    @Override
    public void remove(Nodes entity) {

        nodesDAO.remove(entity);
    }

    @Override
    public List<Nodes> findAll() {
         return  nodesDAO.findAll();
    }

    @Override
    public List<Nodes> findInRange(int firstResult, int maxResults) {
         return nodesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  nodesDAO.count();
    }

    @Override
    public Nodes getByPropertyName(String propertyName, String propertyValue) {
                return nodesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public NodesDAO getNodesDAO() {
        return nodesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setNodesDAO(NodesDAO NodesDAO) {
        this.nodesDAO = NodesDAO;
    }

    @Override
    public List<Nodes> getEntitiesByProperName(String propertyName, String propertyValue) {
        return nodesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
