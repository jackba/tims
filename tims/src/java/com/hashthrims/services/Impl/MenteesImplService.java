/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.Mentees;
import com.hashthrims.repository.jpa.MenteesDAO;
import com.hashthrims.services.MenteesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("menteesService")
@Transactional
public class MenteesImplService implements MenteesService{
     @Autowired
    private MenteesDAO menteesDAO;


    @Override
    public Mentees find(Long id) {
        if(id!= null){
        return menteesDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Mentees entity) {
        menteesDAO.persist(entity);
    }

    @Override
    public void merge(Mentees entity) {
        menteesDAO.merge(entity);
    }

    @Override
    public void remove(Mentees entity) {

        menteesDAO.remove(entity);
    }

    @Override
    public List<Mentees> findAll() {
         return  menteesDAO.findAll();
    }

    @Override
    public List<Mentees> findInRange(int firstResult, int maxResults) {
         return menteesDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  menteesDAO.count();
    }

    @Override
    public Mentees getByPropertyName(String propertyName, String propertyValue) {
                return menteesDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public MenteesDAO getMenteesDAO() {
        return menteesDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setMenteesDAO(MenteesDAO MenteesDAO) {
        this.menteesDAO = MenteesDAO;
    }

    @Override
    public List<Mentees> getEntitiesByProperName(String propertyName, String propertyValue) {
        return menteesDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
