/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringSession;
import com.hashthrims.repository.jpa.MentoringSessionDAO;
import com.hashthrims.services.MentoringSessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("mentoringSessionService")
@Transactional
public class MentoringSessionImplService implements MentoringSessionService {

    @Autowired
    private MentoringSessionDAO mentoringSessionDAO;

    @Override
    public MentoringSession find(Long id) {
        if (id != null) {
            return mentoringSessionDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void persist(MentoringSession entity) {
        mentoringSessionDAO.persist(entity);
    }

    @Override
    public void merge(MentoringSession entity) {
        mentoringSessionDAO.merge(entity);
    }

    @Override
    public void remove(MentoringSession entity) {

        mentoringSessionDAO.remove(entity);
    }

    @Override
    public List<MentoringSession> findAll() {
        return mentoringSessionDAO.findAll();
    }

    @Override
    public List<MentoringSession> findInRange(int firstResult, int maxResults) {
        return mentoringSessionDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return mentoringSessionDAO.count();
    }

    @Override
    public MentoringSession getByPropertyName(String propertyName, String propertyValue) {
        return mentoringSessionDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringSessionDAO getMentoringSessionDAO() {
        return mentoringSessionDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringSessionDAO(MentoringSessionDAO MentoringSessionDAO) {
        this.mentoringSessionDAO = MentoringSessionDAO;
    }

    @Override
    public List<MentoringSession> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringSessionDAO.getEntitiesByProperName(propertyName, propertyValue);
    }

    @Override
    public MentoringSession restFundersAndCompetencies(MentoringSession mSession) {
        mSession.getMentoringFunders().clear();
        mSession.getMentoringCompetencies().clear();
        mSession.getMentoringMentors().clear();
        mentoringSessionDAO.merge(mSession);
        return mSession;
    }
}
