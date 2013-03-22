/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringFunders;
import com.hashthrims.repository.jpa.MentoringFundersDAO;
import com.hashthrims.services.MentoringFundersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentoringFundersService")

public class MentoringFundersImplService implements MentoringFundersService{
     @Autowired
    private MentoringFundersDAO mentoringFundersDAO;


    @Override
    public MentoringFunders find(Long id) {
        if(id!= null){
        return mentoringFundersDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(MentoringFunders entity) {
        mentoringFundersDAO.persist(entity);
    }

    @Override
    public void merge(MentoringFunders entity) {
        mentoringFundersDAO.merge(entity);
    }

    @Override
    public void remove(MentoringFunders entity) {

        mentoringFundersDAO.remove(entity);
    }

    @Override
    public List<MentoringFunders> findAll() {
         return  mentoringFundersDAO.findAll();
    }

    @Override
    public List<MentoringFunders> findInRange(int firstResult, int maxResults) {
         return mentoringFundersDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringFundersDAO.count();
    }

    @Override
    public MentoringFunders getByPropertyName(String propertyName, String propertyValue) {
                return mentoringFundersDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringFundersDAO getMentoringFundersDAO() {
        return mentoringFundersDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringFundersDAO(MentoringFundersDAO MentoringFundersDAO) {
        this.mentoringFundersDAO = MentoringFundersDAO;
    }

    @Override
    public List<MentoringFunders> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringFundersDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
