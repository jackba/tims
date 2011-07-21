/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.MentoringAreasList;
import com.hashthrims.repository.jpa.MentoringAreasListDAO;
import com.hashthrims.services.MentoringAreasListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("mentoringAreasListService")
@Transactional
public class MentoringAreasListImplService implements MentoringAreasListService {

    @Autowired
    private MentoringAreasListDAO mentoringAreasListDAO;

    @Override
    public MentoringAreasList find(Long id) {
        if (id != null) {
            return mentoringAreasListDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void persist(MentoringAreasList entity) {
        mentoringAreasListDAO.persist(entity);
    }

    @Override
    public void merge(MentoringAreasList entity) {
        mentoringAreasListDAO.merge(entity);
    }

    @Override
    public void remove(MentoringAreasList entity) {

        mentoringAreasListDAO.remove(entity);
    }

    @Override
    public List<MentoringAreasList> findAll() {
        return mentoringAreasListDAO.findAll();
    }

    @Override
    public List<MentoringAreasList> findInRange(int firstResult, int maxResults) {
        return mentoringAreasListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return mentoringAreasListDAO.count();
    }

    @Override
    public MentoringAreasList getByPropertyName(String propertyName, String propertyValue) {
        return mentoringAreasListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MentoringAreasListDAO getMentoringAreasListDAO() {
        return mentoringAreasListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMentoringAreasListDAO(MentoringAreasListDAO MentoringAreasListDAO) {
        this.mentoringAreasListDAO = MentoringAreasListDAO;
    }

    @Override
    public List<MentoringAreasList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return mentoringAreasListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
