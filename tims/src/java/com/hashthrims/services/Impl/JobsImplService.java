/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.repository.jpa.JobsDAO;
import com.hashthrims.services.JobsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("jobsService")
@Transactional
public class JobsImplService implements JobsService{
     @Autowired
    private JobsDAO jobsDAO;


    @Override
    public Jobs find(Long id) {
        if(id!= null){
        return jobsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Jobs entity) {
        jobsDAO.persist(entity);
    }

    @Override
    public void merge(Jobs entity) {
        jobsDAO.merge(entity);
    }

    @Override
    public void remove(Jobs entity) {

        jobsDAO.remove(entity);
    }

    @Override
    public List<Jobs> findAll() {
         return  jobsDAO.findAll();
    }

    @Override
    public List<Jobs> findInRange(int firstResult, int maxResults) {
         return jobsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  jobsDAO.count();
    }

    @Override
    public Jobs getByPropertyName(String propertyName, String propertyValue) {
                return jobsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public JobsDAO getJobsDAO() {
        return jobsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setJobsDAO(JobsDAO jobsDAO) {
        this.jobsDAO = jobsDAO;
    }

    @Override
    public List<Jobs> getEntitiesByProperName(String propertyName, String propertyValue) {
        return jobsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
