package com.hashthrims.services.Impl;

import com.hashthrims.domain.Employee;
import com.hashthrims.repository.jpa.EmployeeDAO;
import com.hashthrims.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("employeeService")

public class EmployeeImplService implements EmployeeService{
     @Autowired
    private EmployeeDAO employeeDAO;


    @Override
    public Employee find(Long id) {
        if(id!= null){
        return employeeDAO.find(id);
        }
        return null;
    }


    @Override
    public void persist(Employee entity) {
        employeeDAO.persist(entity);
    }

    @Override
    public void merge(Employee entity) {
        employeeDAO.merge(entity);
    }

    @Override
    public void remove(Employee entity) {

        employeeDAO.remove(entity);
    }

    @Override
    public List<Employee> findAll() {
         return  employeeDAO.findAll();
    }

    @Override
    public List<Employee> findInRange(int firstResult, int maxResults) {
         return employeeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  employeeDAO.count();
    }

    @Override
    public Employee getByPropertyName(String propertyName, String propertyValue) {
                return employeeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getEntitiesByProperName(String propertyName, String propertyValue) {
        return employeeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}