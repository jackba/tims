/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.OrganisationTrainers;
import com.hashthrims.repository.jpa.OrganisationTrainersDAO;
import com.hashthrims.services.OrganisationTrainersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("organisationTrainersService")

public class OrganisationTrainersImplService implements OrganisationTrainersService{
     @Autowired
    private OrganisationTrainersDAO organisationTrainersDAO;


    @Override
    public OrganisationTrainers find(Long id) {
        if(id!= null){
        return organisationTrainersDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(OrganisationTrainers entity) {
        organisationTrainersDAO.persist(entity);
    }

    @Override
    public void merge(OrganisationTrainers entity) {
        organisationTrainersDAO.merge(entity);
    }

    @Override
    public void remove(OrganisationTrainers entity) {

        organisationTrainersDAO.remove(entity);
    }

    @Override
    public List<OrganisationTrainers> findAll() {
         return  organisationTrainersDAO.findAll();
    }

    @Override
    public List<OrganisationTrainers> findInRange(int firstResult, int maxResults) {
         return organisationTrainersDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  organisationTrainersDAO.count();
    }

    @Override
    public OrganisationTrainers getByPropertyName(String propertyName, String propertyValue) {
                return organisationTrainersDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public OrganisationTrainersDAO getOrganisationTrainersDAO() {
        return organisationTrainersDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setOrganisationTrainersDAO(OrganisationTrainersDAO OrganisationTrainersDAO) {
        this.organisationTrainersDAO = OrganisationTrainersDAO;
    }

    @Override
    public List<OrganisationTrainers> getEntitiesByProperName(String propertyName, String propertyValue) {
        return organisationTrainersDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
