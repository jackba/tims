package com.hashthrims.services.Impl;

import com.hashthrims.domain.DisciplinaryAction;
import com.hashthrims.repository.jpa.DisciplinaryActionDAO;
import com.hashthrims.services.DisciplinaryActionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("disciplinaryactionService")
@Transactional
public class DisciplinaryActionImplService implements DisciplinaryActionService{
     @Autowired
    private DisciplinaryActionDAO disciplinaryactionDAO;


    @Override
    public DisciplinaryAction find(Long id) {
        if(id!= null){
        return disciplinaryactionDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(DisciplinaryAction entity) {
        disciplinaryactionDAO.persist(entity);
    }

    @Override
    public void merge(DisciplinaryAction entity) {
        disciplinaryactionDAO.merge(entity);
    }

    @Override
    public void remove(DisciplinaryAction entity) {

        disciplinaryactionDAO.remove(entity);
    }

    @Override
    public List<DisciplinaryAction> findAll() {
         return  disciplinaryactionDAO.findAll();
    }

    @Override
    public List<DisciplinaryAction> findInRange(int firstResult, int maxResults) {
         return disciplinaryactionDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  disciplinaryactionDAO.count();
    }

    @Override
    public DisciplinaryAction getByPropertyName(String propertyName, String propertyValue) {
                return disciplinaryactionDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DisciplinaryActionDAO getDisciplinaryActionDAO() {
        return disciplinaryactionDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDisciplinaryActionDAO(DisciplinaryActionDAO disciplinaryactionDAO) {
        this.disciplinaryactionDAO = disciplinaryactionDAO;
    }

    @Override
    public List<DisciplinaryAction> getEntitiesByProperName(String propertyName, String propertyValue) {
        return disciplinaryactionDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}