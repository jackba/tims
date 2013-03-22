/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.MentorExpertiseArea;
import com.hashthrims.repository.jpa.MentorExpertiseAreaDAO;
import com.hashthrims.services.MentorExpertiseAreaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentorExpertiseAreaService")

public class MentorExpertiseAreaImplService implements MentorExpertiseAreaService{
     @Autowired
    private MentorExpertiseAreaDAO mentorExpertiseAreaDAO;


    @Override
    public MentorExpertiseArea find(Long id) {
        if(id!= null){
        return mentorExpertiseAreaDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(MentorExpertiseArea entity) {
        mentorExpertiseAreaDAO.persist(entity);
    }

    @Override
    public void merge(MentorExpertiseArea entity) {
        mentorExpertiseAreaDAO.merge(entity);
    }

    @Override
    public void remove(MentorExpertiseArea entity) {

        mentorExpertiseAreaDAO.remove(entity);
    }

    @Override
    public List<MentorExpertiseArea> findAll() {
         return  mentorExpertiseAreaDAO.findAll();
    }

    @Override
    public List<MentorExpertiseArea> findInRange(int firstResult, int maxResults) {
         return mentorExpertiseAreaDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentorExpertiseAreaDAO.count();
    }

    @Override
    public MentorExpertiseArea getByPropertyName(String propertyName, String propertyValue) {
                return mentorExpertiseAreaDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentorExpertiseAreaDAO getMentorExpertiseAreaDAO() {
        return mentorExpertiseAreaDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentorExpertiseAreaDAO(MentorExpertiseAreaDAO MentorExpertiseAreaDAO) {
        this.mentorExpertiseAreaDAO = MentorExpertiseAreaDAO;
    }

    @Override
    public List<MentorExpertiseArea> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentorExpertiseAreaDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
