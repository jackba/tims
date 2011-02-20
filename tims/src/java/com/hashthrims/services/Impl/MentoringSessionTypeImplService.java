/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.repository.jpa.MentoringSessionTypeDAO;
import com.hashthrims.services.MentoringSessionTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("mentoringSessionTypeService")
@Transactional
public class MentoringSessionTypeImplService implements MentoringSessionTypeService{
     @Autowired
    private MentoringSessionTypeDAO mentoringSessionTypeDAO;


    @Override
    public MentoringSessionType find(Long id) {
        if(id!= null){
        return mentoringSessionTypeDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(MentoringSessionType entity) {
        mentoringSessionTypeDAO.persist(entity);
    }

    @Override
    public void merge(MentoringSessionType entity) {
        mentoringSessionTypeDAO.merge(entity);
    }

    @Override
    public void remove(MentoringSessionType entity) {

        mentoringSessionTypeDAO.remove(entity);
    }

    @Override
    public List<MentoringSessionType> findAll() {
         return  mentoringSessionTypeDAO.findAll();
    }

    @Override
    public List<MentoringSessionType> findInRange(int firstResult, int maxResults) {
         return mentoringSessionTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  mentoringSessionTypeDAO.count();
    }

    @Override
    public MentoringSessionType getByPropertyName(String propertyName, String propertyValue) {
                return mentoringSessionTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringSessionTypeDAO getMentoringSessionTypeDAO() {
        return mentoringSessionTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringSessionTypeDAO(MentoringSessionTypeDAO MentoringSessionTypeDAO) {
        this.mentoringSessionTypeDAO = MentoringSessionTypeDAO;
    }

    @Override
    public List<MentoringSessionType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringSessionTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
