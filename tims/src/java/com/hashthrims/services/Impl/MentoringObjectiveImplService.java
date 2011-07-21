/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringObjective;
import com.hashthrims.repository.jpa.MentoringObjectiveDAO;
import com.hashthrims.services.MentoringObjectiveService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("mentoringObjectiveService")
@Transactional
public class MentoringObjectiveImplService implements MentoringObjectiveService{
     @Autowired
    private MentoringObjectiveDAO mentoringObjectiveDAO;


    @Override
    public MentoringObjective find(Long id) {
        if(id!= null){
        return mentoringObjectiveDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(MentoringObjective entity) {
        mentoringObjectiveDAO.persist(entity);
    }

    @Override
    public void merge(MentoringObjective entity) {
        mentoringObjectiveDAO.merge(entity);
    }

    @Override
    public void remove(MentoringObjective entity) {

        mentoringObjectiveDAO.remove(entity);
    }

    @Override
    public List<MentoringObjective> findAll() {
         return  mentoringObjectiveDAO.findAll();
    }

    @Override
    public List<MentoringObjective> findInRange(int firstResult, int maxResults) {
         return mentoringObjectiveDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringObjectiveDAO.count();
    }

    @Override
    public MentoringObjective getByPropertyName(String propertyName, String propertyValue) {
                return mentoringObjectiveDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringObjectiveDAO getMentoringObjectiveDAO() {
        return mentoringObjectiveDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringObjectiveDAO(MentoringObjectiveDAO MentoringObjectiveDAO) {
        this.mentoringObjectiveDAO = MentoringObjectiveDAO;
    }

    @Override
    public List<MentoringObjective> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringObjectiveDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
