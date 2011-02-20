/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.positions.Status;
import com.hashthrims.repository.jpa.StatusDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("statusDAO")
@Transactional
public class StatusDAOJPAImpl implements StatusDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Status find(Long id) {
        return em.find(Status.class, id);
    }

    @Override
    public void persist(Status entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Status entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Status entity) {
        Status acc =em.find(Status.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Status> findAll() {
        return (List<Status>) em.createQuery("SELECT a FROM Status a").getResultList();
    }

    @Override
    public List<Status> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Status a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Status a").getSingleResult();
    }

    @Override
    public Status getByPropertyName(String propertyName, String propertyValue) {
        List<Status> list = em.createQuery("SELECT e FROM  Status e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Status> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Status> list = em.createQuery("SELECT e FROM  Status e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
