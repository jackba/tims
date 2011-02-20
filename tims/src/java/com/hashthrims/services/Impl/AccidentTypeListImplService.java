package com.hashthrims.services.Impl;


import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
import com.hashthrims.services.AccidentTypeListService;
import com.hashthrims.services.AccidentTypeListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("accidentTypeListService")
@Transactional
public class AccidentTypeListImplService implements AccidentTypeListService{
     @Autowired
    private AccidentTypeListDAO accidentTypeListDAO;


    @Override
    public AccidentTypeList find(Long id) {
        if(id!= null){
        return accidentTypeListDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(AccidentTypeList entity) {
        accidentTypeListDAO.persist(entity);
    }

    @Override
    public void merge(AccidentTypeList entity) {
        accidentTypeListDAO.merge(entity);
    }

    @Override
    public void remove(AccidentTypeList entity) {

        accidentTypeListDAO.remove(entity);
    }

    @Override
    public List<AccidentTypeList> findAll() {
         return  accidentTypeListDAO.findAll();
    }

    @Override
    public List<AccidentTypeList> findInRange(int firstResult, int maxResults) {
         return accidentTypeListDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  accidentTypeListDAO.count();
    }

    @Override
    public AccidentTypeList getByPropertyName(String propertyName, String propertyValue) {
                return accidentTypeListDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public AccidentTypeListDAO getAccidentTypeListDAO() {
        return accidentTypeListDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setAccidentTypeListDAO(AccidentTypeListDAO accidentTypeListDAO) {
        this.accidentTypeListDAO = accidentTypeListDAO;
    }

    @Override
    public List<AccidentTypeList> getEntitiesByProperName(String propertyName, String propertyValue) {
        return accidentTypeListDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}