/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.repository.jpa.MentorsRolesDAO;
import com.hashthrims.services.MentorsRolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentorsRolesService")
@Transactional
public class MentorsRolesImplService implements MentorsRolesService{
     @Autowired
    private MentorsRolesDAO mentorsRolesDAO;


    @Override
    public MentorsRoles find(Long id) {
        if(id!= null){
        return mentorsRolesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(MentorsRoles entity) {
        mentorsRolesDAO.persist(entity);
    }

    @Override
    public void merge(MentorsRoles entity) {
        mentorsRolesDAO.merge(entity);
    }

    @Override
    public void remove(MentorsRoles entity) {

        mentorsRolesDAO.remove(entity);
    }

    @Override
    public List<MentorsRoles> findAll() {
         return  mentorsRolesDAO.findAll();
    }

    @Override
    public List<MentorsRoles> findInRange(int firstResult, int maxResults) {
         return mentorsRolesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentorsRolesDAO.count();
    }

    @Override
    public MentorsRoles getByPropertyName(String propertyName, String propertyValue) {
                return mentorsRolesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentorsRolesDAO getMentorsRolesDAO() {
        return mentorsRolesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentorsRolesDAO(MentorsRolesDAO MentorsRolesDAO) {
        this.mentorsRolesDAO = MentorsRolesDAO;
    }

    @Override
    public List<MentorsRoles> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentorsRolesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
