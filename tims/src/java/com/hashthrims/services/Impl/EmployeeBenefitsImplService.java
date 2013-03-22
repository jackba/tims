package com.hashthrims.services.Impl;

import com.hashthrims.domain.EmployeeBenefits;
import com.hashthrims.repository.jpa.EmployeeBenefitsDAO;
import com.hashthrims.services.EmployeeBenefitsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("employeebenefitsService")

public class EmployeeBenefitsImplService implements EmployeeBenefitsService{
     @Autowired
    private EmployeeBenefitsDAO employeebenefitsDAO;


    @Override
    public EmployeeBenefits find(Long id) {
        if(id!= null){
        return employeebenefitsDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(EmployeeBenefits entity) {
        employeebenefitsDAO.persist(entity);
    }

    @Override
    public void merge(EmployeeBenefits entity) {
        employeebenefitsDAO.merge(entity);
    }

    @Override
    public void remove(EmployeeBenefits entity) {

        employeebenefitsDAO.remove(entity);
    }

    @Override
    public List<EmployeeBenefits> findAll() {
         return  employeebenefitsDAO.findAll();
    }

    @Override
    public List<EmployeeBenefits> findInRange(int firstResult, int maxResults) {
         return employeebenefitsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  employeebenefitsDAO.count();
    }

    @Override
    public EmployeeBenefits getByPropertyName(String propertyName, String propertyValue) {
                return employeebenefitsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmployeeBenefitsDAO getEmployeeBenefitsDAO() {
        return employeebenefitsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEmployeeBenefitsDAO(EmployeeBenefitsDAO employeebenefitsDAO) {
        this.employeebenefitsDAO = employeebenefitsDAO;
    }

    @Override
    public List<EmployeeBenefits> getEntitiesByProperName(String propertyName, String propertyValue) {
        return employeebenefitsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}