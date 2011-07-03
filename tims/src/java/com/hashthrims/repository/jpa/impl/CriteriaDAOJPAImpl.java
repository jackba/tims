/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.Criteria;
import com.hashthrims.repository.jpa.CriteriaDAO;
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

@Repository("criteriaDAO")
@Transactional
public class CriteriaDAOJPAImpl  implements CriteriaDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Criteria find(Long id) {
        return em.find(Criteria.class, id);
    }

    @Override
    public void persist(Criteria entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Criteria entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Criteria entity) {
        Criteria acc =em.find(Criteria.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Criteria> findAll() {
        return (List<Criteria>) em.createQuery("SELECT a FROM Criteria a").getResultList();
    }

    @Override
    public List<Criteria> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Criteria a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Criteria a").getSingleResult();
    }

    @Override
    public Criteria getByPropertyName(String propertyName, String propertyValue) {
        List<Criteria> list = em.createQuery("SELECT e FROM  Criteria e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Criteria> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Criteria> list = em.createQuery("SELECT e FROM  Criteria e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
