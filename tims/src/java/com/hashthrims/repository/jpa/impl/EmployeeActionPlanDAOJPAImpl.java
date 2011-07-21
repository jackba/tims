/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.EmployeeActionPlan;
import com.hashthrims.repository.jpa.EmployeeActionPlanDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */

@Repository("employeeActionPlanDAO")
@Transactional
public class EmployeeActionPlanDAOJPAImpl  implements EmployeeActionPlanDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public EmployeeActionPlan find(Long id) {
        return em.find(EmployeeActionPlan.class, id);
    }

    @Override
    public void persist(EmployeeActionPlan entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmployeeActionPlan entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmployeeActionPlan entity) {
        EmployeeActionPlan acc =em.find(EmployeeActionPlan.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmployeeActionPlan> findAll() {
        return (List<EmployeeActionPlan>) em.createQuery("SELECT a FROM EmployeeActionPlan a").getResultList();
    }

    @Override
    public List<EmployeeActionPlan> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmployeeActionPlan a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmployeeActionPlan a").getSingleResult();
    }

    @Override
    public EmployeeActionPlan getByPropertyName(String propertyName, String propertyValue) {
        List<EmployeeActionPlan> list = em.createQuery("SELECT e FROM  EmployeeActionPlan e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmployeeActionPlan> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EmployeeActionPlan> list = em.createQuery("SELECT e FROM  EmployeeActionPlan e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
