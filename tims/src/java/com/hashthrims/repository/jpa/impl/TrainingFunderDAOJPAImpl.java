/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.repository.jpa.TrainingFunderDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Repository("trainingFunderDAO")
@Transactional
public class TrainingFunderDAOJPAImpl implements TrainingFunderDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TrainingFunder find(Long id) {
        return em.find(TrainingFunder.class, id);
    }

    @Override
    public void persist(TrainingFunder entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingFunder entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingFunder entity) {
        TrainingFunder acc =em.find(TrainingFunder.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingFunder> findAll() {
        return (List<TrainingFunder>) em.createQuery("SELECT a FROM TrainingFunder a").getResultList();
    }

    @Override
    public List<TrainingFunder> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingFunder a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingFunder a").getSingleResult();
    }

    @Override
    public TrainingFunder getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingFunder> list = em.createQuery("SELECT e FROM  TrainingFunder e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingFunder> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingFunder> list = em.createQuery("SELECT e FROM  TrainingFunder e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
