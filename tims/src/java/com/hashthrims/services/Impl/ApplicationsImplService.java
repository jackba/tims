package com.hashthrims.services.Impl;

import com.hashthrims.domain.Applications;
import com.hashthrims.repository.jpa.ApplicationsDAO;
import com.hashthrims.services.ApplicationsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("applicationsService")

public class ApplicationsImplService implements ApplicationsService{
     @Autowired
    private ApplicationsDAO applicationsDAO;


    @Override
    public Applications find(Long id) {
        if(id!= null){
        return applicationsDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Applications entity) {
        applicationsDAO.persist(entity);
    }

    @Override
    public void merge(Applications entity) {
        applicationsDAO.merge(entity);
    }

    @Override
    public void remove(Applications entity) {

        applicationsDAO.remove(entity);
    }

    @Override
    public List<Applications> findAll() {
         return  applicationsDAO.findAll();
    }

    @Override
    public List<Applications> findInRange(int firstResult, int maxResults) {
         return applicationsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  applicationsDAO.count();
    }

    @Override
    public Applications getByPropertyName(String propertyName, String propertyValue) {
                return applicationsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ApplicationsDAO getApplicationsDAO() {
        return applicationsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setApplicationsDAO(ApplicationsDAO applicationsDAO) {
        this.applicationsDAO = applicationsDAO;
    }

    @Override
    public List<Applications> getEntitiesByProperName(String propertyName, String propertyValue) {
        return applicationsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}