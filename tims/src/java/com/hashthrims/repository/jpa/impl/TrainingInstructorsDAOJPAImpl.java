/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingInstructors;
import com.hashthrims.repository.jpa.TrainingInstructorsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Repository("trainingInstructorsDAO")
@Transactional
public class TrainingInstructorsDAOJPAImpl implements TrainingInstructorsDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TrainingInstructors find(Long id) {
        return em.find(TrainingInstructors.class, id);
    }

    @Override
    public void persist(TrainingInstructors entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingInstructors entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingInstructors entity) {
        TrainingInstructors acc =em.find(TrainingInstructors.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingInstructors> findAll() {
        return (List<TrainingInstructors>) em.createQuery("SELECT a FROM TrainingInstructors a").getResultList();
    }

    @Override
    public List<TrainingInstructors> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingInstructors a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingInstructors a").getSingleResult();
    }

    @Override
    public TrainingInstructors getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingInstructors> list = em.createQuery("SELECT e FROM  TrainingInstructors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingInstructors> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingInstructors> list = em.createQuery("SELECT e FROM  TrainingInstructors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
