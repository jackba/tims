/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.repository.jpa.MentoringFieldDAO;
import com.hashthrims.services.MentoringFieldService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentoringFieldService")

public class MentoringFieldImplService implements MentoringFieldService{
     @Autowired
    private MentoringFieldDAO mentoringFieldDAO;


    @Override
    public MentoringField find(Long id) {
        if(id!= null){
        return mentoringFieldDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(MentoringField entity) {
        mentoringFieldDAO.persist(entity);
    }

    @Override
    public void merge(MentoringField entity) {
        mentoringFieldDAO.merge(entity);
    }

    @Override
    public void remove(MentoringField entity) {

        mentoringFieldDAO.remove(entity);
    }

    @Override
    public List<MentoringField> findAll() {
         return  mentoringFieldDAO.findAll();
    }

    @Override
    public List<MentoringField> findInRange(int firstResult, int maxResults) {
         return mentoringFieldDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringFieldDAO.count();
    }

    @Override
    public MentoringField getByPropertyName(String propertyName, String propertyValue) {
                return mentoringFieldDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringFieldDAO getMentoringFieldDAO() {
        return mentoringFieldDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringFieldDAO(MentoringFieldDAO MentoringFieldDAO) {
        this.mentoringFieldDAO = MentoringFieldDAO;
    }

    @Override
    public List<MentoringField> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringFieldDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
