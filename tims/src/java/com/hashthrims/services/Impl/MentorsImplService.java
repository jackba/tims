/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.repository.jpa.MentorsDAO;
import com.hashthrims.services.MentorsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentorsService")
@Transactional
public class MentorsImplService implements MentorsService{
     @Autowired
    private MentorsDAO mentorsDAO;


    @Override
    public Mentors find(Long id) {
        if(id!= null){
        return mentorsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Mentors entity) {
        mentorsDAO.persist(entity);
    }

    @Override
    public void merge(Mentors entity) {
        mentorsDAO.merge(entity);
    }

    @Override
    public void remove(Mentors entity) {

        mentorsDAO.remove(entity);
    }

    @Override
    public List<Mentors> findAll() {
         return  mentorsDAO.findAll();
    }

    @Override
    public List<Mentors> findInRange(int firstResult, int maxResults) {
         return mentorsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentorsDAO.count();
    }

    @Override
    public Mentors getByPropertyName(String propertyName, String propertyValue) {
                return mentorsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentorsDAO getMentorsDAO() {
        return mentorsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentorsDAO(MentorsDAO MentorsDAO) {
        this.mentorsDAO = MentorsDAO;
    }

    @Override
    public List<Mentors> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentorsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
