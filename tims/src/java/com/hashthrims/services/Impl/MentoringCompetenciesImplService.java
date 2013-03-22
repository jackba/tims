/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringCompetencies;
import com.hashthrims.repository.jpa.MentoringCompetenciesDAO;
import com.hashthrims.services.MentoringCompetenciesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentoringCompetenciesService")

public class MentoringCompetenciesImplService implements MentoringCompetenciesService{
     @Autowired
    private MentoringCompetenciesDAO mentoringCompetenciesDAO;


    @Override
    public MentoringCompetencies find(Long id) {
        if(id!= null){
        return mentoringCompetenciesDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(MentoringCompetencies entity) {
        mentoringCompetenciesDAO.persist(entity);
    }

    @Override
    public void merge(MentoringCompetencies entity) {
        mentoringCompetenciesDAO.merge(entity);
    }

    @Override
    public void remove(MentoringCompetencies entity) {

        mentoringCompetenciesDAO.remove(entity);
    }

    @Override
    public List<MentoringCompetencies> findAll() {
         return  mentoringCompetenciesDAO.findAll();
    }

    @Override
    public List<MentoringCompetencies> findInRange(int firstResult, int maxResults) {
         return mentoringCompetenciesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringCompetenciesDAO.count();
    }

    @Override
    public MentoringCompetencies getByPropertyName(String propertyName, String propertyValue) {
                return mentoringCompetenciesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringCompetenciesDAO getMentoringCompetenciesDAO() {
        return mentoringCompetenciesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringCompetenciesDAO(MentoringCompetenciesDAO MentoringCompetenciesDAO) {
        this.mentoringCompetenciesDAO = MentoringCompetenciesDAO;
    }

    @Override
    public List<MentoringCompetencies> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringCompetenciesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
