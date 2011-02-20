/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.Department;
import com.hashthrims.repository.jpa.DepartmentDAO;
import com.hashthrims.services.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("departmentService")
@Transactional
public class DepartmentImplService implements DepartmentService{
     @Autowired
    private DepartmentDAO departmentDAO;


    @Override
    public Department find(Long id) {
        if(id!= null){
        return departmentDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Department entity) {
        departmentDAO.persist(entity);
    }

    @Override
    public void merge(Department entity) {
        departmentDAO.merge(entity);
    }

    @Override
    public void remove(Department entity) {

        departmentDAO.remove(entity);
    }

    @Override
    public List<Department> findAll() {
         return  departmentDAO.findAll();
    }

    @Override
    public List<Department> findInRange(int firstResult, int maxResults) {
         return departmentDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  departmentDAO.count();
    }

    @Override
    public Department getByPropertyName(String propertyName, String propertyValue) {
                return departmentDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> getEntitiesByProperName(String propertyName, String propertyValue) {
        return departmentDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
