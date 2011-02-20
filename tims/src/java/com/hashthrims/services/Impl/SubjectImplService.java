/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.Subject;
import com.hashthrims.repository.jpa.SubjectDAO;
import com.hashthrims.services.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Service("subjectService")
@Transactional
public class SubjectImplService implements SubjectService{

    @Autowired
    private SubjectDAO usersDAO;


    @Override
    public Subject find(Long id) {
        if(id!= null){
        return usersDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Subject entity) {
        usersDAO.persist(entity);
    }

    @Override
    public void merge(Subject entity) {
        usersDAO.merge(entity);
    }

    @Override
    public void remove(Subject entity) {

        usersDAO.remove(entity);
    }

    @Override
    public List<Subject> findAll() {
         return  usersDAO.findAll();
    }

    @Override
    public List<Subject> findInRange(int firstResult, int maxResults) {
         return usersDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  usersDAO.count();
    }

    @Override
    public Subject getByPropertyName(String propertyName, String propertyValue) {
                return usersDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public SubjectDAO getSubjectDAO() {
        return usersDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setSubjectDAO(SubjectDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public List<Subject> getEntitiesByProperName(String propertyName, String propertyValue) {
        return usersDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

}
