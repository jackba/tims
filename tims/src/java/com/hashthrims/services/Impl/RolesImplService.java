/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.Roles;
import com.hashthrims.repository.jpa.RolesDAO;
import com.hashthrims.services.RolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("rolesService")
@Transactional
public class RolesImplService implements RolesService{
     @Autowired
    private RolesDAO rolesDAO;


    @Override
    public Roles find(Long id) {
        if(id!= null){
        return rolesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Roles entity) {
        rolesDAO.persist(entity);
    }

    @Override
    public void merge(Roles entity) {
        rolesDAO.merge(entity);
    }

    @Override
    public void remove(Roles entity) {

        rolesDAO.remove(entity);
    }

    @Override
    public List<Roles> findAll() {
         return  rolesDAO.findAll();
    }

    @Override
    public List<Roles> findInRange(int firstResult, int maxResults) {
         return rolesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  rolesDAO.count();
    }

    @Override
    public Roles getByPropertyName(String propertyName, String propertyValue) {
                return rolesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public RolesDAO getRolesDAO() {
        return rolesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setRolesDAO(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    @Override
    public List<Roles> getEntitiesByProperName(String propertyName, String propertyValue) {
        return rolesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
