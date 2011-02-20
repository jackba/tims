/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.repository.jpa.DepartureReasonsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("departureReasonsDAO")
@Transactional
public class DepartureReasonsDAOJPAImpl implements DepartureReasonsDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public DepartureReasons find(Long id) {
        return em.find(DepartureReasons.class, id);
    }

    @Override
    public void persist(DepartureReasons entity) {
        em.persist(entity);
    }

    @Override
    public void merge(DepartureReasons entity) {
        em.merge(entity);
    }

    @Override
    public void remove(DepartureReasons entity) {
        DepartureReasons acc =em.find(DepartureReasons.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<DepartureReasons> findAll() {
        return (List<DepartureReasons>) em.createQuery("SELECT a FROM DepartureReasons a").getResultList();
    }

    @Override
    public List<DepartureReasons> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from DepartureReasons a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM DepartureReasons a").getSingleResult();
    }

    @Override
    public DepartureReasons getByPropertyName(String propertyName, String propertyValue) {
        List<DepartureReasons> list = em.createQuery("SELECT e FROM  DepartureReasons e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<DepartureReasons> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<DepartureReasons> list = em.createQuery("SELECT e FROM  DepartureReasons e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
