/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services.Impl;

import com.hashthrims.domain.nimart.CompetencyTasks;
import com.hashthrims.repository.jpa.CompetencyTasksDAO;
import com.hashthrims.services.CompetencyTasksService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("competencyTasksService")
@Transactional
public class CompetencyTasksImplService implements CompetencyTasksService {

    @Autowired
    private CompetencyTasksDAO competencyTasksDAO;

    @Override
    public CompetencyTasks find(Long id) {
        if (id != null) {
            return competencyTasksDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void persist(CompetencyTasks entity) {
        competencyTasksDAO.persist(entity);
    }

    @Override
    public void merge(CompetencyTasks entity) {
        competencyTasksDAO.merge(entity);
    }

    @Override
    public void remove(CompetencyTasks entity) {

        competencyTasksDAO.remove(entity);
    }

    @Override
    public List<CompetencyTasks> findAll() {
        return competencyTasksDAO.findAll();
    }

    @Override
    public List<CompetencyTasks> findInRange(int firstResult, int maxResults) {
        return competencyTasksDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return competencyTasksDAO.count();
    }

    @Override
    public CompetencyTasks getByPropertyName(String propertyName, String propertyValue) {
        return competencyTasksDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CompetencyTasksDAO getCompetencyTasksDAO() {
        return competencyTasksDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCompetencyTasksDAO(CompetencyTasksDAO competencyTasksDAO) {
        this.competencyTasksDAO = competencyTasksDAO;
    }

    @Override
    public List<CompetencyTasks> getEntitiesByProperName(String propertyName, String propertyValue) {
        return competencyTasksDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
