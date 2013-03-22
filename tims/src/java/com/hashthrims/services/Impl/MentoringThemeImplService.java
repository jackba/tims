/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.repository.jpa.MentoringThemeDAO;
import com.hashthrims.services.MentoringThemeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentoringThemeService")

public class MentoringThemeImplService implements MentoringThemeService{
     @Autowired
    private MentoringThemeDAO mentoringThemeDAO;


    @Override
    public MentoringTheme find(Long id) {
        if(id!= null){
        return mentoringThemeDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(MentoringTheme entity) {
        mentoringThemeDAO.persist(entity);
    }

    @Override
    public void merge(MentoringTheme entity) {
        mentoringThemeDAO.merge(entity);
    }

    @Override
    public void remove(MentoringTheme entity) {

        mentoringThemeDAO.remove(entity);
    }

    @Override
    public List<MentoringTheme> findAll() {
         return  mentoringThemeDAO.findAll();
    }

    @Override
    public List<MentoringTheme> findInRange(int firstResult, int maxResults) {
         return mentoringThemeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringThemeDAO.count();
    }

    @Override
    public MentoringTheme getByPropertyName(String propertyName, String propertyValue) {
                return mentoringThemeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringThemeDAO getMentoringThemeDAO() {
        return mentoringThemeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringThemeDAO(MentoringThemeDAO MentoringThemeDAO) {
        this.mentoringThemeDAO = MentoringThemeDAO;
    }

    @Override
    public List<MentoringTheme> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringThemeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
