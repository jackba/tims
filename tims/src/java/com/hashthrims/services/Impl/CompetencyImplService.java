package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.repository.jpa.CompetencyDAO;
import com.hashthrims.services.CompetencyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("competencyService")
public class CompetencyImplService implements CompetencyService {

    @Autowired
    private CompetencyDAO competencyDAO;

    @Override
    public EmployeeCourses find(Long id) {
        if (id != null) {
            return competencyDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(EmployeeCourses entity) {
        competencyDAO.persist(entity);
    }

    @Override
    public void merge(EmployeeCourses entity) {
        competencyDAO.merge(entity);
    }

    @Override
    public void remove(EmployeeCourses entity) {

        competencyDAO.remove(entity);
    }

    @Override
    public List<EmployeeCourses> findAll() {
        return competencyDAO.findAll();
    }

    @Override
    public List<EmployeeCourses> findInRange(int firstResult, int maxResults) {
        return competencyDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return competencyDAO.count();
    }

    @Override
    public EmployeeCourses getByPropertyName(String propertyName, String propertyValue) {
        return competencyDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CompetencyDAO getCompetencyDAO() {
        return competencyDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCompetencyDAO(CompetencyDAO competencyDAO) {
        this.competencyDAO = competencyDAO;
    }

    @Override
    public List<EmployeeCourses> getEntitiesByProperName(String propertyName, String propertyValue) {
        return competencyDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}