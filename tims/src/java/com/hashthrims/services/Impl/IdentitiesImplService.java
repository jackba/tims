/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services.Impl;

import com.hashthrims.domain.Identities;
import com.hashthrims.repository.jpa.IdentitiesDAO;
import com.hashthrims.services.IdentitiesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abismail
 */
@Service("identitiesService")
public class IdentitiesImplService implements IdentitiesService {

    @Autowired
    private IdentitiesDAO identitiesDAO;

    @Override
    public Identities find(Long id) {
        if (id != null) {
            return identitiesDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Identities entity) {
        identitiesDAO.persist(entity);
    }

    @Override
    public void merge(Identities entity) {
        identitiesDAO.merge(entity);
    }

    @Override
    public void remove(Identities entity) {

        identitiesDAO.remove(entity);
    }

    @Override
    public List<Identities> findAll() {
        return identitiesDAO.findAll();
    }

    @Override
    public List<Identities> findInRange(int firstResult, int maxResults) {
        return identitiesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return identitiesDAO.count();
    }

    @Override
    public Identities getByPropertyName(String propertyName, String propertyValue) {
        return identitiesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public IdentitiesDAO getIdentitiesDAO() {
        return identitiesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setIdentitiesDAO(IdentitiesDAO identitiesDAO) {
        this.identitiesDAO = identitiesDAO;
    }

    @Override
    public List<Identities> getEntitiesByProperName(String propertyName, String propertyValue) {
        return identitiesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
