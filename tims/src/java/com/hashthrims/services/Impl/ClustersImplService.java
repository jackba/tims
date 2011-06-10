/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.Clusters;
import com.hashthrims.repository.jpa.ClustersDAO;
import com.hashthrims.services.ClustersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("clustersService")
@Transactional
public class ClustersImplService implements ClustersService{
     @Autowired
    private ClustersDAO clustersDAO;


    @Override
    public Clusters find(Long id) {
        if(id!= null){
        return clustersDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Clusters entity) {
        clustersDAO.persist(entity);
    }

    @Override
    public void merge(Clusters entity) {
        clustersDAO.merge(entity);
    }

    @Override
    public void remove(Clusters entity) {

        clustersDAO.remove(entity);
    }

    @Override
    public List<Clusters> findAll() {
         return  clustersDAO.findAll();
    }

    @Override
    public List<Clusters> findInRange(int firstResult, int maxResults) {
         return clustersDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  clustersDAO.count();
    }

    @Override
    public Clusters getByPropertyName(String propertyName, String propertyValue) {
                return clustersDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ClustersDAO getClustersDAO() {
        return clustersDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setClustersDAO(ClustersDAO ClustersDAO) {
        this.clustersDAO = ClustersDAO;
    }

    @Override
    public List<Clusters> getEntitiesByProperName(String propertyName, String propertyValue) {
        return clustersDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
