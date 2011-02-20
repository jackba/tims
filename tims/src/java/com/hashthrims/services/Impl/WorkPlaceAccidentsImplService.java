package com.hashthrims.services.Impl;

import com.hashthrims.domain.WorkPlaceAccidents;
import com.hashthrims.repository.jpa.WorkPlaceAccidentsDAO;
import com.hashthrims.services.WorkPlaceAccidentsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("workplaceaccidentsService")
@Transactional
public class WorkPlaceAccidentsImplService implements WorkPlaceAccidentsService{
     @Autowired
    private WorkPlaceAccidentsDAO workplaceaccidentsDAO;


    @Override
    public WorkPlaceAccidents find(Long id) {
        if(id!= null){
        return workplaceaccidentsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(WorkPlaceAccidents entity) {
        workplaceaccidentsDAO.persist(entity);
    }

    @Override
    public void merge(WorkPlaceAccidents entity) {
        workplaceaccidentsDAO.merge(entity);
    }

    @Override
    public void remove(WorkPlaceAccidents entity) {

        workplaceaccidentsDAO.remove(entity);
    }

    @Override
    public List<WorkPlaceAccidents> findAll() {
         return  workplaceaccidentsDAO.findAll();
    }

    @Override
    public List<WorkPlaceAccidents> findInRange(int firstResult, int maxResults) {
         return workplaceaccidentsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  workplaceaccidentsDAO.count();
    }

    @Override
    public WorkPlaceAccidents getByPropertyName(String propertyName, String propertyValue) {
                return workplaceaccidentsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public WorkPlaceAccidentsDAO getWorkPlaceAccidentsDAO() {
        return workplaceaccidentsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setWorkPlaceAccidentsDAO(WorkPlaceAccidentsDAO workplaceaccidentsDAO) {
        this.workplaceaccidentsDAO = workplaceaccidentsDAO;
    }

    @Override
    public List<WorkPlaceAccidents> getEntitiesByProperName(String propertyName, String propertyValue) {
        return workplaceaccidentsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}