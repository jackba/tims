/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.repository.jpa.ClassificationDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("classificationsDAO")
@Transactional
public class ClassificationsDAOJPAImpl implements ClassificationDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Classifications find(Long id) {
        return em.find(Classifications.class, id);
    }

    @Override
    public void persist(Classifications entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Classifications entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Classifications entity) {
        Classifications acc =em.find(Classifications.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Classifications> findAll() {
        return (List<Classifications>) em.createQuery("SELECT a FROM Classifications a").getResultList();
    }

    @Override
    public List<Classifications> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Classifications a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Classifications a").getSingleResult();
    }

    @Override
    public Classifications getByPropertyName(String propertyName, String propertyValue) {
        List<Classifications> list = em.createQuery("SELECT e FROM  Classifications e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Classifications> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Classifications> list = em.createQuery("SELECT e FROM  Classifications e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
