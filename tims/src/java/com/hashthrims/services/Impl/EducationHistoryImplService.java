package com.hashthrims.services.Impl;

import com.hashthrims.domain.EducationHistory;
import com.hashthrims.repository.jpa.EducationHistoryDAO;
import com.hashthrims.services.EducationHistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("educationHistoryService")
@Transactional
public class EducationHistoryImplService implements EducationHistoryService{
     @Autowired
    private EducationHistoryDAO educationhistoryDAO;


    @Override
    public EducationHistory find(Long id) {
        if(id!= null){
        return educationhistoryDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(EducationHistory entity) {
        educationhistoryDAO.persist(entity);
    }

    @Override
    public void merge(EducationHistory entity) {
        educationhistoryDAO.merge(entity);
    }

    @Override
    public void remove(EducationHistory entity) {

        educationhistoryDAO.remove(entity);
    }

    @Override
    public List<EducationHistory> findAll() {
         return  educationhistoryDAO.findAll();
    }

    @Override
    public List<EducationHistory> findInRange(int firstResult, int maxResults) {
         return educationhistoryDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  educationhistoryDAO.count();
    }

    @Override
    public EducationHistory getByPropertyName(String propertyName, String propertyValue) {
                return educationhistoryDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EducationHistoryDAO getEducationHistoryDAO() {
        return educationhistoryDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEducationHistoryDAO(EducationHistoryDAO educationhistoryDAO) {
        this.educationhistoryDAO = educationhistoryDAO;
    }

    @Override
    public List<EducationHistory> getEntitiesByProperName(String propertyName, String propertyValue) {
        return educationhistoryDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}