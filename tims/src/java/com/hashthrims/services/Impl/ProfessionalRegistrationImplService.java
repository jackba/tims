package com.hashthrims.services.Impl;

import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.repository.jpa.ProfessionalRegistrationDAO;
import com.hashthrims.services.ProfessionalRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("professionalRegistrationService")
@Transactional
public class ProfessionalRegistrationImplService implements ProfessionalRegistrationService{
     @Autowired
    private ProfessionalRegistrationDAO professionalregistrationDAO;


    @Override
    public ProfessionalRegistration find(Long id) {
        if(id!= null){
        return professionalregistrationDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(ProfessionalRegistration entity) {
        professionalregistrationDAO.persist(entity);
    }

    @Override
    public void merge(ProfessionalRegistration entity) {
        professionalregistrationDAO.merge(entity);
    }

    @Override
    public void remove(ProfessionalRegistration entity) {

        professionalregistrationDAO.remove(entity);
    }

    @Override
    public List<ProfessionalRegistration> findAll() {
         return  professionalregistrationDAO.findAll();
    }

    @Override
    public List<ProfessionalRegistration> findInRange(int firstResult, int maxResults) {
         return professionalregistrationDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  professionalregistrationDAO.count();
    }

    @Override
    public ProfessionalRegistration getByPropertyName(String propertyName, String propertyValue) {
                return professionalregistrationDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ProfessionalRegistrationDAO getProfessionalRegistrationDAO() {
        return professionalregistrationDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setProfessionalRegistrationDAO(ProfessionalRegistrationDAO professionalregistrationDAO) {
        this.professionalregistrationDAO = professionalregistrationDAO;
    }

    @Override
    public List<ProfessionalRegistration> getEntitiesByProperName(String propertyName, String propertyValue) {
        return professionalregistrationDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}