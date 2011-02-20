/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.Department;
import com.hashthrims.repository.jpa.DepartmentDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("departmentDAO")
@Transactional
public class DepartmentDAOJPAImpl implements DepartmentDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Department find(Long id) {
        return em.find(Department.class, id);
    }

    @Override
    public void persist(Department entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Department entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Department entity) {
        Department acc =em.find(Department.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Department> findAll() {
        return (List<Department>) em.createQuery("SELECT a FROM Department a").getResultList();
    }

    @Override
    public List<Department> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Department a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Department a").getSingleResult();
    }

    @Override
    public Department getByPropertyName(String propertyName, String propertyValue) {
        List<Department> list = em.createQuery("SELECT e FROM  Department e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Department> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Department> list = em.createQuery("SELECT e FROM  Department e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
