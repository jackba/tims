/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.repository.jpa.EmployeeMentoringDAO;
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

@Repository("employeeMentoringDAO")
@Transactional
public class EmployeeMentoringDAOJPAImpl  implements EmployeeMentoringDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public EmployeeMentoring find(Long id) {
        return em.find(EmployeeMentoring.class, id);
    }

    @Override
    public void persist(EmployeeMentoring entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmployeeMentoring entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmployeeMentoring entity) {
        EmployeeMentoring acc =em.find(EmployeeMentoring.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmployeeMentoring> findAll() {
        return (List<EmployeeMentoring>) em.createQuery("SELECT a FROM EmployeeMentoring a").getResultList();
    }

    @Override
    public List<EmployeeMentoring> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmployeeMentoring a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmployeeMentoring a").getSingleResult();
    }

    @Override
    public EmployeeMentoring getByPropertyName(String propertyName, String propertyValue) {
        List<EmployeeMentoring> list = em.createQuery("SELECT e FROM  EmployeeMentoring e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmployeeMentoring> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EmployeeMentoring> list = em.createQuery("SELECT e FROM  EmployeeMentoring e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
