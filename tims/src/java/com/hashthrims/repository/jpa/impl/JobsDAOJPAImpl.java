/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.repository.jpa.JobsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("jobsDAO")
@Transactional
public class JobsDAOJPAImpl implements JobsDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Jobs find(Long id) {
        return em.find(Jobs.class, id);
    }

    @Override
    public void persist(Jobs entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Jobs entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Jobs entity) {
        Jobs acc =em.find(Jobs.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Jobs> findAll() {
        return (List<Jobs>) em.createQuery("SELECT a FROM Jobs a").getResultList();
    }

    @Override
    public List<Jobs> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Jobs a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Jobs a").getSingleResult();
    }

    @Override
    public Jobs getByPropertyName(String propertyName, String propertyValue) {
        List<Jobs> list = em.createQuery("SELECT e FROM  Jobs e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Jobs> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Jobs> list = em.createQuery("SELECT e FROM  Jobs e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
