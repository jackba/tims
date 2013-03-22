/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringMentors;
import com.hashthrims.repository.jpa.MentoringMentorsDAO;
import com.hashthrims.services.MentoringMentorsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentoringMentorsService")

public class MentoringMentorsImplService implements MentoringMentorsService{
     @Autowired
    private MentoringMentorsDAO mentoringMentorsDAO;


    @Override
    public MentoringMentors find(Long id) {
        if(id!= null){
        return mentoringMentorsDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(MentoringMentors entity) {
        mentoringMentorsDAO.persist(entity);
    }

    @Override
    public void merge(MentoringMentors entity) {
        mentoringMentorsDAO.merge(entity);
    }

    @Override
    public void remove(MentoringMentors entity) {

        mentoringMentorsDAO.remove(entity);
    }

    @Override
    public List<MentoringMentors> findAll() {
         return  mentoringMentorsDAO.findAll();
    }

    @Override
    public List<MentoringMentors> findInRange(int firstResult, int maxResults) {
         return mentoringMentorsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringMentorsDAO.count();
    }

    @Override
    public MentoringMentors getByPropertyName(String propertyName, String propertyValue) {
                return mentoringMentorsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringMentorsDAO getMentoringMentorsDAO() {
        return mentoringMentorsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringMentorsDAO(MentoringMentorsDAO MentoringMentorsDAO) {
        this.mentoringMentorsDAO = MentoringMentorsDAO;
    }

    @Override
    public List<MentoringMentors> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringMentorsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
