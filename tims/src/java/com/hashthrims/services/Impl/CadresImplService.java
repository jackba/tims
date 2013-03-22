/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.repository.jpa.CadresDAO;
import com.hashthrims.services.CadresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("cadresService")

public class CadresImplService implements CadresService{
     @Autowired
    private CadresDAO cadresDAO;


    @Override
    public Cadres find(Long id) {
        if(id!= null){
        return cadresDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Cadres entity) {
        cadresDAO.persist(entity);
    }

    @Override
    public void merge(Cadres entity) {
        cadresDAO.merge(entity);
    }

    @Override
    public void remove(Cadres entity) {

        cadresDAO.remove(entity);
    }

    @Override
    public List<Cadres> findAll() {
         return  cadresDAO.findAll();
    }

    @Override
    public List<Cadres> findInRange(int firstResult, int maxResults) {
         return cadresDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  cadresDAO.count();
    }

    @Override
    public Cadres getByPropertyName(String propertyName, String propertyValue) {
                return cadresDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CadresDAO getCadresDAO() {
        return cadresDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCadresDAO(CadresDAO cadresDAO) {
        this.cadresDAO = cadresDAO;
    }

    @Override
    public List<Cadres> getEntitiesByProperName(String propertyName, String propertyValue) {
        return cadresDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
