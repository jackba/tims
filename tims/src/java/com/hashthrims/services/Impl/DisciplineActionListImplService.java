/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.repository.jpa.DisciplineActionTypeListDAO;
import com.hashthrims.services.DisciplineActionTypeListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("disciplineActionListService")

public class DisciplineActionListImplService implements DisciplineActionTypeListService{
     @Autowired
    private DisciplineActionTypeListDAO discipleneActionListDAO;


    @Override
    public DisciplineActionTypeList find(Long id) {
        if(id!= null){
        return discipleneActionListDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(DisciplineActionTypeList entity) {
        discipleneActionListDAO.persist(entity);
    }

    @Override
    public void merge(DisciplineActionTypeList entity) {
        discipleneActionListDAO.merge(entity);
    }

    @Override
    public void remove(DisciplineActionTypeList entity) {

        discipleneActionListDAO.remove(entity);
    }

    @Override
    public List<DisciplineActionTypeList> findAll() {
         return  discipleneActionListDAO.findAll();
    }

    @Override
    public List<DisciplineActionTypeList> findInRange(int firstResult, int maxResults) {
         return discipleneActionListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  discipleneActionListDAO.count();
    }

    @Override
    public DisciplineActionTypeList getByPropertyName(String propertyName, String propertyValue) {
                return discipleneActionListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DisciplineActionTypeListDAO getDisciplineActionTypeListDAO() {
        return discipleneActionListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDisciplineActionTypeListDAO(DisciplineActionTypeListDAO discipleneActionListDAO) {
        this.discipleneActionListDAO = discipleneActionListDAO;
    }

    @Override
    public List<DisciplineActionTypeList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return discipleneActionListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
